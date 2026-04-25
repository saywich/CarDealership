package ng.saywich.orderservice.infrastructure.persistence.abstractions.purchasing;

import ng.saywich.orderservice.application.abstractions.order.IInStockOrderRepository;
import ng.saywich.orderservice.application.contracts.order.operations.models.OrderFilter;
import ng.saywich.shared.domain.purchasing.order.CarInStockOrder;
import ng.saywich.orderservice.infrastructure.persistence.jparepositories.purchasing.IInStockOrderDaoRepository;
import ng.saywich.orderservice.infrastructure.persistence.mappers.purchasing.InStockOrderMapper;
import ng.saywich.orderservice.infrastructure.persistence.specifications.InStockOrderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class InStockOrderRepository implements IInStockOrderRepository {
    @Autowired
    private IInStockOrderDaoRepository repository;

    @Autowired
    private InStockOrderMapper mapper;

    @Override
    public List<CarInStockOrder> query(UUID[] orderIds) {
        if (orderIds.length == 0) {
            return repository.findAll().stream().map(mapper::toOrder).toList();
        }

        return repository.findAllById(List.of(orderIds)).stream()
            .map(o -> mapper.toOrder(o))
            .toList();
    }

    @Override
    public List<CarInStockOrder> queryWithFilter(OrderFilter filter) {
        return repository.findAll(new InStockOrderSpecification(filter))
            .stream()
            .map(mapper::toOrder)
            .toList();
    }

    @Override
    public void createOrder(CarInStockOrder order) {
        repository.save(mapper.toOrderDao(order));
    }

    @Override
    public void updateOrder(CarInStockOrder order) {
        repository.save(mapper.toOrderDao(order));
    }
}
