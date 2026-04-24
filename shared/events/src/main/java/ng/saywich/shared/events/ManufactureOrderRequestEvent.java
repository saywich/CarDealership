package ng.saywich.shared.events;

import java.util.Date;
import java.util.UUID;

public record ManufactureOrderRequestEvent(
    UUID eventId,
    UUID orderId,
    UUID carModelId,
    UUID optionSetId,
    UUID customerId,
    Date timestamp
) { }
