package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.api.dto.LoginDto;
import com.ujutechnology.api8.api.dto.RegistMemberDto;
import com.ujutechnology.api8.api.dto.ResultDto;
import com.ujutechnology.api8.biz.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = { "", "/" })
    public String hello() {
        return "hello";
    }

    @PostMapping("/register")
    public void resister(RegistMemberDto registMemberDto){
        memberService.register(registMemberDto);
    }

    @PostMapping("/login")
    public ResultDto<String> login(LoginDto loginDto) throws Exception {
        memberService.login(loginDto);
        ResultDto<String> resultDto = new ResultDto<>();
        return resultDto;
    }

}
