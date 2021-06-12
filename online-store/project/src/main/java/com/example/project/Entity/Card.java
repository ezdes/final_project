package com.example.project.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.function.Function;

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

    @Column(name = "ccv", nullable = false)
    Integer ccv;

    @Column(name = "balance")
    Double balance;

}
