package shoppingcart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shoppingcart.dto.OrderDetailDto;
import shoppingcart.dto.OrderDto;
import shoppingcart.service.OrderService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
                                         @RequestBody @Valid final List<OrderDetailDto> orderDetailRequestDtos) {
        long orderId = orderService.addOrder(orderDetailRequestDtos, customerName);
        return ResponseEntity.created(
                URI.create("/api/" + customerName + "/orders/" + orderId)).build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> findOrder(@PathVariable final String customerName,
                                              @PathVariable final Long orderId) {
        final OrderDto orderDto = orderService.findOrderById(customerName, orderId);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> findOrders(@PathVariable final String customerName) {
        final List<OrderDto> orderDtos =
                orderService.findOrdersByCustomerName(customerName);
        return ResponseEntity.ok(orderDtos);
    }
}