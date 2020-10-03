package com.sys.reservation.service.impl;

import com.sys.reservation.dao.ReservationDAO;
import com.sys.reservation.dto.ReservationDTO;
import com.sys.reservation.model.Reservation;
import com.sys.reservation.web.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ReservationServiceImpl_ITests {

    @Autowired
    ReservationDAO reservationDAO;

    @Autowired
    ReservationServiceImpl reservationService = new ReservationServiceImpl(reservationDAO);

    ReservationDTO reserv;
    Reservation res;
    Long id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        reserv = new ReservationDTO();
        reserv.setValidateNumber("fefefe");
        reserv.setEndOfReservation(Calendar.getInstance().getTime());
        reserv.setStartOfReservation(Calendar.getInstance().getTime());
        reserv.setEstablishmentId(1L);
        reserv.setUserFirstName("User");
        reserv.setUserId(1L);
        reserv.setEstablishmentName("name");
        reserv.setValid(false);

        res = new Reservation();
        res.setValidateNumber("fefefe");
        res.setEndOfReservation(Calendar.getInstance().getTime());
        res.setStartOfReservation(Calendar.getInstance().getTime());
        res.setEstablishmentId(1L);
        res.setUserFirstName("User");
        res.setUserId(1L);
        res.setEstablishmentName("name");
        res.setValid(false);
    }


    @Test
    @DisplayName("Test methode createReservation()")
    void testCreateReservation() {
        ReservationDTO result = reservationService.createReservation(reserv, 20);
        id = result.getId();
        Assertions.assertNotNull(result);
    }

    @Test
    @DisplayName("Test methode getListReservationsDispon()")
    void testGetListReservationsDispon() {
        List<ReservationDTO> result = reservationService.getListReservationsDispon(reserv, 20);
        Assertions.assertEquals(4,result.size() - 1);
    }

    @Test
    @DisplayName("Test methode delete() reservation exist")
    void testDeleteCommentExist() {
        assertThatCode(() -> reservationService.deleteReservation(id))
                .doesNotThrowAnyException();
        assertFalse(reservationDAO.existsById(id));
    }

    @Test
    @DisplayName("Test methode delete() reservation not exist")
    void testDeleteCommentNotExist() {
        assertThrows(NotFoundException.class, () -> {
            reservationService.deleteReservation(100000L);
        });
    }
}
