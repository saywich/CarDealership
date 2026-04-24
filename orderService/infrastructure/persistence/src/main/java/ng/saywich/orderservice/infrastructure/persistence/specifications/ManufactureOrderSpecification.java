package ng.saywich.orderservice.infrastructure.persistence.specifications;

import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import ng.saywich.orderservice.application.contracts.order.operations.models.OrderFilter;
import ng.saywich.orderservice.infrastructure.persistence.models.purchasing.CustomerDao;
import ng.saywich.orderservice.infrastructure.persistence.models.purchasing.ManagerDao;
import ng.saywich.orderservice.infrastructure.persistence.models.purchasing.ManufactureOrderDao;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ManufactureOrderSpecification implements Specification<ManufactureOrderDao> {
    private OrderFilter filter;

    @Override
    public @Nullable Predicate toPredicate(
        Root<ManufactureOrderDao> root,
        CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder
    ) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.customerId() != null) {
            Join<ManufactureOrderDao, CustomerDao> customerJoin
                = root.join("customer_id", JoinType.INNER);

            predicates.add(criteriaBuilder.equal(
                customerJoin.get("id"),
                filter.customerId()
            ));
        }

        if (filter.managerId() != null) {
            Join<ManufactureOrderDao, ManagerDao> managerJoin
                = root.join("manager_id", JoinType.INNER);

            predicates.add(criteriaBuilder.equal(
                managerJoin.get("id"),
                filter.customerId()
            ));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

