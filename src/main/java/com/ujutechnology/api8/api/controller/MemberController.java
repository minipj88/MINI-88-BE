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
    public void resister(@RequestBody RegistMemberDto registMemberDto) throws Exception {
        log.info(registMemberDto.toString());
        memberService.register(registMemberDto);
    }

    @PostMapping("/login")
    public ResultDto<MemberDto> login(@RequestBody LoginDto loginDto) throws Exception {
        log.info(loginDto.toString());

        memberService.login(loginDto);

        return getMember(loginDto.getEmail());
    }

    @GetMapping("/member")
    public ResultDto<MemberDto> getMember(@RequestBody String email) {
        log.info(email);

        MemberDto memberDto = new MemberDto();
        memberService.getMember(email, memberDto);
        ResultDto<MemberDto> resultDto = new ResultDto<>();
        resultDto.setData(memberDto);
        return resultDto;
    }
}
