package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.api.dto.*;
import com.ujutechnology.api8.biz.service.MemberService;
import com.ujutechnology.api8.security.MemberAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

/**
 * @author kei
 * @since 2022-08-24
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public void resister(@RequestBody RegistMemberDto registMemberDto) throws Exception {
        log.info("resister>>> "+registMemberDto.toString());
        memberService.register(registMemberDto);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberDto> login(@RequestBody LoginDto loginDto, @ApiIgnore HttpSession session) throws Exception {
        log.info("login>>> "+ loginDto.toString());
        memberService.login(loginDto);

        MemberAuth auth = new MemberAuth();
        auth.setEmail(loginDto.getEmail());
        memberService.saveToken(auth);
        session.setAttribute("auth", auth);

        return getMember(loginDto.getEmail());
    }

    @PostMapping("/auth")
    public ResponseEntity<MemberDto> auth(@RequestBody MemberAuth auth, @ApiIgnore HttpSession session) throws Exception {
        log.info("auth>>> "+auth.toString());
        memberService.getToken(auth);
        session.setAttribute("auth", auth);
        return getMember(auth.getEmail());
    }

    @GetMapping("/logout")
    public void logout(@ApiIgnore HttpSession session){
        log.info("logout");
        session.invalidate();
    }

    @GetMapping("/member")
    public ResponseEntity<MemberDto> getMember(String email) {
        log.info("getMember>>> "+email);
        MemberDto memberDto = new MemberDto();
        memberService.getMember(email, memberDto);
        return ResponseEntity.ok(memberDto);
    }

    @PutMapping("/member")
    public void modifyMember(@RequestBody ModifyMemberDto memberDto, @ApiIgnore HttpSession session) {
        log.info("modifyMember>>> "+memberDto);
        String email = ((MemberAuth)session.getAttribute("auth")).getEmail();
        memberService.modifyMember(email, memberDto);
    }


    @PutMapping("/credit")
    public ResponseEntity IncCredit(@RequestBody CreditDto creditDto, @ApiIgnore HttpSession session){
        creditDto.setEmail(
                ((MemberAuth)session.getAttribute("auth")).getEmail()
        );
        memberService.incCredit(creditDto);

        return ResponseEntity.ok(creditDto);
    }
}
