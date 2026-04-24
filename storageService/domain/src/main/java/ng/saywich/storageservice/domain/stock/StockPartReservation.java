package ng.saywich.storageservice.domain.stock;

import ng.saywich.storageservice.domain.assembling.AssembleRequest;

import java.util.UUID;

public record StockPartReservation(
    UUID id,
    AssembleRequest assembleRequest,
    PartStockUnit partStockUnit
) { }
