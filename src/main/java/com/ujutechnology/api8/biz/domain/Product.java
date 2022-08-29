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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "PRODUCTNAME", nullable = false)
    private String productName; // 금융상품명
    private int age;
    private double rate;
    private String job;
    private String financialCompanyNumber;
    private String financialCompanyName;
    private String productNumber;
    private String joinWay;
    private String cbName;
    private String creditProductTypeName;

    @Builder
    public Product(String financialCompanyNumber, String financialCompanyName, String productNumber, String productName, String cbName, String creditProductTypeName, String joinWay, Integer age, Double rate, String job){
        this.financialCompanyName = financialCompanyName;
        this.productNumber = productNumber;
        this.productName = productName;
        this.cbName = cbName;
        this.joinWay = joinWay;
        this.age = age;
        this.rate = rate;
        this.job = job;
        this.creditProductTypeName = creditProductTypeName;

    }

}
