package io.wisoft.leedongyeop.jwtpractice.repository;

import io.wisoft.leedongyeop.jwtpractice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(final String name);
}
