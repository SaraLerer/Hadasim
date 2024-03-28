////package com.javatpoint.controller;
////
////public class CoronaController {
////}
//package com.javatpoint.controller;
//
//import com.javatpoint.model.Address;
//import com.javatpoint.model.Corona;
//import com.javatpoint.model.Vaccine;
//import com.javatpoint.service.CoronaRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import sun.dc.pr.PRError;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@CrossOrigin(origins = "*") // Use http instead of https
//@RestController
//@RequestMapping("/api/corona")
//public class CoronaController {
//
//    private CoronaRepository coronaRepository ;
//
//    @Autowired
//    public CoronaController(CoronaRepository coronaRepository) {
//        this.coronaRepository = coronaRepository;
//    }
//
//    @GetMapping("/getAll")
//    public ResponseEntity<List<Corona>> get()
//    {
//        try{
//            List<Corona> coronaList=new ArrayList<>();
//            coronaRepository.findAll().forEach((e->coronaList.add(e)));
//            return new ResponseEntity<>(coronaList, HttpStatus.OK);
//        }catch(Exception e){
//            System.out.println(e);
//            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//    //function that return the user by id
//    @GetMapping("/getCorona/{id}")
//    public  ResponseEntity<Corona> getCorona(@PathVariable Long id) throws IOException {
//        Corona c=coronaRepository.findById(id).orElse(null);
//        if(c!=null)
//        {
//            return new ResponseEntity<>(c,HttpStatus.OK);
//        }
//        else
//        {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//    @PutMapping("/updateCorona/{id}")
//    public ResponseEntity<Corona> updateCorona(@PathVariable Long id, @RequestBody Corona updatedCorona) {
//        try {
//            Corona existingCorona = coronaRepository.findById(id).orElse(null);
//            if (existingCorona != null) {
//                // Update the fields of the existingCorona with the fields from updatedCorona
//                existingCorona.setDatePositiveResult(updatedCorona.getDatePositiveResult());
//                existingCorona.setRecoveryDate(updatedCorona.getRecoveryDate());
//                // If needed, update other fields here
//
//                // Update the list of vaccinations
//                List<Vaccine> updatedVaccineList = updatedCorona.getVaccineList();
//                if (updatedVaccineList != null) {
//                    // Set the corona reference for each vaccine in the updated list
//                    updatedVaccineList.forEach(vaccine -> vaccine.setCorona(existingCorona));
//                    existingCorona.setVaccineList(updatedVaccineList);
//                }
//
//                // Save the updated corona entity
//                Corona savedCorona = coronaRepository.save(existingCorona);
//                return new ResponseEntity<>(savedCorona, HttpStatus.OK);
//            } else {
//                // Corona with the given ID not found
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            // Internal server error occurred
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//
//
//}
//package com.javatpoint.controller;
//
//public class CoronaController {
//}
package com.javatpoint.controller;

import com.javatpoint.model.Address;
import com.javatpoint.model.Corona;
import com.javatpoint.model.Vaccine;
import com.javatpoint.service.CoronaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import sun.dc.pr.PRError;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*") // Use http instead of https
@RestController
@RequestMapping("/api/corona")
public class CoronaController {

    private CoronaRepository coronaRepository ;

    @Autowired
    public CoronaController(CoronaRepository coronaRepository) {
        this.coronaRepository = coronaRepository;
    }

    //function that return all the corona
    @GetMapping("/getAll")
    public ResponseEntity<List<Corona>> get()
    {
        try{
            List<Corona> coronaList=new ArrayList<>();
            coronaRepository.findAll().forEach((e->coronaList.add(e)));
            return new ResponseEntity<>(coronaList, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //function that return the corona by id
    @GetMapping("/getCorona/{id}")
    public  ResponseEntity<Corona> getCorona(@PathVariable Long id) throws IOException {
        Corona c=coronaRepository.findById(id).orElse(null);
        if(c!=null)
        {
            return new ResponseEntity<>(c,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //function that update corona by id
    @PutMapping("/updateCorona/{id}")
    public ResponseEntity<Corona> updateCorona(@PathVariable Long id, @RequestBody Corona updatedCorona) {
        try {
            // Check if updatedCorona is null
            if (updatedCorona == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Corona existingCorona = coronaRepository.findById(id).orElse(null);
            if (existingCorona != null) {
                // Update dates only if provided
                LocalDate updatedPositiveResult = updatedCorona.getDatePositiveResult();
                LocalDate updatedRecoveryDate = updatedCorona.getRecoveryDate();
                if (updatedPositiveResult != null &&
                        (updatedRecoveryDate == null || updatedPositiveResult.compareTo(updatedRecoveryDate) < 0)) {
                    existingCorona.setDatePositiveResult(updatedPositiveResult);
                    existingCorona.setRecoveryDate(updatedRecoveryDate);
                }

                // Update the list of vaccinations
                List<Vaccine> updatedVaccineList = updatedCorona.getVaccineList();
                if (updatedVaccineList == null) {
                    updatedVaccineList = new ArrayList<>(); // Initialize an empty list
                }

                // Check the size of the updatedVaccineList
                if (updatedVaccineList.size() > 4) {
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }

                // Set the corona reference for each vaccine in the updated list
                updatedVaccineList.forEach(vaccine -> vaccine.setCorona(existingCorona));
                existingCorona.setVaccineList(updatedVaccineList);

                Corona savedCorona = coronaRepository.save(existingCorona);
                return new ResponseEntity<>(savedCorona, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("error---------------------------- " + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    //not used
    @PostMapping("/addCorona")
    public ResponseEntity<Corona> addCorona( @RequestBody Corona corona) {
        try {
            Corona newCorona = coronaRepository.save(corona);
            return new ResponseEntity<>(newCorona, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //function that delete the corona by id
    @DeleteMapping("/deleteCorona/{id}")
    public ResponseEntity<HttpStatus> deleteCorona(@PathVariable Long id) {
        try {
            coronaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //function that catch the errors of the validtion
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
