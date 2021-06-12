package com.example.project.Service;

import com.example.project.Entity.*;
import com.example.project.Enum.Status;
import com.example.project.Enum.Type;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.OrderRepository;
import com.example.project.Repository.PaymentRepository;
import com.example.project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CardService cardService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPayment(Long id) throws ResourceNotFoundException {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find payment with id ", id));
    }

    @Override
    public Payment createPayment(Payment payment) throws ResourceNotFoundException {

//        User user = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
//        payment.setUser(user);
//        Order order = orderRepository.findOrderByUser_Id(user.getId());
//        payment.setOrder(order);
        Order order = orderService.getOrder(payment.getOrder().getId());
        User user = userRepository.findById(order.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("Could not find user with id"));

        if (!user.getLogin().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            throw new ResourceNotFoundException();
        }
        Delivery delivery = deliveryService.getDelivery(payment.getDelivery().getId());

        if (!delivery.getUser().getId().equals(userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId())) {
            throw new ResourceNotFoundException();
        }
        
        Location location = locationService.getLocation(delivery.getLocation().getId());
        payment.setTotal(order.getTotal() + location.getRegion().getPrice());


        if (payment.getType().equals(Type.DELIVERY)) {
            payment.setCard(null);
            payment.setStatus(Status.OK);
        }
        else if (payment.getType().equals(Type.CASH)) {
            if (payment.getCard() == null) throw new ResourceNotFoundException();

            Card card = cardService.getCard(payment.getCard().getId());


            if (card.getBalance() < payment.getTotal()) {
                payment.setStatus(Status.FAILED);
            }

            else {
                card.setBalance(card.getBalance() - payment.getTotal());
                cardService.updateCardById(card.getId(), card);
                payment.setStatus(Status.OK);
            }
        }

        else throw new ResourceNotFoundException();

        return paymentRepository.save(payment);
    }


    @Override
    public void deletePaymentById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Payment updatePaymentById(Long id, Payment payment) throws ResourceNotFoundException {
        return paymentRepository.findById(id)
                .map(newPayment -> {
                    newPayment.setStatus(payment.getStatus());
                    newPayment.setType(payment.getType());
                    newPayment.setCard(payment.getCard());
                    newPayment.setDelivery(payment.getDelivery());
                    newPayment.setOrder(payment.getOrder());
                    return paymentRepository.save(newPayment);
                }).orElseThrow(() -> new ResourceNotFoundException("Could not find payment with id ", id));
    }
}
