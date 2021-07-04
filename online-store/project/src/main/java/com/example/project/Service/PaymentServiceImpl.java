package com.example.project.Service;

import com.example.project.Entity.Card;
import com.example.project.Entity.Delivery;
import com.example.project.Entity.Payment;
import com.example.project.Enum.PaymentStatus;
import com.example.project.Exception.NotEnoughMoneyException;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Model.PaymentModel;
import com.example.project.Repository.CardRepository;
import com.example.project.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardService cardService;
    @Autowired
    private DeliveryService deliveryService;


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
    public Payment createPayment(PaymentModel paymentModel) throws ResourceNotFoundException, NotEnoughMoneyException {
        Payment payment = new Payment();
        String month = paymentModel.getCardExpirationDate().substring(0, 2);
        String year = paymentModel.getCardExpirationDate().substring(2, 4);
        Card card = cardRepository.findCardByExpirationDateAndCvcAndNumber(month + "/" + year,
                paymentModel.getCardCVC(), paymentModel.getCardNumber());

        if (card == null) throw new ResourceNotFoundException("card not found");

        else {
            Delivery delivery = new Delivery();
            delivery.setCity(paymentModel.getCity());
            delivery.setHouseNumber(paymentModel.getCity());
            delivery.setStreet(paymentModel.getCity());
            delivery.setRegion(paymentModel.getRegion());
            delivery.setUser(paymentModel.getUser());

            payment.setTotal(paymentModel.getTotal());
            payment.setDelivery(delivery);
            payment.setUser(paymentModel.getUser());
            payment.setCard(card);
            deliveryService.createDelivery(delivery);

            if (card.getBalance() < payment.getTotal()) {
                payment.setPaymentStatus(PaymentStatus.FAILED);
                throw new NotEnoughMoneyException("Not enough money in balance");
            }

            else {
                card.setBalance(card.getBalance() - paymentModel.getTotal());
                cardService.updateCardById(card.getId(), card);
                payment.setPaymentStatus(PaymentStatus.OK);
            }
        }

        return paymentRepository.save(payment);
    }


    @Override
    public void deletePaymentById(Long id) {
        paymentRepository.deleteById(id);
    }

//    @Override
//    public Payment updatePaymentById(Long id, Payment payment) throws ResourceNotFoundException {
//        return paymentRepository.findById(id)
//                .map(newPayment -> {
////                    newPayment.setPaymentStatus(payment.getPaymentStatus());
////                    newPayment.setDeliveryType(payment.getDeliveryType());
////                    newPayment.setCard(payment.getCard());
////                    newPayment.setDelivery(payment.getDelivery());
////                    newPayment.setOrder(payment.getOrder());
//                    return paymentRepository.save(newPayment);
//                }).orElseThrow(() -> new ResourceNotFoundException("Could not find payment with id ", id));
//    }
}
