package ng.saywich.infrastructure.persistence.specifications;

import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import ng.saywich.application.contracts.order.operations.models.OrderFilter;
import ng.saywich.infrastructure.persistence.models.purchasing.CustomerDao;
import ng.saywich.infrastructure.persistence.models.purchasing.InStockOrderDao;
import ng.saywich.infrastructure.persistence.models.purchasing.ManagerDao;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class InStockOrderSpecification implements Specification<InStockOrderDao> {
    private OrderFilter filter;

    @Override
    public @Nullable Predicate toPredicate(
        Root<InStockOrderDao> root,
        CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder
    ) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.customerId() != null) {
            Join<InStockOrderDao, CustomerDao> customerJoin
                = root.join("customer", JoinType.INNER);

            predicates.add(criteriaBuilder.equal(
                customerJoin.get("id"),
                filter.customerId()
            ));
        }

        if (filter.managerId() != null) {
            Join<InStockOrderDao, ManagerDao> managerJoin
                = root.join("manager", JoinType.INNER);

            predicates.add(criteriaBuilder.equal(
                managerJoin.get("id"),
                filter.managerId()
            ));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
