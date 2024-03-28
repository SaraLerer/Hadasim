////package com.javatpoint.service;
////
////public class MapStructMapper {
////}
//package com.javatpoint.service;
//
//import com.javatpoint.dto.MemberDTO;
//import com.javatpoint.model.Member;
//import org.mapstruct.Mapper;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Base64;
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface MapStructMapper {
//
//    List<MemberDTO>  membersToDto(List<Member> members);
//
//    default MemberDTO memberToDto(Member m) throws IOException {
//
//        MemberDTO memberDTO =new MemberDTO();
//
//        memberDTO.setId(m.getId());
//        memberDTO.setFirstName(m.getFirstName());
//        memberDTO.setLastName(m.getLastName());
//        memberDTO.setIDNumber(m.getIDNumber());
//        memberDTO.setAddress(m.getAddress());
//        memberDTO.setDateOfBirth(m.getDateOfBirth());
//        memberDTO.setTelephone(m.getTelephone());
//        memberDTO.setCellPhone(m.getCellPhone());
//        memberDTO.setCorona(m.getCorona());
//
//        //שומר את הנתיב של התמונה
//        Path filename= Paths.get(m.getImage());
//        //הופך את המערך לביטים של תמונה
//        byte[]byteImage= Files.readAllBytes(filename);
//        memberDTO.setImage(Base64.getEncoder().encodeToString(byteImage));
//
//        return memberDTO;
//
//    }
//
//    default Member dtoToMember(MemberDTO m)
//    {
//        Member member=new Member();
//
//        member.setId(m.getId());
//        member.setFirstName(m.getFirstName());
//        member.setLastName(m.getLastName());
//        member.setIDNumber(m.getIDNumber());
//        member.setAddress(m.getAddress());
//        member.setDateOfBirth(m.getDateOfBirth());
//        member.setTelephone(m.getTelephone());
//        member.setCellPhone(m.getCellPhone());
//        member.setCorona(m.getCorona());
//
//        return member;
//    }
//}

//package com.javatpoint.service;
//
//public class MapStructMapper {
//}
package com.javatpoint.service;

import com.javatpoint.dto.MemberDTO;
import com.javatpoint.model.Member;
import org.mapstruct.Mapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    List<MemberDTO>  membersToDto(List<Member> members);

    default MemberDTO memberToDto(Member m) throws IOException {

        MemberDTO memberDTO =new MemberDTO();

        memberDTO.setId(m.getId());
        memberDTO.setFirstName(m.getFirstName());
        memberDTO.setLastName(m.getLastName());
        memberDTO.setIdentity(m.getIdentity());
        memberDTO.setAddress(m.getAddress());
        memberDTO.setDateOfBirth(m.getDateOfBirth());
        memberDTO.setTelephone(m.getTelephone());
        memberDTO.setCellPhone(m.getCellPhone());
        memberDTO.setCorona(m.getCorona());

        //שומר את הנתיב של התמונה
        Path filename= Paths.get(m.getImage());
        //הופך את המערך לביטים של תמונה
        byte[]byteImage= Files.readAllBytes(filename);
        memberDTO.setImage(Base64.getEncoder().encodeToString(byteImage));

        return memberDTO;

    }

    default Member dtoToMember(MemberDTO m)
    {
        Member member=new Member();

        member.setId(m.getId());
        member.setFirstName(m.getFirstName());
        member.setLastName(m.getLastName());
        member.setIdentity(m.getIdentity());
        member.setAddress(m.getAddress());
        member.setDateOfBirth(m.getDateOfBirth());
        member.setTelephone(m.getTelephone());
        member.setCellPhone(m.getCellPhone());
        member.setCorona(m.getCorona());

        return member;
    }
}

