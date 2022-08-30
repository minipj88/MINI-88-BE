package com.ujutechnology.api8.api.dto;

import com.ujutechnology.api8.biz.domain.Member;
import lombok.Data;

/**
 * @author kei
 * @since 2022-08-30
 */
@Data
public class MemberDto {
    private String email;
    private String nickName;
    private String profilePhoto;
    private String job;
    private int age;
    private int credit;
    private int point;

    public void setMember(Member member) {
        this.email = member.getEmail();
        this.nickName = member.getNickName();
        this.profilePhoto = member.getProfilePhoto();
        this.job = member.getJob();
        this.age = member.getAge();
        this.credit = member.getCredit();
        this.point = member.getPoint();
    }
}
