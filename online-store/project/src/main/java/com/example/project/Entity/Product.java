package com.example.project.Entity;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity {

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "price", nullable = false)
    Double price;

    @Column(name = "weight", nullable = false)
    Double weight;

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    Image image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
