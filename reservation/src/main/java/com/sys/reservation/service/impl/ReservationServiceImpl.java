package com.sys.reservation.service.impl;

import com.sys.reservation.dao.ReservationDAO;
import com.sys.reservation.dto.ReservationDTO;
import com.sys.reservation.model.Reservation;
import com.sys.reservation.service.ReservationService;
import com.sys.reservation.util.GeneratorValidateNumber;
import com.sys.reservation.web.exception.LimiteException;
import com.sys.reservation.web.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final int LENGTH_VALIDATION_NUMBER = 6;

    private final ModelMapper modelMapper = new ModelMapper();

    private final ReservationDAO reservationDAO;

    public ReservationServiceImpl(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }


    @Override
    public List<ReservationDTO> getAllByEstablishmentId(Long establishmentId) {
        List<ReservationDTO> dtos = new ArrayList<>();
        List<Reservation> entityList = reservationDAO.findAllByEstablishmentId(establishmentId);

        for (Reservation e : entityList) {
            dtos.add(toDto(e));
        }

        if (!dtos.isEmpty()) {
            return dtos;
        } else {
            LOGGER.error("Data Base is empty");
            throw new NotFoundException("Data Base is empty");
        }
    }

    @Override
    public List<ReservationDTO> getAllByUserId(Long userId) {
        List<ReservationDTO> dtos = new ArrayList<>();
        List<Reservation> entityList = reservationDAO.findAllByUserId(userId);

        for (Reservation e : entityList) {
            dtos.add(toDto(e));
        }

        if (!dtos.isEmpty()) {
            return dtos;
        } else {
            LOGGER.error("Data Base is empty");
            throw new NotFoundException("Data Base is empty");
        }
    }

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO, int limitationOfReservation) {
        if (!availabilityOfReservationTime(reservationDTO, limitationOfReservation)){
            LOGGER.info("Exceeded the limit of reservations for this time: " + reservationDTO.getStartOfReservation());
            throw new LimiteException("Exceeded the limit of reservations for this time: " + reservationDTO.getStartOfReservation());
        } else {
            LOGGER.info("Reservation created");
            reservationDTO.setValidateNumber(GeneratorValidateNumber.getAlphaNumericString(LENGTH_VALIDATION_NUMBER));
            return toDto(reservationDAO.save(toEntity(reservationDTO)));
        }
    }

    private Boolean availabilityOfReservationTime (ReservationDTO reservationDTO, int limitationOfReservations) {
        Long count = reservationDAO.countReservationsByStartOfReservationIsLessThanEqualAndEndOfReservationEquals(reservationDTO.getStartOfReservation(), reservationDTO.getEndOfReservation());
        return count < limitationOfReservations;
    }

    @Override
    public void deleteReservation(Long id) {
        if (!reservationDAO.existsById(id)){
            throw new NotFoundException("Reservation with this id not exist");
        } else {
            reservationDAO.deleteById(id);
            LOGGER.info("Reservation deleted");
        }
    }

    @Override
    public void validateReservation(String validationNumber) {
        if (!reservationDAO.existsByValidateNumber(validationNumber)){
            throw new NotFoundException("Reservation with this validationNumber: " + validationNumber + " not exist");
        } else {
            Reservation res = reservationDAO.findByValidateNumber(validationNumber);
            res.setValid(Boolean.TRUE);
            reservationDAO.save(res);
            LOGGER.info("Reservation validated");
        }
    }

    @Override
    public boolean existByValidNumb(String validationNumber) {
        return reservationDAO.existsByValidateNumber(validationNumber);
    }

    @Override
    public boolean existsByUserAndEstablAndStartOfReservation(Long userId, Long establishmentId, Date startOfReservation) {
        return reservationDAO.existsByUserIdAndEstablishmentIdAndStartOfReservation(userId, establishmentId, startOfReservation);
    }

    private ReservationDTO toDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDTO.class);
    }

    private Reservation toEntity(ReservationDTO reservationDTO) {
        return modelMapper.map(reservationDTO, Reservation.class);
    }
}
