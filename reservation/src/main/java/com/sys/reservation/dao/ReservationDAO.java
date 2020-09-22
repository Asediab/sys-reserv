package com.sys.reservation.dao;

import com.sys.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationDAO extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByEstablishmentId(Long establishmentId);

    List<Reservation> findAllByUserId(Long userId);

    Reservation findByValidateNumber(String validationNumber);

    Long countReservationsByEstablishmentIdAndStartOfReservationGreaterThanEqualAndEndOfReservationEquals(Long establishmentId, Date startOfReservation, Date endOfReservation);

    boolean existsByValidateNumber(String validationNumber);

    boolean existsByUserIdAndEstablishmentIdAndStartOfReservation(Long userId, Long establishmentId, Date startOfReservation);
}
