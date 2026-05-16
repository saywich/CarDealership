package ng.saywich.orderservice.infrastructure.grpc.abstractions;

import ng.saywich.orderservice.application.abstractions.carstock.IStockCarRepository;
import ng.saywich.orderservice.application.contracts.dtos.carstock.StockCarDto;
import ng.saywich.orderservice.infrastructure.grpc.mappers.StockCarMapper;
import ng.saywich.shared.grpc.carstock.ListCarsInStockRequest;
import ng.saywich.shared.grpc.carstock.StockCarServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockCarRepository implements IStockCarRepository {
    @Autowired
    private StockCarServiceGrpc.StockCarServiceBlockingStub stub;

    @Autowired
    private StockCarMapper mapper;

    @Override
    public List<StockCarDto> queryInStock() {
        return stub.listCarsInStock(ListCarsInStockRequest.newBuilder().build())
            .getCarsList()
            .stream()
            .map(mapper::toDto)
            .toList();
    }
}
