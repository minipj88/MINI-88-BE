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

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    LocalDateTime modifiedDate;

    @Column(nullable = false)
    private String memberEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="productId", nullable = false)
    private Product product;

    @Builder
    public Cart(Long id, LocalDateTime createdDate, LocalDateTime modifiedDate, String memberEmail, Product product) {
        this.id = id;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.memberEmail = memberEmail;
        this.product = product;
    }
}
