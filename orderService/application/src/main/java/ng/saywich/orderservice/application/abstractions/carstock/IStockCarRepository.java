package ng.saywich.orderservice.application.abstractions.carstock;

import ng.saywich.orderservice.application.contracts.dtos.carstock.StockCarDto;

import java.util.List;

public interface IStockCarRepository {
    List<StockCarDto> queryInStock();
}
