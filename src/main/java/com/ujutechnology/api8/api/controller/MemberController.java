package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.api.dto.LoginDto;
import com.ujutechnology.api8.api.dto.MemberDto;
import com.ujutechnology.api8.api.dto.RegistMemberDto;
import com.ujutechnology.api8.api.dto.ResultDto;
import com.ujutechnology.api8.biz.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public void resister(@RequestBody RegistMemberDto registMemberDto){
        memberService.register(registMemberDto);
    }

    @PostMapping("/login")
    public ResultDto<String> login(@RequestBody LoginDto loginDto) throws Exception {
        memberService.login(loginDto);
        ResultDto<String> resultDto = new ResultDto<>();
        return resultDto;
    }

    @GetMapping("/member")
    public MemberDto getMember(@RequestBody String email) {
        MemberDto memberDto = new MemberDto();
        memberService.getMember(email, memberDto);
        return memberDto;
    }
}
