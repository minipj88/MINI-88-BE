package com.ujutechnology.api8.api.controller.dto;

import lombok.Data;

/**
 * @author kei
 * @since 2022-08-25
 */
@Data
public class RegistMemberDto {
    private String email;
    private String password;
    private String nickName;
    private String profilePhoto;
    private String job;
    private int age;
}
