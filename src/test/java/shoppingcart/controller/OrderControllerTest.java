package shoppingcart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import shoppingcart.dto.OrderDetailDto;
import shoppingcart.dto.OrderRequestDto;
import shoppingcart.dto.OrdersDto;
import shoppingcart.service.OrderService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @DisplayName("CREATED와 Location을 반환한다.")
    @Test
    void addOrder() throws Exception {
        // given
        final Long cartId = 1L;
        final int quantity = 5;
        final Long cartId2 = 1L;
        final int quantity2 = 5;
        final String customerName = "pobi";
        final List<OrderRequestDto> requestDtos =
                Arrays.asList(new OrderRequestDto(cartId, quantity), new OrderRequestDto(cartId2, quantity2));

        final Long expectedOrderId = 1L;
        when(orderService.addOrder(any(), eq(customerName)))
                .thenReturn(expectedOrderId);

        // when // then
        mockMvc.perform(post("/api/customers/" + customerName + "/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(requestDtos))
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location",
                        "/api/" + customerName + "/orders/" + expectedOrderId));
    }

    @DisplayName("사용자 이름과 주문 ID를 통해 단일 주문 내역을 조회하면, 단일 주문 내역을 받는다.")
    @Test
    void findOrder() throws Exception {

        // given
        final String customerName = "pobi";
        final Long orderId = 1L;
        final OrdersDto expected = new OrdersDto(orderId,
                Arrays.asList(new OrderDetailDto(2L, 1_000, "banana", "imageUrl", 2)));

        when(orderService.findOrderById(customerName, orderId))
                .thenReturn(expected);

        // when // then
        mockMvc.perform(get("/api/customers/" + customerName + "/orders/" + orderId)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("order_id").value(orderId))
                .andExpect(jsonPath("order_details[0].product_id").value(2L))
                .andExpect(jsonPath("order_details[0].price").value(1_000))
                .andExpect(jsonPath("order_details[0].name").value("banana"))
                .andExpect(jsonPath("order_details[0].image_url").value("imageUrl"))
                .andExpect(jsonPath("order_details[0].quantity").value(2));
    }

    @DisplayName("사용자 이름을 통해 주문 내역 목록을 조회하면, 주문 내역 목록을 받는다.")
    @Test
    void findOrders() throws Exception {
        // given
        final String customerName = "pobi";
        final List<OrdersDto> expected = Arrays.asList(
                new OrdersDto(1L, Arrays.asList(
                        new OrderDetailDto(1L, 1_000, "banana", "imageUrl", 2))),
                new OrdersDto(2L, Arrays.asList(
                        new OrderDetailDto(2L, 2_000, "apple", "imageUrl2", 4)))
        );

        when(orderService.findOrdersByCustomerName(customerName))
                .thenReturn(expected);

        // when // then
        mockMvc.perform(get("/api/customers/" + customerName + "/orders/")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].order_id").value(1L))
                .andExpect(jsonPath("$[0].order_details[0].product_id").value(1L))
                .andExpect(jsonPath("$[0].order_details[0].price").value(1_000))
                .andExpect(jsonPath("$[0].order_details[0].name").value("banana"))
                .andExpect(jsonPath("$[0].order_details[0].image_url").value("imageUrl"))
                .andExpect(jsonPath("$[0].order_details[0].quantity").value(2))

                .andExpect(jsonPath("$[1].order_id").value(2L))
                .andExpect(jsonPath("$[1].order_details[0].product_id").value(2L))
                .andExpect(jsonPath("$[1].order_details[0].price").value(2_000))
                .andExpect(jsonPath("$[1].order_details[0].name").value("apple"))
                .andExpect(jsonPath("$[1].order_details[0].image_url").value("imageUrl2"))
                .andExpect(jsonPath("$[1].order_details[0].quantity").value(4));
    }
}
