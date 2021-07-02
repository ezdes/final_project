package com.example.project.Service;

import com.example.project.Entity.Card;
import com.example.project.Entity.Payment;
import com.example.project.Enum.PaymentStatus;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CardService cardService;

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
        Card card = cardService.getCard(payment.getCard().getId());
        if (card.getBalance() < payment.getTotal()) {
            payment.setPaymentStatus(PaymentStatus.FAILED);
        }

        else {
            card.setBalance(card.getBalance() - payment.getTotal());
            payment.setPaymentStatus(PaymentStatus.OK);
        }

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
//                    newPayment.setPaymentStatus(payment.getPaymentStatus());
//                    newPayment.setDeliveryType(payment.getDeliveryType());
//                    newPayment.setCard(payment.getCard());
//                    newPayment.setDelivery(payment.getDelivery());
//                    newPayment.setOrder(payment.getOrder());
                    return paymentRepository.save(newPayment);
                }).orElseThrow(() -> new ResourceNotFoundException("Could not find payment with id ", id));
    }
}
