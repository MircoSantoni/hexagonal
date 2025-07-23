package com.hexagonal.hexagonal.infrastructure.adapters.out.jpa;

import com.hexagonal.hexagonal.domain.model.ennums.Gender;
import com.hexagonal.hexagonal.infrastructure.utils.GenderConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "first_name" , nullable = false)
    public String firstName;

    @Column(name = "last_name" , nullable = false)
    public String lastName;

    @Column( nullable = false)

    public String email;

    @Column(name = "gender", nullable = false)
    @Convert(converter = GenderConverter.class)
    public Gender gender;

    @Column(name = "ip_address" , nullable = false)
    public String ipAddress;
}
