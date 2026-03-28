package ng.saywich.infrastructure.persistence.abstractions.purchasing;

import ng.saywich.application.abstractions.order.IManufactureOrderRepository;
import ng.saywich.application.abstractions.order.IManagerRepository;
import ng.saywich.domain.purchasing.order.CarManufactureOrder;
import ng.saywich.infrastructure.persistence.jparepositories.purchasing.IManufactureOrderDaoRepository;
import ng.saywich.infrastructure.persistence.mappers.purchasing.ManufactureOrderMapper;
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
    public void createOrder(CarManufactureOrder order) {
        repository.save(mapper.toOrderDao(order));
    }

    @Override
    public void updateOrder(CarManufactureOrder order) {
        repository.save(mapper.toOrderDao(order));
    }
}
