package ng.saywich.storageservice.presentation.grpc;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import ng.saywich.shared.grpc.carstock.ListCarsInStockRequest;
import ng.saywich.shared.grpc.carstock.ListCarsInStockResponse;
import ng.saywich.shared.grpc.carstock.StockCar;
import ng.saywich.shared.grpc.carstock.StockCarServiceGrpc;
import ng.saywich.storageservice.application.contracts.car.ICarService;
import ng.saywich.storageservice.application.contracts.dtos.cars.CarDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StockCarGrpcService extends StockCarServiceGrpc.StockCarServiceImplBase {
    private final ICarService carService;

    @Override
    public void listCarsInStock(
        ListCarsInStockRequest request,
        StreamObserver<ListCarsInStockResponse> responseObserver
    ) {
        List<StockCar> cars = carService.queryInStock().cars().stream()
            .map(this::toGrpc)
            .toList();

        responseObserver.onNext(
            ListCarsInStockResponse.newBuilder()
                .addAllCars(cars)
                .build()
        );
        responseObserver.onCompleted();
    }

    private StockCar toGrpc(CarDto car) {
        return StockCar.newBuilder()
            .setCarId(car.carId().toString())
            .setVinNumber(car.vinNumber())
            .setModelId(car.model().modelId().toString())
            .setModelName(car.model().name())
            .setBrandId(car.model().brand().id().toString())
            .setBrandName(car.model().brand().name())
            .setBasePrice(car.model().basePrice().toPlainString())
            .build();
    }
}