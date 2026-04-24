package ng.saywich.storageservice.application.services.orders.listeners;

import lombok.RequiredArgsConstructor;
import ng.saywich.shared.domain.car.Car;
import ng.saywich.shared.domain.exceptions.DomainValidationException;
import ng.saywich.shared.events.InStockOrderRequestEvent;
import ng.saywich.shared.events.InStockOrderResponseEvent;
import ng.saywich.storageservice.application.abstractions.messaging.IProcessedMessageRepository;
import ng.saywich.storageservice.application.abstractions.assembling.IAssembleRequestRepository;
import ng.saywich.storageservice.application.abstractions.assembling.IWarehouseAdminRepository;
import ng.saywich.storageservice.application.abstractions.car.ICarRepository;
import ng.saywich.storageservice.application.abstractions.stock.IStockCarReservationRepository;
import ng.saywich.storageservice.application.models.messaging.EventType;
import ng.saywich.storageservice.application.models.messaging.ProcessedMessage;
import ng.saywich.storageservice.application.services.messaging.OutboxService;
import ng.saywich.storageservice.domain.assembling.AssembleRequest;
import ng.saywich.storageservice.domain.assembling.AssembleRequestStatus;
import ng.saywich.storageservice.domain.assembling.WarehouseAdmin;
import ng.saywich.storageservice.domain.stock.StockCarReservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class InStockOrderListener {
    private final ICarRepository carRepository;

    private final IStockCarReservationRepository carReservationRepository;

    private final IProcessedMessageRepository processedMessageRepository;

    private final IAssembleRequestRepository assembleRequestRepository;

    private final IWarehouseAdminRepository warehouseAdminRepository;

    private final OutboxService outboxService;

    @Value("${kafka.topics.in-stock-order-response}")
    public String responseTopicName;

    @KafkaListener(
        topics = "${kafka.topics.in-stock-order-request}",
        groupId = "storage-service"
    )
    @Transactional
    public void handle(InStockOrderRequestEvent event) {
        String consumer = "in-stock-order-request-listener";

        if (processedMessageRepository.exists(event.eventId(), consumer)) {
            return;
        }

        boolean carAvailable = carReservationRepository.isCarAvailable(event.carId());

        if (carAvailable) {
            List<Car> carList = carRepository.query(new UUID[] { event.carId() });

            if (carList.isEmpty()) {
                throw new DomainValidationException("Can't find car.");
            }

            Car requestedCar = carList.get(0);

            List<WarehouseAdmin> adminList = warehouseAdminRepository.query(new UUID[0]);

            if (adminList.isEmpty()) {
                throw new DomainValidationException("Can't find warehouse admin.");
            }

            AssembleRequest assembleRequest = new AssembleRequest(
                UUID.randomUUID(),
                event.orderId(),
                adminList.get(ThreadLocalRandom.current().nextInt(adminList.size())),
                AssembleRequestStatus.ASSEMBLED
            );

            assembleRequestRepository.create(assembleRequest);
            carReservationRepository.create(new StockCarReservation(
                UUID.randomUUID(),
                assembleRequest,
                requestedCar
            ));
        }

        InStockOrderResponseEvent responseEvent =
            new InStockOrderResponseEvent(
                UUID.randomUUID(),
                event.orderId(),
                event.carId(),
                carAvailable,
                Date.from(Instant.now())
            );

        outboxService.add(
            responseTopicName,
            responseEvent.orderId().toString(),
            EventType.IN_STOCK_ORDER,
            responseEvent
        );

        processedMessageRepository.create(
            new ProcessedMessage(event.eventId(), consumer, Date.from(Instant.now()))
        );
    }
}
