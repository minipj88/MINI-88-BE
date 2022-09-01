package com.ujutechnology.api8.biz.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCTNAME", nullable = false)
    private String productName; // 금융상품명
    @Column(name = "AGE")
    private int age;    //20-30, 40, 50-60, all
    @Column(name = "JOB")
    private String job;
    @Column(name = "COMPANYNAME")
    private String financialCompanyName;
    @Column(name = "PRODUCTNUMBER")
    private String productNumber;
    @Column(name="JOINWAY")
    private String joinWay;
    @Column(name="CBNAME")
    private String cbName;
    @Column(name="PRODUCTTYPE")
    private String productType;
    @Column(name="MINRATE")
    private double minRate;
    @Column(name="MAXRATE")
    private double maxRate;
    @Column(name="MAXAMOUNTS")
    private String maxAmount;

    @Builder

    public Product(Long id, String productName, int age, String job, String financialCompanyName, String productNumber, String joinWay, String cbName, String productType, double minRate, double maxRate, String maxAmount) {
        this.id = id;
        this.productName = productName;
        this.age = age;
        this.job = job;
        this.financialCompanyName = financialCompanyName;
        this.productNumber = productNumber;
        this.joinWay = joinWay;
        this.cbName = cbName;
        this.productType = productType;
        this.minRate = minRate;
        this.maxRate = maxRate;
        this.maxAmount = maxAmount;
    }
}
