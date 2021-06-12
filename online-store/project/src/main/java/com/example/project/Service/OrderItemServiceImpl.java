package com.example.project.Service;

import com.example.project.Entity.*;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.OrderItemRepository;
import com.example.project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem getOrderItem(Long id) throws ResourceNotFoundException {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find order item with id ", id));
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) throws ResourceNotFoundException {
        orderItem.setPrice(productService.getProduct(orderItem.getProduct().getId()).getPrice());
        Order order = orderService.getOrder(orderItem.getOrder().getId());
        User user = userRepository.findById(order.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("Could not find user with id"));

        if (!user.getLogin().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            throw new ResourceNotFoundException();
        }

        order.setQuantity(orderItem.getQuantity() + order.getQuantity());
        order.setTotal(orderItem.getPrice() * orderItem.getQuantity() + order.getTotal());
        orderService.updateOrderById(order.getId(), order);
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItemById(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItem updateOrderItemById(Long id, OrderItem orderItem) throws ResourceNotFoundException {
        return orderItemRepository.findById(id)
                .map(newOrderItem -> {
                    newOrderItem.setProduct(orderItem.getProduct());
                    newOrderItem.setQuantity(orderItem.getQuantity());
                    try {
                        newOrderItem.setPrice(productService.getProduct(orderItem.getProduct().getId()).getPrice());
                    } catch (ResourceNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        List<OrderItem> orderItems = getAllOrderItems();
                        Order order = orderService.getOrder(orderItem.getOrder().getId());
                        Integer quantity = 0;
                        double total = 0.0;
                        for (OrderItem item : orderItems) {
                            if (item.getOrder().getId().equals(orderItem.getOrder().getId())) {
                                quantity += item.getQuantity();
                                total += item.getPrice() * item.getQuantity();
                            }
                        }

                        order.setQuantity(quantity);
                        order.setTotal(total);
                        orderService.updateOrderById(order.getId(), order);
//                        Order order = orderService.getOrder(orderItem.getOrder().getId());
//                        order.setQuantity(orderItem.getQuantity());
//                        order.setTotal(newOrderItem.getPrice() * orderItem.getQuantity());
//                        orderService.updateOrderById(order.getId(), order);
                    } catch (ResourceNotFoundException e) {
                        e.printStackTrace();
                    }
                    return orderItemRepository.save(newOrderItem);
                }).orElseThrow(() -> new ResourceNotFoundException("Could not find order item with id ", id));
    }
}
