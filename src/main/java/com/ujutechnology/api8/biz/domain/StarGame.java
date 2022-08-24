package com.ujutechnology.api8.biz.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "STARGAME")
@Data
@NoArgsConstructor
public class StarGame {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}



