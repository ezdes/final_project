package com.example.project.Service;

import com.example.project.Entity.Order;
import com.example.project.Entity.User;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.OrderRepository;
import com.example.project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrder(Long id) throws ResourceNotFoundException {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find order with id ", id));
    }

//    @Override
//    public Order createOrder(Order order) {
//        order.setTotal(0.0);
//        order.setQuantity(0);
//        return orderRepository.save(order);
//    }

    @Override
    public Order createOrder() {
        Order order = new Order();
        User user = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        order.setUser(user);
        order.setTotal(0.0);
        order.setQuantity(0);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order updateOrderById(Long id, Order order) throws ResourceNotFoundException {
        return orderRepository.findById(id)
                .map(newOrder -> {
                    newOrder.setUser(order.getUser());
                    return orderRepository.save(newOrder);
                }).orElseThrow(() -> new ResourceNotFoundException("Could not find order with id ", id));
    }
}
