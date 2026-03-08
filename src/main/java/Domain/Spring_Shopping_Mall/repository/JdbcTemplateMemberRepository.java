package Domain.Spring_Shopping_Mall.repository;

import Domain.Spring_Shopping_Mall.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Member save(Member member) {
        jdbcTemplate.update(
                "INSERT INTO member(name, email, password) VALUES (?, ?, ?)",
                member.getName(),
                member.getEmail(),
                member.getPassword()
        );
        return member;
    }

    @Override
    public Optional<Member> findByEmail(String email) {

        List<Member> result = jdbcTemplate.query(
                "select * from member where email = ?",
                (rs, rowNum) -> {
                    Member member = new Member();
                    member.setId(rs.getLong("id"));
                    member.setName(rs.getString("name"));
                    member.setEmail(rs.getString("email"));
                    member.setPassword(rs.getString("password"));
                    return member;
                },
                email
        );
        return result.stream().findAny();
    }

}
