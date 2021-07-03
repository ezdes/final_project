package com.example.project.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment extends BaseEntity{
    @Column(name = "text", nullable = false)
    String text;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    Product product;
}
