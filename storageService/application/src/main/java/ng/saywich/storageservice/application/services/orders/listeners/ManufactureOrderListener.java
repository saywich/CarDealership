package ng.saywich.storageservice.application.services.orders.listeners;

import lombok.RequiredArgsConstructor;
import ng.saywich.shared.domain.car.parts.OptionSet;
import ng.saywich.shared.domain.exceptions.DomainValidationException;
import ng.saywich.shared.events.ManufactureOrderRequestEvent;
import ng.saywich.shared.events.ManufactureOrderResponseEvent;
import ng.saywich.storageservice.application.abstractions.messaging.IProcessedMessageRepository;
import ng.saywich.storageservice.application.abstractions.assembling.IAssembleRequestRepository;
import ng.saywich.storageservice.application.abstractions.assembling.IWarehouseAdminRepository;
import ng.saywich.storageservice.application.abstractions.carparts.IOptionSetRepository;
import ng.saywich.storageservice.application.abstractions.stock.IStockPartReservationRepository;
import ng.saywich.storageservice.application.contracts.stock.IPartStockUnitService;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryPartStockAvailability;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryPartStockUnitByPartId;
import ng.saywich.storageservice.application.models.messaging.EventType;
import ng.saywich.storageservice.application.models.messaging.ProcessedMessage;
import ng.saywich.storageservice.application.services.messaging.OutboxService;
import ng.saywich.storageservice.domain.assembling.AssembleRequest;
import ng.saywich.storageservice.domain.assembling.AssembleRequestStatus;
import ng.saywich.storageservice.domain.assembling.WarehouseAdmin;
import ng.saywich.storageservice.domain.stock.StockPartReservation;
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
public class ManufactureOrderListener {
    private final IOptionSetRepository optionSetRepository;

    private final IWarehouseAdminRepository warehouseAdminRepository;

    private final IAssembleRequestRepository assembleRequestRepository;

    private final IStockPartReservationRepository partReservationRepository;

    private final IProcessedMessageRepository processedMessageRepository;

    private final IPartStockUnitService partStockUnitService;

    private final OutboxService outboxService;

    @Value("${kafka.topics.manufacture-order-response}")
    public String responseTopicName;

    @KafkaListener(
        topics = "${kafka.topics.manufacture-order-request}",
        groupId = "storage-service"
    )
    @Transactional
    public void handle(ManufactureOrderRequestEvent event) {
        String consumer = "manufacture-order-request-listener";

        if (processedMessageRepository.exists(event.eventId(), consumer)) {
            return;
        }

        List<UUID> requestedPartIdList = resolvePartIds(event.optionSetId());

        boolean allAvailable = requestedPartIdList.stream()
            .allMatch((id) -> {
                return partStockUnitService.queryAvailability(
                    new QueryPartStockAvailability.Request(id)
                ).isAvailable();
            });

        if (!allAvailable) {
            outboxService.add(
                responseTopicName,
                event.orderId().toString(),
                EventType.MANUFACTURE_ORDER,
                new ManufactureOrderResponseEvent(
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

        for (UUID id : requestedPartIdList) {
            partReservationRepository.create(new StockPartReservation(
                UUID.randomUUID(),
                assembleRequest,
                partStockUnitService.queryByPartId(
                    new QueryPartStockUnitByPartId.Request(id)
                ).unit()
            ));
        }

        ManufactureOrderResponseEvent responseEvent =
            new ManufactureOrderResponseEvent(
                UUID.randomUUID(),
                event.orderId(),
                true,
                Date.from(Instant.now())
            );

        outboxService.add(
            responseTopicName,
            responseEvent.orderId().toString(),
            EventType.MANUFACTURE_ORDER,
            responseEvent
        );

        processedMessageRepository.create(
            new ProcessedMessage(event.eventId(), consumer, Date.from(Instant.now()))
        );
    }

    private List<UUID> resolvePartIds(UUID optionSetId) {
        List<OptionSet> optionSetList = optionSetRepository
            .query(new UUID[] { optionSetId });

        if (optionSetList.isEmpty()) {
            throw new DomainValidationException("Can't find option set.");
        }

        OptionSet optionSet = optionSetList.get(0);

        return List.of(
            optionSet.engine().carPart().getPartId(),
            optionSet.transmission().carPart().getPartId(),
            optionSet.color().carPart().getPartId(),
            optionSet.rims().carPart().getPartId(),
            optionSet.interior().carPart().getPartId()
        );
    }
}
