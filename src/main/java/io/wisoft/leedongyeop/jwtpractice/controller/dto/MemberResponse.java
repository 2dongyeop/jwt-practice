package io.wisoft.leedongyeop.jwtpractice.controller.dto;

import io.wisoft.leedongyeop.jwtpractice.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {
    private String name;
    public static MemberResponse of(Member member) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.name = member.getName();
        return memberResponse;
    }
}
