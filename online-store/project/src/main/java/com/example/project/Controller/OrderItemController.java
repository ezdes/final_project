package com.example.project.Controller;

import com.example.project.Entity.OrderItem;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Service.OrderItemService;
import com.example.project.Service.OrderService;
import com.example.project.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable Long id) throws ResourceNotFoundException {
        return orderItemService.getOrderItem(id);
    }

    @PutMapping("/{id}")
    public OrderItem updateOrderItemById(@PathVariable Long id, @RequestBody OrderItem orderItem) throws ResourceNotFoundException {
        return orderItemService.updateOrderItemById(id, orderItem);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable Long id) {
        orderItemService.deleteOrderItemById(id);
    }

    @PostMapping
    public OrderItem createOrder(@RequestBody OrderItem orderItem) throws ResourceNotFoundException {
        return orderItemService.createOrderItem(orderItem);
    }
}
