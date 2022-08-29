package com.ujutechnology.api8.biz.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kei
 * @since 2022-08-24
 */
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
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
    LocalDateTime createdDate;
    @LastModifiedDate
    @Column(nullable = false)
    LocalDateTime modifiedDate;
    String nickName;
    String profilePhoto;
    String job;
    int age;
    int credit;
    int point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();

    @Builder
    public Member(Long id, String email, String password, String token, LocalDateTime createdDate, LocalDateTime modifiedDate, String nickName, String profilePhoto, String job, int age, int credit, int point) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.token = token;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.nickName = nickName;
        this.profilePhoto = profilePhoto;
        this.job = job;
        this.age = age;
        this.credit = credit;
        this.point = point;
    }
}
