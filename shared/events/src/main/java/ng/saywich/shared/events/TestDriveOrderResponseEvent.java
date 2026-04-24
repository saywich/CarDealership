package ng.saywich.shared.events;

import java.util.Date;
import java.util.UUID;

public record TestDriveOrderResponseEvent(
    UUID eventId,
    UUID orderId,
    boolean isSuccess,
    Date timestamp
) { }
