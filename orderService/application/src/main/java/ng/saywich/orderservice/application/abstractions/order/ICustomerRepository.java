package ng.saywich.orderservice.application.abstractions.order;

import ng.saywich.shared.domain.purchasing.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICustomerRepository {
    List<Customer> query(UUID[] customerIds);

    Optional<Customer> queryById(UUID customerId);
}
