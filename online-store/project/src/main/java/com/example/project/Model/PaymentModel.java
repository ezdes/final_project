package com.example.project.Model;

import com.example.project.Entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentModel {
    String city;
    String region;
    String street;
    String houseNumber;
    Long cardNumber;
    Integer cardCVC;
    String cardExpirationDate;
    Double total;
    User user;
}
