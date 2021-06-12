package com.example.project.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends BaseEntity implements GrantedAuthority {

    @Column(name = "name", nullable = false)
    String name;

    @Override
    public String getAuthority() {
        return name;
    }

}
