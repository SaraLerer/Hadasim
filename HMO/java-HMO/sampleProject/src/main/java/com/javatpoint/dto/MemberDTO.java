////package com.javatpoint.dto;
////
////public class MemberDTO {
////}
//package com.javatpoint.dto;
//
//import com.javatpoint.model.Address;
//import com.javatpoint.model.Corona;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//public class MemberDTO {
//    private Long id;
//    private String firstName;
//    private String lastName;
//    private String IDNumber;
//    private Address address;
//    private LocalDate DateOfBirth;
//    private String telephone;
//    private String cellPhone;
//    private Corona corona;
//    private String image;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getIDNumber() {
//        return IDNumber;
//    }
//
//    public void setIDNumber(String IDNumber) {
//        this.IDNumber = IDNumber;
//    }
//
//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
//
//    public LocalDate getDateOfBirth() {
//        return DateOfBirth;
//    }
//
//    public void setDateOfBirth(LocalDate dateOfBirth) {
//        DateOfBirth = dateOfBirth;
//    }
//
//    public String getTelephone() {
//        return telephone;
//    }
//
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }
//
//    public String getCellPhone() {
//        return cellPhone;
//    }
//
//    public void setCellPhone(String cellPhone) {
//        this.cellPhone = cellPhone;
//    }
//
//    public Corona getCorona() {
//        return corona;
//    }
//
//    public void setCorona(Corona corona) {
//        this.corona = corona;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//}
//package com.javatpoint.dto;
//
//public class MemberDTO {
//}
package com.javatpoint.dto;

import com.javatpoint.model.Address;
import com.javatpoint.model.Corona;

import javax.persistence.*;
import java.time.LocalDate;

public class MemberDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String identity;
    private Address address;
    private LocalDate dateOfBirth;
    private String telephone;
    private String cellPhone;
    private Corona corona;
    private String image;

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
