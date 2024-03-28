////package com.javatpoint.controller;
////
////public class MemberController {
////}
//package com.javatpoint.controller;
//
//import com.javatpoint.dto.MemberDTO;
//import com.javatpoint.model.Corona;
//import com.javatpoint.model.Member;
//import com.javatpoint.model.Vaccine;
//import com.javatpoint.service.CoronaRepository;
//import com.javatpoint.service.MapStructMapper;
//import com.javatpoint.service.MemberRepository;
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
//@RequestMapping("/api/member")
//public class MemberController {
//
//    private MemberRepository memberRepository;
//    private VaccineRepository vaccineRepository;
//    private CoronaRepository coronaRepository;
//    private MapStructMapper mapper;
//
//    private static String UPLOAD_DIRECTORY=System.getProperty("user.dir") +"\\images\\";
//
//
//    public MemberController(MemberRepository memberRepository, VaccineRepository vaccineRepository, CoronaRepository coronaRepository, MapStructMapper mapper) {
//        this.memberRepository = memberRepository;
//        this.vaccineRepository = vaccineRepository;
//        this.coronaRepository = coronaRepository;
//        this.mapper = mapper;
//    }
//
//    @Autowired
//
//
//
//
//    //    function that return all members with image
//    @GetMapping("/getAllMembers")
//    public ResponseEntity<List<MemberDTO>> getAllMembersDto()
//    {
//        try{
//            List<Member> members=new ArrayList<>();
//            memberRepository.findAll().forEach((e->members.add(e)));
//            return new ResponseEntity<>(mapper.membersToDto(members), HttpStatus.OK);
//        }catch(Exception e){
//            System.out.println(e);
//            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
////    //function that upload a member
////    @PostMapping("/uploadMember")
////    public ResponseEntity<Member> uploadMember(@RequestPart ("image") MultipartFile file,
////                                               @RequestPart ("member") Member m)
////    {
////        try{
////            String filePath=UPLOAD_DIRECTORY+file.getOriginalFilename();
////            Path filename= Paths.get(filePath);
////            Files.write(filename,file.getBytes());
////            m.setImage(filePath);
////            Member newMember=memberRepository.save(m);
////            List<Vaccine> vaccines=m.getCorona().getVaccineList();
////            Corona corona=m.getCorona();
////            for (int i = 0;  vaccines.get(i)!=null; i++) {
////                vaccines.get(i).setCorona(corona);
////                Vaccine vaccine=vaccineRepository.save(vaccines.get(i));
////            }
////
////
////
////
////            return new ResponseEntity<>(newMember,HttpStatus.CREATED);
////
////        }
////        catch (Exception e){
////            System.out.println(e);
////            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
//
//        //function that update member by id
//    @PutMapping("/updateMember/{id}")
//    public ResponseEntity<MemberDTO> updateMember(@PathVariable long id,
//                                                  @RequestPart ("image") MultipartFile file,
//                                                  @RequestPart ("member") Member member) throws IOException {
//        Member m = memberRepository.findById(id).orElse(null);
//
//        try {
//            if (m != null) {
//                m.setId(member.getId());
//                m.setFirstName(member.getFirstName());
//                m.setLastName(member.getLastName());
//                m.setIDNumber(member.getIDNumber());
//                m.setAddress(member.getAddress());
//                m.setDateOfBirth(member.getDateOfBirth());
//                m.setTelephone(member.getTelephone());
//                m.setCellPhone(member.getCellPhone());
//                m.setCorona(member.getCorona());
//
//
//                String filePath = UPLOAD_DIRECTORY + file.getOriginalFilename();
//                Path filename = Paths.get(filePath);
//                Files.write(filename, file.getBytes());
//                m.setImage(filePath);
//
//
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            memberRepository.save(m);
//            return new ResponseEntity<>(mapper.memberToDto(m), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//    @PostMapping("/uploadMember")
//    public ResponseEntity<Member> uploadMember(@RequestPart("image") MultipartFile file,
//                                               @RequestPart("member") Member m) {
//        try {
//            String filePath = UPLOAD_DIRECTORY + file.getOriginalFilename();
//            Path filename = Paths.get(filePath);
//            Files.write(filename, file.getBytes());
//            m.setImage(filePath);
//
//
//            Member newMember = memberRepository.save(m);
//
//            return new ResponseEntity<>(newMember, HttpStatus.CREATED);
//        } catch (IOException e) {
//            System.out.println(e);
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//
//
//
//    //function that delete the user by id
//    @DeleteMapping("/deleteMember/{id}")
//    public ResponseEntity deleteMember(@PathVariable long id){
//        try{
//            memberRepository.deleteById(id);
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//        catch (Exception e){
//
//            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<Member> addMember(@RequestBody Member m){
//        try{
//
//            Member newMember=memberRepository.save(m);
//            System.out.println("--------------------------");
//            return new ResponseEntity(newMember, HttpStatus.CREATED);
//        }
//        catch (Exception e){
//            System.out.println(e);
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//    @PostMapping("/uploadMemberwithoutimg")
//    public ResponseEntity<Member> uploadMember222(@RequestBody Member m)
//    {
//        try{
//
//            Member newMember=memberRepository.save(m);
//            return new ResponseEntity<>(newMember,HttpStatus.CREATED);
//
//        }
//        catch (Exception e){
//            System.out.println(e);
//            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/updateMemberWithout/{id}")
//    public ResponseEntity<MemberDTO> updateMemberWithOut(@PathVariable long id,
//                                                  @RequestBody Member member) throws IOException {
//        Member m = memberRepository.findById(id).orElse(null);
//
//        try {
//            if (m != null) {
//                m.setId(member.getId());
//                m.setFirstName(member.getFirstName());
//                m.setLastName(member.getLastName());
//                m.setIDNumber(member.getIDNumber());
//                m.setAddress(member.getAddress());
//                m.setDateOfBirth(member.getDateOfBirth());
//                m.setTelephone(member.getTelephone());
//                m.setCellPhone(member.getCellPhone());
//                m.setCorona(member.getCorona());
//
////
////                String filePath = UPLOAD_DIRECTORY + file.getOriginalFilename();
////                Path filename = Paths.get(filePath);
////                Files.write(filename, file.getBytes());
////                m.setImage(filePath);
//
//
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            memberRepository.save(m);
//            return new ResponseEntity<>(mapper.memberToDto(m), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//}
//package com.javatpoint.controller;
//
//public class MemberController {
//}
package com.javatpoint.controller;

