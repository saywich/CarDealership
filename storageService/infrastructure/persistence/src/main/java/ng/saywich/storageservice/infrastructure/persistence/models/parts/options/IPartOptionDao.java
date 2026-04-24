package ng.saywich.storageservice.infrastructure.persistence.models.parts.options;

import java.math.BigDecimal;
import java.util.UUID;

public interface IPartOptionDao<T> {
    public UUID getId();

    public T getPart();

    public BigDecimal getPriceDifference();
}
