package Domain.Spring_Shopping_Mall.repository;

import Domain.Spring_Shopping_Mall.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface MemberRepository {
    Member save(Member member);
//    Optional<Member> findByEmail(String email);
}
