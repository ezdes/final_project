package com.example.project.Entity;

import com.example.project.Enum.Status;
import com.example.project.Enum.Type;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    Type type;

    @Column(name = "total_price")
    Double total;

//    @ManyToOne
//    @JoinColumn(name = "client_id", referencedColumnName = "id")
//    User user;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    Order order;

    @OneToOne
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    Card card;
}
