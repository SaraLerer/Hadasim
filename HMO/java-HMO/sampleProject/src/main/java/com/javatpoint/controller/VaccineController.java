////package com.javatpoint.controller;
////
////public class VaccineController {
////}
//package com.javatpoint.controller;
//
//import com.javatpoint.model.Address;
//import com.javatpoint.model.Corona;
//import com.javatpoint.model.Member;
//import com.javatpoint.model.Vaccine;
//import com.javatpoint.service.CoronaRepository;
//import com.javatpoint.service.VaccineRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//@CrossOrigin(origins = "*") // Use http instead of https
//@RestController
//@RequestMapping("/api/vaccine")
//public class VaccineController {
//
//    private VaccineRepository vaccineRepository;
//    private CoronaRepository coronaRepository;
//
//    public VaccineController(VaccineRepository vaccineRepository, CoronaRepository coronaRepository) {
//        this.vaccineRepository = vaccineRepository;
//        this.coronaRepository = coronaRepository;
//    }
//
//    @Autowired
//
//    @GetMapping("/getAll")
//    public ResponseEntity<List<Vaccine>> get()
//    {
//        try{
//            List<Vaccine> vaccineList=new ArrayList<>();
//            vaccineRepository.findAll().forEach((e->vaccineList.add(e)));
//            return new ResponseEntity<>(vaccineList, HttpStatus.OK);
//        }catch(Exception e){
//            System.out.println(e);
//            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    //function that return the user by id
//    @GetMapping("/getVaccine/{id}")
//    public  ResponseEntity<Vaccine> getVaccine(@PathVariable Long id) throws IOException {
//        Vaccine v=vaccineRepository.findById(id).orElse(null);
//        if(v!=null)
//        {
//            return new ResponseEntity<>(v,HttpStatus.OK);
//        }
//        else
//        {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    //function that upload a member
//    @PostMapping("/addVaccine/{corona_id}")
//    public ResponseEntity<Vaccine> addVaccine(@PathVariable long corona_id ,
//                                              @RequestBody Vaccine v)
//    {
//        System.out.println("corona_id!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!    "+corona_id);
//        try{
//            Corona corona=coronaRepository.findById(corona_id).orElse(null);
//            if(corona==null)
//            {
//                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
//            }
//
//            List<Vaccine>vaccineList=vaccineRepository.findAllByCoronaId(corona_id);
//            if(vaccineList.size()>4)
//            {
//                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
//            }
//            else
//            {
//                v.setCorona(corona);
//                Vaccine newVaccine=vaccineRepository.save(v);
//                return new ResponseEntity<>(newVaccine,HttpStatus.CREATED);
//            }
//
//
//        }
//        catch (Exception e){
//            System.out.println(e);
//            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
//
//        }
//    }
//
//
//
//}
//package com.javatpoint.controller;
//
//public class VaccineController {
//}
package com.javatpoint.controller;

import com.javatpoint.model.Address;
import com.javatpoint.model.Corona;
import com.javatpoint.model.Member;
import com.javatpoint.model.Vaccine;
import com.javatpoint.service.CoronaRepository;
import com.javatpoint.service.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*") // Use http instead of https
@RestController
@RequestMapping("/api/vaccine")
public class VaccineController {

    private VaccineRepository vaccineRepository;
    private CoronaRepository coronaRepository;

    @Autowired
    public VaccineController(VaccineRepository vaccineRepository, CoronaRepository coronaRepository) {
        this.vaccineRepository = vaccineRepository;
        this.coronaRepository = coronaRepository;
    }


   //function that return all the vaccines
    @GetMapping("/getAll")
    public ResponseEntity<List<Vaccine>> get()
    {
        try{
            List<Vaccine> vaccineList=new ArrayList<>();
            vaccineRepository.findAll().forEach((e->vaccineList.add(e)));
            return new ResponseEntity<>(vaccineList, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //function that return the vaccine by id
    @GetMapping("/getVaccine/{id}")
    public  ResponseEntity<Vaccine> getVaccine(@PathVariable Long id) throws IOException {
        Vaccine v=vaccineRepository.findById(id).orElse(null);
        if(v!=null)
        {
            return new ResponseEntity<>(v,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //function that upload a vaccine by corona_id
    @PostMapping("/addVaccine/{corona_id}")
    public ResponseEntity<Vaccine> addVaccine(@PathVariable long corona_id ,
                                              @RequestBody Vaccine v)
    {
       try{
            Corona corona=coronaRepository.findById(corona_id).orElse(null);
            if(corona==null)
            {
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }

            List<Vaccine>vaccineList=vaccineRepository.findAllByCoronaId(corona_id);
            if(vaccineList.size()>4)
            {
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            }
            else
            {
                v.setCorona(corona);
                Vaccine newVaccine=vaccineRepository.save(v);
                return new ResponseEntity<>(newVaccine,HttpStatus.CREATED);
            }


        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    //function that update the vaccine by id
    @PutMapping("/updateVaccine/{id}")
    public ResponseEntity<Vaccine> updateVaccine(@PathVariable Long id, @RequestBody Vaccine vaccine) {
        Vaccine existingVaccine = vaccineRepository.findById(id).orElse(null);
        if (existingVaccine != null) {
            existingVaccine.setDate(vaccine.getDate());
            existingVaccine.setManufacturer(vaccine.getManufacturer());
            Vaccine updatedVaccine = vaccineRepository.save(existingVaccine);
            return new ResponseEntity<>(updatedVaccine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //function that delete the vaccine by id
    @DeleteMapping("/deleteVaccine/{id}")
    public ResponseEntity<HttpStatus> deleteVaccine(@PathVariable Long id) {
        try {
            vaccineRepository.deleteById(id);
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
