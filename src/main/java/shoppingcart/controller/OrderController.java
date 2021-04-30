package shoppingcart.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoppingcart.dto.OrderDetailRequestDto;
import shoppingcart.dto.OrderResponseDto;
import shoppingcart.service.OrderService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("/api/customers/{customerName}/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Void> addOrder(@PathVariable final String customerName,
                                         @RequestBody @Valid final List<OrderDetailRequestDto> orderDetailRequestDtos) {
        long orderId = orderService.addOrder(orderDetailRequestDtos, customerName);
        return ResponseEntity.created(
                URI.create("/api/" + customerName + "/orders/" + orderId)).build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> findOrder(@PathVariable final String customerName,
                                                      @PathVariable final Long orderId) {
        final OrderResponseDto orderResponseDto = orderService.findOrderById(customerName, orderId);
        return ResponseEntity.ok(orderResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findOrders(@PathVariable final String customerName) {
        final List<OrderResponseDto> orderResponseDtos =
                orderService.findOrdersByCustomerName(customerName);
        return ResponseEntity.ok(orderResponseDtos);
    }
}