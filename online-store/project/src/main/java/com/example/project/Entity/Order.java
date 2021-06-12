package com.example.project.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @Column(name = "total")
    Double total;

    @Column(name = "quantity")
    Integer quantity;


//    @OneToOne
//    @JoinColumn(name = "payment_id", referencedColumnName = "id")
//    Payment payment;
//
//    @OneToOne
//    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
//    Delivery delivery;
}
