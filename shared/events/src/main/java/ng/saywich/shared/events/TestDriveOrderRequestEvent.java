package ng.saywich.shared.events;

import java.util.Date;
import java.util.UUID;

public record TestDriveOrderRequestEvent(
    UUID eventId,
    UUID orderId,
    UUID carId,
    UUID customerId,
    Date timestamp
) { }