import com.javatpoint.dto.MemberDTO;
import com.javatpoint.model.Corona;
import com.javatpoint.model.Member;
import com.javatpoint.model.Vaccine;
import com.javatpoint.service.CoronaRepository;
import com.javatpoint.service.MapStructMapper;
import com.javatpoint.service.MemberRepository;
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
@RequestMapping("/api/member")
public class MemberController {

    private MemberRepository memberRepository;
    private VaccineRepository vaccineRepository;
    private CoronaRepository coronaRepository;
    private MapStructMapper mapper;

    private static String UPLOAD_DIRECTORY=System.getProperty("user.dir") +"\\images\\";

    @Autowired
    public MemberController(MemberRepository memberRepository, VaccineRepository vaccineRepository, CoronaRepository coronaRepository, MapStructMapper mapper) {
        this.memberRepository = memberRepository;
        this.vaccineRepository = vaccineRepository;
        this.coronaRepository = coronaRepository;
        this.mapper = mapper;
    }






    //    function that return all members with image
    @GetMapping("/getAllMembers")
    public ResponseEntity<List<MemberDTO>> getAllMembersDto()
    {
        try{
            List<Member> members=new ArrayList<>();
            memberRepository.findAll().forEach((e->members.add(e)));
            return new ResponseEntity<>(mapper.membersToDto(members), HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//



    //function that upload a new member with image
    @PostMapping("/uploadMember")
    public ResponseEntity<Member> uploadMember(@RequestPart("image") MultipartFile file,
                                            @RequestPart("member") Member m) {
        Member member=memberRepository.findByIdentity(m.getIdentity());
        if(member!=null)
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        try {
            String filePath = UPLOAD_DIRECTORY + file.getOriginalFilename();
            Path filename = Paths.get(filePath);
            Files.write(filename, file.getBytes());
            m.setImage(filePath);


            Member newMember = memberRepository.save(m);

            return new ResponseEntity<>(newMember, HttpStatus.CREATED);
        } catch (IOException e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






 //function that upload a new member without image  (not used)
    @PostMapping("/uploadMemberwithoutimg")
    public ResponseEntity<Member> uploadMember222( @RequestBody Member m)
    {

        try{
            Member member=memberRepository.findByIdentity(m.getIdentity());
            if(member!=null)
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            Member newMember=memberRepository.save(m);
            return new ResponseEntity<>(newMember,HttpStatus.CREATED);

        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //function that update member by id
    @PutMapping("/updateMember/{id}")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable long id,
                                                  @RequestPart ("image") MultipartFile file,
                                                   @RequestPart ("member") Member member) throws IOException {

        //if the member is updated to a exist member
        Member existMember=memberRepository.findByIdentityAndIdIsNot(member.getIdentity(),id);
        if(existMember!=null)
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        Member m = memberRepository.findById(id).orElse(null);

        try {
            if (m != null) {
                m.setId(member.getId());
                m.setFirstName(member.getFirstName());
                m.setLastName(member.getLastName());
                m.setIdentity(member.getIdentity());
                m.setAddress(member.getAddress());
                m.setDateOfBirth(member.getDateOfBirth());
                m.setTelephone(member.getTelephone());
                m.setCellPhone(member.getCellPhone());
                m.setCorona(member.getCorona());


                String filePath = UPLOAD_DIRECTORY + file.getOriginalFilename();
                Path filename = Paths.get(filePath);
                Files.write(filename, file.getBytes());
                m.setImage(filePath);


            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            memberRepository.save(m);
            return new ResponseEntity<>(mapper.memberToDto(m), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //function that upadte member without image by ID
    @PutMapping("/updateMemberWithout/{id}")
    public ResponseEntity<MemberDTO> updateMemberWithOut(@PathVariable long id,
                                                        @RequestBody Member member) throws IOException {
        //if the member is updated to a exist member
        Member existMember=memberRepository.findByIdentityAndIdIsNot(member.getIdentity(),id);
        if(existMember!=null)
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

        Member m = memberRepository.findById(id).orElse(null);

        try {
            if (m != null) {
                m.setId(member.getId());
                m.setFirstName(member.getFirstName());
                m.setLastName(member.getLastName());
                m.setIdentity(member.getIdentity());
                m.setAddress(member.getAddress());
                m.setDateOfBirth(member.getDateOfBirth());
                m.setTelephone(member.getTelephone());
                m.setCellPhone(member.getCellPhone());
                m.setCorona(member.getCorona());

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            memberRepository.save(m);
            return new ResponseEntity<>(mapper.memberToDto(m), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //function that delete the user by id
    @DeleteMapping("/deleteMember/{id}")
    public ResponseEntity deleteMember(@PathVariable long id){
        try{
            memberRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){

            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
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
