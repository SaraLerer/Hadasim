//package com.javatpoint;
//
//public class Address {
//}
package com.javatpoint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "City is mandatory")
    @NotBlank(message = "City is mandatory")
    private String city;
    @NotNull(message = "Street is mandatory")
    @NotBlank(message = "Street is mandatory")
    private String street;
    @NotNull(message = " HouseNumber mandatory")
    @NotBlank(message = "HouseNumber is mandatory")
    private String houseNumber;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Member member;


    public Address() {
    }

    public Address(Long id, String city, String street, String houseNumber) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}
