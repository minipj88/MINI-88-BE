package com.ujutechnology.api8.biz.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCTNAME", nullable = false)
    private String productName; // 금융상품명
    private int age;    //20-30, 40, 50-60, all 
    private double rate;
    private String job;
    private String financialCompanyNumber;
    private String financialCompanyName;
    private String productNumber;
    private String joinWay;
    private String cbName;
    private String creditProductTypeName;

    @Builder
    public Product(Long id, String productName, int age, double rate, String job, String financialCompanyNumber, String financialCompanyName, String productNumber, String joinWay, String cbName, String creditProductTypeName) {
        this.id = id;
        this.productName = productName;
        this.age = age;
        this.rate = rate;
        this.job = job;
        this.financialCompanyNumber = financialCompanyNumber;
        this.financialCompanyName = financialCompanyName;
        this.productNumber = productNumber;
        this.joinWay = joinWay;
        this.cbName = cbName;
        this.creditProductTypeName = creditProductTypeName;
    }
}
