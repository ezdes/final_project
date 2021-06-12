package com.example.project.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Image extends BaseEntity{

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "format", nullable = false)
    String format;

    @Column(name = "url", nullable = false)
    String url;
}
