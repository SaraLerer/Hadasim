//package com.javatpoint.model;
//
//public class Corona {
//}
package com.javatpoint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Corona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy="corona",cascade = CascadeType.ALL)
    private List<Vaccine> vaccineList;
    @Past(message = "the datePositiveResult is not past")
    private LocalDate datePositiveResult;
    @Past(message = "the recoveryDate is not past")
    private LocalDate recoveryDate;

//    @OneToOne(mappedBy = "corona", cascade = CascadeType.ALL)
//    private Member member;

    public Corona() {
    }

    public Corona(Long id, List<Vaccine> vaccineList, LocalDate datePositiveResult, LocalDate recoveryDate) {
        this.id = id;
        this.vaccineList = vaccineList;
        this.datePositiveResult = datePositiveResult;
        this.recoveryDate = recoveryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Vaccine> getVaccineList() {
        return vaccineList;
    }

    public void setVaccineList(List<Vaccine> vaccineList) {
        this.vaccineList = vaccineList;
    }

    public LocalDate getDatePositiveResult() {
        return datePositiveResult;
    }

    public void setDatePositiveResult(LocalDate datePositiveResult) {
        this.datePositiveResult = datePositiveResult;
    }

    public LocalDate getRecoveryDate() {
        return recoveryDate;
    }

    public void setRecoveryDate(LocalDate recoveryDate) {
        this.recoveryDate = recoveryDate;
    }
}
