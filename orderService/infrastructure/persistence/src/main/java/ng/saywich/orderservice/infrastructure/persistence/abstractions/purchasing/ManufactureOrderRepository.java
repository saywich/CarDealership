package ng.saywich.orderservice.infrastructure.persistence.abstractions.purchasing;

import ng.saywich.orderservice.application.abstractions.order.IManagerRepository;
import ng.saywich.orderservice.application.abstractions.order.IManufactureOrderRepository;
import ng.saywich.orderservice.application.contracts.order.operations.models.OrderFilter;
import ng.saywich.shared.domain.purchasing.order.CarManufactureOrder;
import ng.saywich.orderservice.infrastructure.persistence.jparepositories.purchasing.IManufactureOrderDaoRepository;
import ng.saywich.orderservice.infrastructure.persistence.mappers.purchasing.ManufactureOrderMapper;
import ng.saywich.orderservice.infrastructure.persistence.specifications.ManufactureOrderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ManufactureOrderRepository implements IManufactureOrderRepository {
    @Autowired
    private IManufactureOrderDaoRepository repository;

    @Autowired
    private ManufactureOrderMapper mapper;

    @Autowired
    private IManagerRepository managerRepository;

    @Override
    public List<CarManufactureOrder> query(UUID[] orderIds) {
        return repository.findAllById(List.of(orderIds)).stream()
            .map(o -> mapper.toOrder(o))
            .toList();
    }

    @Override
    public List<CarManufactureOrder> queryWithFilter(OrderFilter filter) {
        return repository.findAll(new ManufactureOrderSpecification(filter))
            .stream()
            .map(mapper::toOrder)
            .toList();
    }

    @Override
    public void createOrder(CarManufactureOrder order) {
        repository.save(mapper.toOrderDao(order));
    }

    @Override
    public void updateOrder(CarManufactureOrder order) {
        repository.save(mapper.toOrderDao(order));
    }
}
