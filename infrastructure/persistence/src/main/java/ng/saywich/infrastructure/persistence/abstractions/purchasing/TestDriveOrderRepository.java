package ng.saywich.infrastructure.persistence.abstractions.purchasing;

import ng.saywich.application.abstractions.order.ITestDriveOrderRepository;
import ng.saywich.domain.purchasing.order.TestDriveOrder;
import ng.saywich.infrastructure.persistence.jparepositories.purchasing.ITestDriveOrderDaoRepository;
import ng.saywich.infrastructure.persistence.mappers.purchasing.TestDriveOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TestDriveOrderRepository implements ITestDriveOrderRepository {
    @Autowired
    private ITestDriveOrderDaoRepository repository;

    @Autowired
    private TestDriveOrderMapper mapper;

    @Override
    public List<TestDriveOrder> query(UUID[] orderIds) {
        return repository.findAllById(List.of(orderIds)).stream()
            .map(o -> mapper.toOrder(o))
            .toList();
    }

    @Override
    public void createOrder(TestDriveOrder order) {
        repository.save(mapper.toOrderDao(order));
    }

    @Override
    public void updateOrder(TestDriveOrder order) {
        repository.save(mapper.toOrderDao(order));
    }
}
