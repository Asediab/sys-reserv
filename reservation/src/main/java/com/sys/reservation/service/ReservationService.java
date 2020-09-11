package com.sys.reservation.service;

import com.sys.reservation.dto.ReservationDTO;

import java.util.LinkedHashMap;
import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getAllByEstablishmentId(Long establishmentId);
    List<ReservationDTO> getAllByUserId(Long userId);
    ReservationDTO createReservation(ReservationDTO reservationDTO, int limitationOfReservation);
    void deleteReservation(ReservationDTO reservationDTO);
    void validateReservation(String validationNumber);
    LinkedHashMap<String, Long> getAvailableTimeMorningForEstablishment(Long establishmentId);
    LinkedHashMap<String, Long> getAvailableTimeDayForEstablishment(Long establishmentId);
    LinkedHashMap<String, Long> getAvailableTimeEveningForEstablishment(Long establishmentId);
    boolean existsByUserAndEstablAndBegins(Long userId, Long establishmentId, int beginning);
    boolean existByValidNumb(String validationNumber);
}
