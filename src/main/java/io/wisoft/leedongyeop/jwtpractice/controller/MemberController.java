package io.wisoft.leedongyeop.jwtpractice.controller;

import io.wisoft.leedongyeop.jwtpractice.service.MemberService;
import io.wisoft.leedongyeop.jwtpractice.controller.dto.TokenResponse;
import io.wisoft.leedongyeop.jwtpractice.controller.dto.LoginRequest;
import io.wisoft.leedongyeop.jwtpractice.controller.dto.MemberResponse;
import io.wisoft.leedongyeop.jwtpractice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/login")
    public ResponseEntity<TokenResponse> login(
            @RequestBody final LoginRequest request) {

        final String token = memberService.createToken(request);
        return ResponseEntity.ok(new TokenResponse(token, "bearer"));
    }

    @GetMapping("/api/info/{member-name}")
    public ResponseEntity<MemberResponse> getUserFromToken(
            @PathVariable("member-name") final String name) {
        final Member member = memberService.findByName(name);
        return ResponseEntity.ok().body(MemberResponse.of(member));
    }
}
