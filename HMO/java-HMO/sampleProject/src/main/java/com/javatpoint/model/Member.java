
package com.javatpoint.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import java.time.LocalDate;



@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "FirstName is mandatory")
    @NotBlank(message = "FirstName is mandatory")
    private String firstName;
    @NotNull(message = "LastNme is mandatory")
    @NotBlank(message = "LastNme is mandatory")
    private String lastName;

    @NotBlank(message = "Identity is mandatory")
    @NotNull(message = "Identity is mandatory")
    @Size(min= 9,max = 9,message = "Size of Identity is 9")
    private String identity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @NotNull(message = "DateOfBirth is mandatory")
    @Past(message = "DateOfBirth is not past")
    private LocalDate dateOfBirth;
    @NotNull(message = "telephone is mandatory")
    @NotBlank(message = "telephone is mandatory")
    @Size(min=9 ,max = 9,message ="size of telephone is 9")
    private String telephone;
    @NotNull(message = "cellPhone is mandatory")
    @NotBlank(message = "cellPhone is mandatory")
    @Size(min = 10 ,max = 10,message = "size of cellphone is 9")
    private String cellPhone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "corona_id", referencedColumnName = "id")
    private Corona corona;
    @NotNull
    private String image;

    public Member() {
    }

    public Member(Long id, String firstName, String lastName, String identity, Address address, LocalDate dateOfBirth, String telephone, String cellPhone, Corona corona, String image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identity = identity;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.telephone = telephone;
        this.cellPhone = cellPhone;
        this.corona = corona;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Corona getCorona() {
        return corona;
    }

    public void setCorona(Corona corona) {
        this.corona = corona;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

