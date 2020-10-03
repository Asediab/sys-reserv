package com.sys.reservation.service.impl;

import com.sys.reservation.dao.ReservationDAO;
import com.sys.reservation.dto.ReservationDTO;
import com.sys.reservation.model.Reservation;
import com.sys.reservation.web.exception.LimiteException;
import com.sys.reservation.web.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;

class ReservationServiceImplTest {

    @Mock
    ReservationDAO reservationDAO;
    @InjectMocks
    ReservationServiceImpl reservationServiceImpl;

    ReservationDTO reserv;
    Reservation res;

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
    @DisplayName("Test methode getAllByEstablishment() Id Exist")
    void testGetAllByEstablishmentIdExist() {
        Reservation res2 = new Reservation();
        when(reservationDAO.findAllByEstablishmentIdAndActiveIsTrue(1L)).thenReturn(Arrays.<Reservation>asList(res2));
        List<ReservationDTO> result = reservationServiceImpl.getAllByEstablishmentId(1L);
        Assertions.assertNotNull(result);

    }

    @Test
    @DisplayName("Test methode getAllByEstablishment() Id not Exist")
    void testGetAllByEstablishmentIdNotExist() {
        when(reservationDAO.findAllByEstablishmentIdAndActiveIsTrue(10L)).thenReturn(Collections.emptyList());

        Assertions.assertThrows(NotFoundException.class, () -> reservationServiceImpl.getAllByEstablishmentId(10L));
    }

    @Test
    @DisplayName("Test methode getAllByUserId() id Not Exist")
    void testGetAllByUserIdNotExist() {
        when(reservationDAO.findAllByUserIdAndActiveIsTrue(1L)).thenReturn(Collections.emptyList());

        Assertions.assertThrows(NotFoundException.class, () -> reservationServiceImpl.getAllByUserId(1L));
    }

    @Test
    @DisplayName("Test methode getAllByUserId() id Exist")
    void testGetAllByUserIdExist() {
        when(reservationDAO.findAllByUserIdAndActiveIsTrue(10L)).thenReturn(Collections.singletonList(new Reservation()));

        Assertions.assertNotNull(reservationServiceImpl.getAllByUserId(10L));
    }

    @Test
    @DisplayName("Test methode createReservation() limiteException")
    void testCreateReservationLimitException() {
        when(reservationDAO.countReservationsByEstablishmentIdAndStartOfReservationGreaterThanEqualAndEndOfReservationEqualsAndActiveIsTrue(anyLong(), any(), any())).thenReturn(Long.valueOf(5));
        Assertions.assertThrows(LimiteException.class, () -> reservationServiceImpl.createReservation(reserv, 4));
    }

    @Test
    @DisplayName("Test methode createReservation()")
    void testCreateReservation() {
        when(reservationDAO.countReservationsByEstablishmentIdAndStartOfReservationGreaterThanEqualAndEndOfReservationEqualsAndActiveIsTrue(anyLong(), any(), any())).thenReturn(Long.valueOf(5));
        Assertions.assertNotNull(reservationServiceImpl.createReservation(reserv, 6));
    }

    @Test
    @DisplayName("Test methode availabilityOfReservationTime()")
    void testAvailabilityOfReservationTime() {
        when(reservationDAO.countReservationsByEstablishmentIdAndStartOfReservationGreaterThanEqualAndEndOfReservationEqualsAndActiveIsTrue(anyLong(), any(), any())).thenReturn(5L);

        Boolean result = reservationServiceImpl.availabilityOfReservationTime(new ReservationDTO(), 10);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Test methode validateReservation()")
    void testValidateReservation() {
        when(reservationDAO.findByValidateNumber(anyString())).thenReturn(new Reservation());
        when(reservationDAO.existsByValidateNumberAndActiveIsTrue(anyString())).thenReturn(true);

        reservationServiceImpl.validateReservation("validationNumber");
    }

    @Test
    @DisplayName("Test methode existByValidNumb() ")
    void testExistByValidNumb() {
        when(reservationDAO.existsByValidateNumberAndActiveIsTrue(anyString())).thenReturn(true);

        boolean result = reservationServiceImpl.existByValidNumb("validationNumber");
        Assertions.assertEquals(true, result);
    }

    @Test
    @DisplayName("Test methode existsByUserAndEstablAndStartOfReservation()")
    void testExistsByUserAndEstablAndStartOfReservation() {
        when(reservationDAO.existsByUserIdAndEstablishmentIdAndStartOfReservationAndActiveIsTrue(anyLong(), anyLong(), any())).thenReturn(true);

        boolean result = reservationServiceImpl.existsByUserAndEstablAndStartOfReservation(1L, 1L, new GregorianCalendar(2020, Calendar.SEPTEMBER, 23, 22, 9).getTime());
        Assertions.assertEquals(true, result);
    }

    @Test
    @DisplayName("Test methode getAllByEstablishmentIdActiveFalse() Id Exist")
    void testGetAllByEstablishmentIdActiveFalseIdExist() {
        when(reservationDAO.findAllByEstablishmentIdAndActiveIsFalse(10L)).thenReturn(Collections.singletonList(res));
        List<ReservationDTO> result = reservationServiceImpl.getAllByEstablishmentIdActiveFalse(10L);

        Assertions.assertNotNull(result);
    }

    @Test
    @DisplayName("Test methode getAllByEstablishmentIdActiveFalse() Id not Exist")
    void testGetAllByEstablishmentIdActiveFalseIdNotExist() {
        when(reservationDAO.findAllByEstablishmentIdAndActiveIsFalse(9L)).thenReturn(Collections.emptyList());

        Assertions.assertThrows(NotFoundException.class, () -> reservationServiceImpl.getAllByEstablishmentIdActiveFalse(9L));
    }
}
