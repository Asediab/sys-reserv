package com.sys.reservation.service;

import com.sys.reservation.dto.ReservationDTO;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getAllByEstablishmentId(Long establishmentId);
    List<ReservationDTO> getAllByUserId(Long userId);
    ReservationDTO createReservation(ReservationDTO reservationDTO, int limitationOfReservation);
    void deleteReservation(Long id);
    void validateReservation(String validationNumber);
    boolean existsByUserAndEstablAndStartOfReservation(Long userId, Long establishmentId, Date startOfReservation);
    boolean existByValidNumb(String validationNumber);
}
