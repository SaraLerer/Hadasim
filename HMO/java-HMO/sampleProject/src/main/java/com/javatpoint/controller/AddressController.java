//package com.javatpoint.controller;
//
//public class AddressController {
//}
package com.javatpoint.controller;

import com.javatpoint.dto.MemberDTO;
import com.javatpoint.model.Address;
import com.javatpoint.model.Member;
import com.javatpoint.service.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*") // Use http instead of https
@RestController
@RequestMapping("/api/address")
public class AddressController {

    private AddressRepository addressRepository;

    @Autowired
    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    //function that return all the users
    @GetMapping("/getAll")
    public ResponseEntity<List<Address>> get()
    {
        try{
            List<Address> addressList=new ArrayList<>();
            addressRepository.findAll().forEach((e->addressList.add(e)));
            return new ResponseEntity<>(addressList, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //function that return the user by id
    @GetMapping("/getAddress/{id}")
    public  ResponseEntity<Address> getMemberDto(@PathVariable Long id) throws IOException {
        Address address=addressRepository.findById(id).orElse(null);
        if(address!=null)
        {
            return new ResponseEntity<>(address,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //function that craete new user
    @PostMapping("/create")
    public ResponseEntity<Address> createAddress( @RequestBody Address address) {
        try {
            Address createdAddress = addressRepository.save(address);
            return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //function that upadte the user by id
    @PutMapping("/update/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        Address existingAddress = addressRepository.findById(id).orElse(null);
        if (existingAddress != null) {
            existingAddress.setStreet(address.getStreet());
            existingAddress.setCity(address.getCity());
            existingAddress.setHouseNumber(address.getHouseNumber());
            Address updatedAddress = addressRepository.save(existingAddress);
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //function that delete the user by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAddress(@PathVariable Long id) {
        try {
            addressRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //function that catch the error of the validtion
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
