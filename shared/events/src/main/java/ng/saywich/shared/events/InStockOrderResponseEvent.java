package ng.saywich.shared.events;

import java.util.Date;
import java.util.UUID;

public record InStockOrderResponseEvent(
    UUID eventId,
    UUID orderId,
    UUID carId,
    boolean isSuccess,
    Date timestamp
) { }
