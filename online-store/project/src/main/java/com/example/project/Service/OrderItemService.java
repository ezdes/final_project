package com.example.project.Service;

import com.example.project.Entity.OrderItem;
import com.example.project.Exception.ResourceNotFoundException;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> getAllOrderItems();
    OrderItem getOrderItem(Long id) throws ResourceNotFoundException;
    OrderItem createOrderItem(OrderItem orderItem) throws ResourceNotFoundException;
    void deleteOrderItemById(Long id);
    OrderItem updateOrderItemById(Long id, OrderItem orderItem) throws ResourceNotFoundException;
}
