package com.sys.reservation.service.impl;

import com.sys.reservation.dao.ReservationDAO;
import com.sys.reservation.dto.ReservationDTO;
import com.sys.reservation.web.exception.NotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationServiceImpl_ITests {

    @Autowired
    ReservationDAO reservationDAO;

    @Autowired
    ReservationServiceImpl reservationService = new ReservationServiceImpl(reservationDAO);

    static ReservationDTO reserv;
    static Long id;

    @BeforeAll
    static void setUp() {
        reserv = new ReservationDTO();
        reserv.setValidateNumber("fefefe");
        reserv.setEndOfReservation(Calendar.getInstance().getTime());
        reserv.setStartOfReservation(Calendar.getInstance().getTime());
        reserv.setEstablishmentId(1L);
        reserv.setUserFirstName("User");
        reserv.setUserId(1L);
        reserv.setEstablishmentName("name");
        reserv.setValid(false);
    }


    @Test
    @Order(1)
    @DisplayName("Test methode createReservation()")
    void testCreateReservation() {
        ReservationDTO result = reservationService.createReservation(reserv, 20);
        id = result.getId();
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(2)
    @DisplayName("Test methode getListReservationsDispon()")
    void testGetListReservationsDispon() {
        List<ReservationDTO> result = reservationService.getListReservationsDispon(reserv, 20);
        Assertions.assertEquals(4,result.size() - 1);
    }


    @Test
    @Order(4)
    @DisplayName("Test methode delete() reservation not exist")
    void testDeleteCommentNotExist() {
        assertThrows(NotFoundException.class, () -> {
            reservationService.deleteReservation(100000L);
        });
    }
}
