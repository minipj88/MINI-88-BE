package com.ujutechnology.api8.biz.repository;

import com.ujutechnology.api8.biz.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kei
 * @since 2022-08-26
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
