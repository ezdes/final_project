package com.example.project.Service;

import com.example.project.Entity.Order;
import com.example.project.Exception.ResourceNotFoundException;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrder(Long id) throws ResourceNotFoundException;
//    Order createOrder(Order order);
    void deleteOrderById(Long id);
    Order updateOrderById(Long id, Order order) throws ResourceNotFoundException;
    Order createOrder();
}
