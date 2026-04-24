package ng.saywich.orderservice.presentation.security.evaluators;

import ng.saywich.orderservice.application.contracts.dtos.purchasing.InStockOrderDto;
import ng.saywich.orderservice.application.contracts.order.IInStockOrderService;
import ng.saywich.orderservice.application.contracts.order.operations.QueryInStockOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class InStockOrderSecurityEvaluator {
    @Autowired
    private IInStockOrderService orderService;

    public boolean customerIsOwner(UUID orderId, Jwt customerJwt) {
        UUID customerId = UUID.fromString(customerJwt.getClaimAsString("sub"));

        List<InStockOrderDto> orderDtoList = orderService.query(new QueryInStockOrder.Request(
            new UUID[]{ orderId }, null
        )).orders();

        if (orderDtoList.isEmpty()) return false;

        InStockOrderDto orderDto = orderDtoList.get(0);

        return (customerId == orderDto.customer().customerId());
    }

    public boolean managerIsResponsibleFor(UUID orderId, Jwt managerJwt) {
        UUID managerId = UUID.fromString(managerJwt.getClaimAsString("sub"));

        List<InStockOrderDto> orderDtoList = orderService.query(new QueryInStockOrder.Request(
            new UUID[]{ orderId }, null
        )).orders();

        if (orderDtoList.isEmpty()) return false;

        InStockOrderDto orderDto = orderDtoList.get(0);

        return (managerId == orderDto.manager().managerId());
    }

    public boolean userIsRelatedTo(UUID orderId, Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getClaimAsString("sub"));

        System.out.println(userId);

        List<InStockOrderDto> orderDtoList = orderService.query(new QueryInStockOrder.Request(
            new UUID[]{ orderId }, null
        )).orders();

        if (orderDtoList.isEmpty()) return false;

        InStockOrderDto orderDto = orderDtoList.get(0);

        return (userId == orderDto.manager().managerId()
            || userId == orderDto.customer().customerId());
    }
}
