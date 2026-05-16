package ng.saywich.integrationtests;

import io.grpc.ManagedChannel;
import io.grpc.Server;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import ng.saywich.orderservice.application.contracts.dtos.carstock.StockCarDto;
import ng.saywich.orderservice.infrastructure.grpc.abstractions.StockCarRepository;
import ng.saywich.orderservice.infrastructure.grpc.mappers.StockCarMapperImpl;
import ng.saywich.shared.grpc.carstock.ListCarsInStockRequest;
import ng.saywich.shared.grpc.carstock.ListCarsInStockResponse;
import ng.saywich.shared.grpc.carstock.StockCar;
import ng.saywich.shared.grpc.carstock.StockCarServiceGrpc;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StockCarGrpcIntegrationTest {
    @Test
    void orderRepositoryReceivesCarsInStockThroughBlockingGrpcStub() throws Exception {
        UUID carId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID modelId = UUID.fromString("22222222-2222-2222-2222-222222222222");
        UUID brandId = UUID.fromString("33333333-3333-3333-3333-333333333333");

        try (GrpcFixture fixture = GrpcFixture.start(List.of(
            StockCar.newBuilder()
                .setCarId(carId.toString())
                .setVinNumber("fancyvin")
                .setModelId(modelId.toString())
                .setModelName("Camry")
                .setBrandId(brandId.toString())
                .setBrandName("Toyota")
                .setBasePrice("42000")
                .build()
        ))) {
            List<StockCarDto> cars = fixture.repository().queryInStock();

            assertEquals(1, cars.size());
            StockCarDto car = cars.get(0);

            assertAll(
                () -> assertEquals(carId, car.carId()),
                () -> assertEquals("fancyvin", car.vinNumber()),
                () -> assertEquals(modelId, car.modelId()),
                () -> assertEquals("Camry", car.modelName()),
                () -> assertEquals(brandId, car.brandId()),
                () -> assertEquals("Toyota", car.brandName()),
                () -> assertEquals(new BigDecimal("42000"), car.basePrice())
            );
        }
    }

    @Test
    void orderRepositoryReceivesEmptyListWhenStorageHasNoCarsInStock() throws Exception {
        try (GrpcFixture fixture = GrpcFixture.start(List.of())) {
            assertTrue(fixture.repository().queryInStock().isEmpty());
        }
    }

    private record GrpcFixture(
        Server server,
        ManagedChannel channel,
        StockCarRepository repository
    ) implements AutoCloseable {
        static GrpcFixture start(List<StockCar> carsInStock) throws IOException {
            String serverName = InProcessServerBuilder.generateName();
            Server server = InProcessServerBuilder.forName(serverName)
                .directExecutor()
                .addService(new StubStockCarGrpcService(carsInStock))
                .build()
                .start();

            ManagedChannel channel = InProcessChannelBuilder.forName(serverName)
                .directExecutor()
                .build();

            StockCarRepository repository = new StockCarRepository();
            ReflectionTestUtils.setField(
                repository,
                "stub",
                StockCarServiceGrpc.newBlockingStub(channel)
            );
            ReflectionTestUtils.setField(repository, "mapper", new StockCarMapperImpl());

            return new GrpcFixture(server, channel, repository);
        }

        @Override
        public void close() throws InterruptedException {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            server.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    private static final class StubStockCarGrpcService
        extends StockCarServiceGrpc.StockCarServiceImplBase {
        private final List<StockCar> carsInStock;

        private StubStockCarGrpcService(List<StockCar> carsInStock) {
            this.carsInStock = carsInStock;
        }

        @Override
        public void listCarsInStock(
            ListCarsInStockRequest request,
            StreamObserver<ListCarsInStockResponse> responseObserver
        ) {
            responseObserver.onNext(
                ListCarsInStockResponse.newBuilder()
                    .addAllCars(carsInStock)
                    .build()
            );
            responseObserver.onCompleted();
        }
    }
}
