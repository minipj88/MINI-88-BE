package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.api.dto.LoginDto;
import com.ujutechnology.api8.api.dto.MemberDto;
import com.ujutechnology.api8.api.dto.RegistMemberDto;
import com.ujutechnology.api8.biz.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MemberDto> login(@RequestBody LoginDto loginDto) throws Exception {
        log.info("login>>> "+loginDto.toString());
        try {
            memberService.login(loginDto);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        return getMember(loginDto.getEmail());
    }

    @GetMapping("/member")
    public ResponseEntity<MemberDto> getMember(String email) {
        log.info("getMember>>> "+email);
        MemberDto memberDto = new MemberDto();
        memberService.getMember(email, memberDto);
        return new ResponseEntity(memberDto, HttpStatus.OK);
    }
}
