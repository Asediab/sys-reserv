package com.sys.reservation.dao;

import com.sys.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationDAO extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByEstablishmentId(Long establishmentId);

    List<Reservation> findAllByUserId(Long userId);

    Reservation findByValidateNumber(String validationNumber);

    List<Reservation> findAllByBeginningAndNumberOfHours(int beginning, int numberOfHours);

    Long countByBeginningAndNumberOfHours(int beginning, int numberOfHours);

    Long countByBeginning(int beginning);

    boolean existsByValidateNumber(String validationNumber);

    boolean existsByUserIdAndEstablishmentIdAndBeginning(Long userId, Long establishmentId, int beginning);
}
