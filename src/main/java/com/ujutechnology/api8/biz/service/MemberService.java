package com.ujutechnology.api8.biz.service;

import com.ujutechnology.api8.api.dto.LoginDto;
import com.ujutechnology.api8.api.dto.MemberDto;
import com.ujutechnology.api8.api.dto.RegistMemberDto;
import com.ujutechnology.api8.biz.domain.Member;
import com.ujutechnology.api8.biz.repository.MemberRepository;
import com.ujutechnology.api8.security.JwtToken;
import com.ujutechnology.api8.security.MemberAuth;
import com.ujutechnology.api8.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.message.AuthException;
import java.util.Optional;

/**
 * @author kei
 * @since 2022-08-24
 */
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public void register(RegistMemberDto registMemberDto) throws Exception  {
        registMemberDto.setPassword(PasswordEncoder.encode(registMemberDto.getPassword()));
        Optional<Member> memberOpt = memberRepository.findByEmail(registMemberDto.getEmail());
        if(memberOpt.isEmpty()){
            Member member = Member.builder()
                    .email(registMemberDto.getEmail())
                    .password(registMemberDto.getPassword())
                    .name(registMemberDto.getName())
                    .role("USER")
                    .profilePhoto(registMemberDto.getProfilePhoto())
                    .age(registMemberDto.getAge())
                    .job(registMemberDto.getJob())
                    .build();
            memberRepository.save(member);
        } else {
            throw new Exception("회원가입 실패");
        }
    }

    public void login(LoginDto loginDto) throws Exception {
        loginDto.setPassword(PasswordEncoder.encode(loginDto.getPassword()));
        memberRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword())
                .orElseThrow(AuthException::new);
    }

    public void getMember(String email, MemberDto memberDto) {
        memberRepository.findByEmail(email).ifPresent(member -> {
            memberDto.setMember(member);
        });
    }

    public void saveToken(MemberAuth auth) {
        auth.setToken(JwtToken.Encode(auth.getEmail()));
        memberRepository.findByEmail(auth.getEmail()).ifPresent(member -> {
            member.setToken(auth.getToken());
            memberRepository.save(member);
        });
    }

    public void getToken(MemberAuth auth) throws Exception {
        memberRepository.findByEmailAndToken(auth.getEmail(), auth.getToken())
                .orElseThrow(AuthException::new);
    }
}
