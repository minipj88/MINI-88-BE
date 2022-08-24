package com.ujutechnology.api8.biz.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author kei
 * @since 2022-08-24
 */
@Entity
@Data
public class Member {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
