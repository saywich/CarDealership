package ng.saywich.infrastructure.persistence.abstractions.purchasing;

import ng.saywich.application.abstractions.order.IInStockOrderRepository;
import ng.saywich.application.contracts.order.operations.models.OrderFilter;
import ng.saywich.domain.purchasing.order.CarInStockOrder;
import ng.saywich.infrastructure.persistence.jparepositories.purchasing.IInStockOrderDaoRepository;
import ng.saywich.infrastructure.persistence.mappers.purchasing.InStockOrderMapper;
import ng.saywich.infrastructure.persistence.specifications.InStockOrderSpecification;
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
