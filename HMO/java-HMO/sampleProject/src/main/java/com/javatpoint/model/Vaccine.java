
package com.javatpoint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;



import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Past(message = "Date is mandatory")
    private LocalDate date;
    @NotBlank(message = "Manufacturer  is mandatory")
    private String manufacturer;




    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="corona_id")
    private Corona corona;

    public Vaccine() {
    }

    public Vaccine(Long id, LocalDate date, String manufacturer, Corona corona) {
        this.id = id;
        this.date = date;
        this.manufacturer = manufacturer;
        this.corona = corona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Corona getCorona() {
        return corona;
    }

    public void setCorona(Corona corona) {
        this.corona = corona;
    }
}

