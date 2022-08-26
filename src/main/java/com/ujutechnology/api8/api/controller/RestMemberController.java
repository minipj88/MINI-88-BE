package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.api.controller.dto.LoginDto;
import com.ujutechnology.api8.api.controller.dto.RegistMemberDto;
import com.ujutechnology.api8.api.controller.dto.ResultDto;
import com.ujutechnology.api8.biz.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.IntStream.builder;

/**
 * @author kei
 * @since 2022-08-24
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class RestMemberController {
    private final MemberService memberService;

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = { "", "/" })
    public String hello() {
        return "hello";
    }

    @PostMapping("/regist")
    public ResultDto<String> resist(RegistMemberDto registMemberDto){
        log.debug("member resist >>>"+registMemberDto.toString());
        memberService.regist(registMemberDto);
        ResultDto<String> resultDto = new ResultDto<>();
        resultDto.setResult(true);
        return resultDto;
    }

    @PostMapping("/login")
    public ResultDto<String> login(LoginDto loginDto){
        boolean ret = memberService.login(loginDto);
        ResultDto<String> resultDto = new ResultDto<>();
        if(ret){
            resultDto.setResult(true);
        }
        return resultDto;
    }

}
