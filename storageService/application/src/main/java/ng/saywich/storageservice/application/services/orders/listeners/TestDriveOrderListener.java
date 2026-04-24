package ng.saywich.storageservice.application.services.orders.listeners;

import lombok.RequiredArgsConstructor;
import ng.saywich.shared.domain.car.Car;
import ng.saywich.shared.domain.exceptions.DomainValidationException;
import ng.saywich.shared.events.TestDriveOrderRequestEvent;
import ng.saywich.shared.events.TestDriveOrderResponseEvent;
import ng.saywich.storageservice.application.abstractions.messaging.IProcessedMessageRepository;
import ng.saywich.storageservice.application.abstractions.assembling.IAssembleRequestRepository;
import ng.saywich.storageservice.application.abstractions.assembling.IWarehouseAdminRepository;
import ng.saywich.storageservice.application.abstractions.car.ICarRepository;
import ng.saywich.storageservice.application.models.messaging.EventType;
import ng.saywich.storageservice.application.models.messaging.ProcessedMessage;
import ng.saywich.storageservice.application.services.messaging.OutboxService;
import ng.saywich.storageservice.domain.assembling.AssembleRequest;
import ng.saywich.storageservice.domain.assembling.AssembleRequestStatus;
import ng.saywich.storageservice.domain.assembling.WarehouseAdmin;
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
public class TestDriveOrderListener {
    private final ICarRepository carRepository;

    private final IProcessedMessageRepository processedMessageRepository;

    private final IAssembleRequestRepository assembleRequestRepository;

    private final IWarehouseAdminRepository warehouseAdminRepository;

    private final OutboxService outboxService;

    @Value("${kafka.topics.test-drive-order-response}")
    private String responseTopicName;

    @KafkaListener(
        topics = "${kafka.topics.test-drive-order-request}",
        groupId = "storage-service"
    )
    @Transactional
    public void handle(TestDriveOrderRequestEvent event) {
        String consumer = "test-drive-order-request-listener";

        if (processedMessageRepository.exists(event.eventId(), consumer)) {
            return;
        }

        List<Car> carList = carRepository.query(new UUID[] { event.carId() });

        if (carList.isEmpty()) {
            outboxService.add(
                responseTopicName,
                event.orderId().toString(),
                EventType.TEST_DRIVE_ORDER,
                new TestDriveOrderResponseEvent(
                    UUID.randomUUID(),
                    event.orderId(),
                    false,
                    Date.from(Instant.now())
                )
            );

            processedMessageRepository.create(
                new ProcessedMessage(event.eventId(), consumer, Date.from(Instant.now()))
            );
            return;
        }

        List<WarehouseAdmin> adminList = warehouseAdminRepository.query(new UUID[0]);

        if (adminList.isEmpty()) {
            throw new DomainValidationException("Can't find warehouse admin.");
        }

        AssembleRequest assembleRequest = new AssembleRequest(
            UUID.randomUUID(),
            event.orderId(),
            adminList.get(ThreadLocalRandom.current().nextInt(adminList.size())),
            AssembleRequestStatus.CREATED
        );

        assembleRequestRepository.create(assembleRequest);

        outboxService.add(
            responseTopicName,
            event.orderId().toString(),
            EventType.TEST_DRIVE_ORDER,
            new TestDriveOrderResponseEvent(
                UUID.randomUUID(),
                event.orderId(),
                true,
                Date.from(Instant.now())
            )
        );

        processedMessageRepository.create(
            new ProcessedMessage(event.eventId(), consumer, Date.from(Instant.now()))
        );
    }
}
