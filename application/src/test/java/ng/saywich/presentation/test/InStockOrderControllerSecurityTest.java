package ng.saywich.presentation.test;

import ng.saywich.application.abstractions.order.IInStockOrderRepository;
import ng.saywich.application.contracts.dtos.cars.CarDto;
import ng.saywich.application.contracts.dtos.purchasing.CustomerDto;
import ng.saywich.application.contracts.dtos.purchasing.InStockOrderDto;
import ng.saywich.application.contracts.dtos.purchasing.ManagerDto;
import ng.saywich.application.contracts.mappers.cars.CarModelDtoMapper;
import ng.saywich.application.contracts.order.IInStockOrderService;
import ng.saywich.application.contracts.order.operations.AcceptOrder;
import ng.saywich.application.contracts.order.operations.QueryInStockOrder;
import ng.saywich.domain.purchasing.order.states.OrderState;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class InStockOrderControllerSecurityTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CarModelDtoMapper carModelDtoMapper;

    @MockitoBean
    private IInStockOrderService orderService;

    @MockitoBean
    private IInStockOrderRepository orderRepository;

    @Test
    public void acceptOrder_NoToken_Returns401() throws Exception {
        AcceptOrder.Request request = new AcceptOrder.Request(UUID.randomUUID());

        mockMvc.perform(post("/in_stock_orders/accept")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))
        ).andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void acceptOrder_WithWrongUser_Returns403() throws Exception {
        UUID orderId = UUID.randomUUID();
        UUID managerId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        UUID carId = UUID.randomUUID();

        CarDtoMockRepository carDtoMockRepository = new CarDtoMockRepository(carModelDtoMapper);

        AcceptOrder.Request request = new AcceptOrder.Request(orderId);

        InStockOrderDto mockOrder = new InStockOrderDto(
            carId,
            OrderState.PENDING,
            new ManagerDto(managerId, "mock manager"),
            new CustomerDto(customerId, "mock customer"),
            new CarDto(carId, "mock vin", carDtoMockRepository.getModel())
        );

        Mockito.when(
            orderService.query(
                new QueryInStockOrder.Request(new UUID[]{orderId}, null))
            )
            .thenReturn(new QueryInStockOrder.Response(Arrays.stream(new InStockOrderDto[]{mockOrder}).toList()));

        mockMvc.perform(post("/in_stock_orders/accept")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))
            .with(jwt().jwt(builder -> builder.claim("sub", customerId)))
        ).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void acceptOrder_WithCorrectUser_Returns200() throws Exception {
        UUID orderId = UUID.randomUUID();
        UUID managerId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        UUID carId = UUID.randomUUID();

        CarDtoMockRepository carDtoMockRepository = new CarDtoMockRepository(carModelDtoMapper);

        AcceptOrder.Request request = new AcceptOrder.Request(orderId);
        AcceptOrder.Response response = new AcceptOrder.Response(orderId, OrderState.ACCEPTED);

        InStockOrderDto mockOrder = new InStockOrderDto(
            orderId,
            OrderState.PENDING,
            new ManagerDto(managerId, "mock manager"),
            new CustomerDto(customerId, "mock customer"),
            new CarDto(carId, "mock vin", carDtoMockRepository.getModel())
        );

        Mockito.when(
                orderService.query(
                    new QueryInStockOrder.Request(new UUID[]{ orderId }, null))
            )
            .thenReturn(new QueryInStockOrder.Response(Arrays.stream(new InStockOrderDto[]{ mockOrder }).toList()));

        Mockito.when(orderService.acceptOrder(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/in_stock_orders/accept")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))
            .with(jwt()
                .jwt(builder -> builder.claim("sub", managerId.toString()))
                .authorities(new SimpleGrantedAuthority("ROLE_manager"))
            ))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
