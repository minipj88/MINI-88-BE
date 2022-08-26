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

/**
 * @author kei
 * @since 2022-08-26
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Long memberId;
    @Column(nullable = false)
    Long productId;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    LocalDateTime createdDate;
    @LastModifiedDate
    @Column(nullable = false)
    LocalDateTime modifiedDate;

    @Builder
    public Reservation(Long id, Long memberId, Long productId, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.memberId = memberId;
        this.productId = productId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
