package com.raphael.itoosabi.data.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Address {

    @Id
    @GeneratedValue
    private Long addressId;

    @Column(name = "house_number")
    private Integer houseNumber;

    private String city;
    private String street;
    private String state;
    private String country;

    @Column(name = "land_mark")
    private String landmark;

    private String zipCode;

    @OneToOne
    private User user;


}
