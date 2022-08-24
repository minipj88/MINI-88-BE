package com.ujutechnology.api8.biz.repository;

import com.ujutechnology.api8.biz.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kei
 * @since 2022-08-24
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
