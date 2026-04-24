package ng.saywich.storageservice.domain.stock;

import java.util.UUID;

public record PartStockUnit(
    UUID id,
    UUID partId,
    PartType partType,
    Integer totalCount,
    Integer reservedCount
) { }
