package com.ujutechnology.api8.api.dto;

import lombok.Data;

/**
 * @author kei
 * @since 2022-09-05
 */
@Data
public class ModifyMemberDto {
    private String name;
    private String profilePhoto;
    private String job;
    private int age;
}
