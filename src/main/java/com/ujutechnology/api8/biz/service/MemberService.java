package com.ujutechnology.api8.biz.service;

import com.ujutechnology.api8.api.controller.dto.RegistMemberDto;
import com.ujutechnology.api8.biz.domain.Member;
import com.ujutechnology.api8.biz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void regist(RegistMemberDto registMemberDto) {
        Optional<Member> memberOpt = memberRepository.findByEmail(registMemberDto.getEmail());
        if(memberOpt.isEmpty()){
            Member member = Member.builder()
                    .email(registMemberDto.getEmail())
                    .password(registMemberDto.getPassword())
                    .nickName(registMemberDto.getNickName())
                    .profilePhoto(registMemberDto.getProfilePhoto())
                    .age(registMemberDto.getAge())
                    .job(registMemberDto.getJob())
                    .credit(500)
                    .build();
            memberRepository.save(member);
        }
    }
}
