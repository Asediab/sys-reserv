package com.sys.reservation.service.impl;

import com.sys.reservation.dao.ReservationDAO;
import com.sys.reservation.dto.ReservationDTO;
import com.sys.reservation.model.Reservation;
import com.sys.reservation.web.exception.LimiteException;
import com.sys.reservation.web.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllByEstablishmentIdExist() {
        Reservation res2 = new Reservation();
        when(reservationDAO.findAllByEstablishmentIdAndActiveIsTrue(1L)).thenReturn(Arrays.<Reservation>asList(res2));
        List<ReservationDTO> result = reservationServiceImpl.getAllByEstablishmentId(1L);
        Assertions.assertNotNull(result);

    }

    @Test
    void testGetAllByEstablishmentIdNotExist() {
        when(reservationDAO.findAllByEstablishmentIdAndActiveIsTrue(10L)).thenReturn(Collections.emptyList());

        Assertions.assertThrows(NotFoundException.class, () -> reservationServiceImpl.getAllByEstablishmentId(10L));
    }

    @Test
    void testGetAllByUserId() {
        when(reservationDAO.findAllByUserIdAndActiveIsTrue(1L)).thenReturn(Collections.emptyList());

        Assertions.assertThrows(NotFoundException.class, () -> reservationServiceImpl.getAllByUserId(1L));
    }

    @Test
    void testCreateReservation() {
        when(reservationDAO.countReservationsByEstablishmentIdAndStartOfReservationGreaterThanEqualAndEndOfReservationEqualsAndActiveIsTrue(anyLong(), any(), any())).thenReturn(Long.valueOf(5));
        ReservationDTO reserv = new ReservationDTO();
        reserv.setValidateNumber("fefe");
        reserv.setEndOfReservation(Calendar.getInstance().getTime());
        reserv.setStartOfReservation(Calendar.getInstance().getTime());
        reserv.setEstablishmentId(1L);
        reserv.setUserFirstName("User");
        reserv.setUserId(1L);
        reserv.setEstablishmentName("name");
        reserv.setValid(false);
        Assertions.assertThrows(LimiteException.class, () -> reservationServiceImpl.createReservation(reserv, 4));
    }

    @Test
    void testAvailabilityOfReservationTime() {
        when(reservationDAO.countReservationsByEstablishmentIdAndStartOfReservationGreaterThanEqualAndEndOfReservationEqualsAndActiveIsTrue(anyLong(), any(), any())).thenReturn(5L);

        Boolean result = reservationServiceImpl.availabilityOfReservationTime(new ReservationDTO(), 10);
        Assertions.assertTrue(result);
    }

    @Test
    void testValidateReservation() {
        when(reservationDAO.findByValidateNumber(anyString())).thenReturn(new Reservation());
        when(reservationDAO.existsByValidateNumberAndActiveIsTrue(anyString())).thenReturn(true);

        reservationServiceImpl.validateReservation("validationNumber");
    }

    @Test
    void testExistByValidNumb() {
        when(reservationDAO.existsByValidateNumberAndActiveIsTrue(anyString())).thenReturn(true);

        boolean result = reservationServiceImpl.existByValidNumb("validationNumber");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testExistsByUserAndEstablAndStartOfReservation() {
        when(reservationDAO.existsByUserIdAndEstablishmentIdAndStartOfReservationAndActiveIsTrue(anyLong(), anyLong(), any())).thenReturn(true);

        boolean result = reservationServiceImpl.existsByUserAndEstablAndStartOfReservation(1L, 1L, new GregorianCalendar(2020, Calendar.SEPTEMBER, 23, 22, 9).getTime());
        Assertions.assertEquals(true, result);
    }
}
