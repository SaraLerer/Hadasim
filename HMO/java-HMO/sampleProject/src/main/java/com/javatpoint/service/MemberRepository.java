//package com.javatpoint.service;
//
//public interface MemberRepository {
//}
package com.javatpoint.service;

import com.javatpoint.dto.MemberDTO;
import com.javatpoint.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface MemberRepository extends JpaRepository<Member,Long> {
Member findByIdentity(String identity);
    Member findByIdentityAndIdIsNot(String identity, Long id);}
