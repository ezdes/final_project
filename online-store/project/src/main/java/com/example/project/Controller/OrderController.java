package com.example.project.Controller;

import com.example.project.Entity.Order;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) throws ResourceNotFoundException {
        return orderService.getOrder(id);
    }

    @PutMapping("/{id}")
    public Order updateOrderById(@PathVariable Long id, @RequestBody Order order) throws ResourceNotFoundException {
        return orderService.updateOrderById(id, order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }

//    @PostMapping
//    public Order createOrder(@RequestBody Order order) {
//        return orderService.createOrder(order);
//    }

    @PostMapping
    public Order createOrder() {
        return orderService.createOrder();
    }
}
