package com.ujutechnology.api8.biz.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author kei
 * @since 2022-08-24
 */
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false)
    String password;
    String token;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    LocalDateTime createTime;
    @LastModifiedDate
    @Column(nullable = false)
    LocalDateTime updateTime;
    String nickName;
    String profilePhoto;
    String job;
    String age;
    int credit;
    int point;

    @Builder
    public Member(Long id, String email, String password, String token, LocalDateTime createTime, LocalDateTime updateTime, String nickName, String profilePhoto, String job, String age, int credit, int point) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.token = token;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.nickName = nickName;
        this.profilePhoto = profilePhoto;
        this.job = job;
        this.age = age;
        this.credit = credit;
        this.point = point;
    }
}
