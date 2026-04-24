package ng.saywich.storageservice.domain.stock;

import ng.saywich.shared.domain.car.Car;
import ng.saywich.storageservice.domain.assembling.AssembleRequest;

import java.util.UUID;

public record StockCarReservation(
    UUID id,
    AssembleRequest assembleRequest,
    Car reservedCar
) { }
