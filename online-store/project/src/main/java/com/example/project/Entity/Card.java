package com.example.project.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "card")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card extends BaseEntity {

    @Column(name = "number", nullable = false, unique = true)
    Integer number;

    @Column(name = "cvc", nullable = false)
    Integer cvc;

    @Column(name = "balance")
    Double balance;

    @Column(name = "expiration_date")
    String expirationDate;

}
