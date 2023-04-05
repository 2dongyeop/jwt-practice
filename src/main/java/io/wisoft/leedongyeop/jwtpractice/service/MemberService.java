package io.wisoft.leedongyeop.jwtpractice.service;

import io.wisoft.leedongyeop.jwtpractice.controller.dto.LoginRequest;
import io.wisoft.leedongyeop.jwtpractice.domain.Member;
import io.wisoft.leedongyeop.jwtpractice.jwt.JwtTokenProvider;
import io.wisoft.leedongyeop.jwtpractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String createToken(final LoginRequest request) {
        Member member = memberRepository.findByName(request.getName()).orElseThrow(IllegalArgumentException::new);

        //TODO : 비밀번호 확인 등의 유효성 검사 필요

        return jwtTokenProvider.createToken(member.getName());
    }

    public Member findByName(final String name) {
        return memberRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
    }
}
