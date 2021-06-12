package com.example.project.Entity;

import com.example.project.Enum.RegionCost;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "location")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Location extends BaseEntity{

    @Column(name = "postal_code", nullable = false)
    String postalCode;

    @Column(name = "city", nullable = false)
    String city;

    @Enumerated(EnumType.STRING)
    @Column(name = "region", nullable = false)
    RegionCost region;

    @Column(name = "country", nullable = false)
    String country;
}
