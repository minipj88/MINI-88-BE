package com.ujutechnology.api8.biz.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@Data
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PRODUCTNAME", nullable = false)
    private String productName; // 금융상품명

    @Column(name = "PRODUCTRATE", nullable = false)
    private String productRate;

    @Builder
    public Product(String Name, String Rate){
        this.productName = Name;
        this.productRate = Rate;
    }
}
