package com.ujutechnology.api8.biz.service;

import com.ujutechnology.api8.biz.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author kei
 * @since 2022-08-26
 */
@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
}
