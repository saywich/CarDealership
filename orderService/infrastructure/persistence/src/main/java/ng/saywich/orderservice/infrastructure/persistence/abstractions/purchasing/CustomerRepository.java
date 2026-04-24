package ng.saywich.orderservice.infrastructure.persistence.abstractions.purchasing;

import ng.saywich.orderservice.application.abstractions.order.ICustomerRepository;
import ng.saywich.orderservice.infrastructure.persistence.jparepositories.purchasing.ICustomerDaoRepository;
import ng.saywich.orderservice.infrastructure.persistence.mappers.purchasing.CustomerMapper;
import ng.saywich.orderservice.infrastructure.persistence.models.purchasing.CustomerDao;
import ng.saywich.shared.domain.purchasing.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerRepository implements ICustomerRepository {
    @Autowired
    private ICustomerDaoRepository customerDaoRepository;

    @Autowired
    private CustomerMapper mapper;

    @Override
    public List<Customer> query(UUID[] customerIds) {
        List<CustomerDao> managerDaoList = customerIds.length == 0
            ? customerDaoRepository.findAll()
            : customerDaoRepository.findAllById(List.of(customerIds));

        return managerDaoList.stream()
            .map(mapper::toCustomer)
            .toList();
    }

    @Override
    public Optional<Customer> queryById(UUID customerId) {
        Optional<CustomerDao> customerDao =
            customerDaoRepository.findById(customerId);

        return customerDao.map(dao -> mapper.toCustomer(dao));

    }
}
