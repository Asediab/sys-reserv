package com.sys.reservation.dao;

import com.sys.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationDAO extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByEstablishmentIdAndActiveIsTrue(Long establishmentId);

    List<Reservation> findAllByUserIdAndActiveIsTrue(Long userId);

    Reservation findByValidateNumber(String validationNumber);

    Long countReservationsByEstablishmentIdAndStartOfReservationGreaterThanEqualAndEndOfReservationEqualsAndActiveIsTrue(Long establishmentId, Date startOfReservation, Date endOfReservation);

    boolean existsByValidateNumberAndActiveIsTrue(String validationNumber);

    boolean existsByUserIdAndEstablishmentIdAndStartOfReservationAndActiveIsTrue(Long userId, Long establishmentId, Date startOfReservation);

    List<Reservation> findAllByStartOfReservationLessThanEqualAndValidIsFalseAndActiveIsTrue(Date date);

    List<Reservation> findAllByEndOfReservationLessThanAndActiveIsTrue(Date date);

    List<Reservation> findAllByEstablishmentIdAndActiveIsFalse(Long establishmentId);
}
