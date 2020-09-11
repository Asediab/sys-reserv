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
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final String MORNING = "morning";
    private final String DAY = "day";
    private final String EVENING = "evening";
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
        if (countReservationsByHour(reservationDTO.getBeginning()) >= limitationOfReservation){
            LOGGER.info("Exceeded the limit of reservations for this time: " + reservationDTO.getBeginning());
            throw new LimiteException("Exceeded the limit of reservations for this time: " + reservationDTO.getBeginning());
        } else {
            LOGGER.info("Reservation created");
            reservationDTO.setValidateNumber(GeneratorValidateNumber.getAlphaNumericString(LENGTH_VALIDATION_NUMBER));
            return toDto(reservationDAO.save(toEntity(reservationDTO)));
        }
    }

    @Override
    public void deleteReservation(ReservationDTO reservationDTO) {
        if (!reservationDAO.existsById(reservationDTO.getId())){
            throw new NotFoundException("Reservation with this id not exist");
        } else {
            reservationDAO.delete(toEntity(reservationDTO));
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
    public LinkedHashMap<String, Long> getAvailableTimeMorningForEstablishment(Long establishmentId) {
        if(reservationDAO.existsById(establishmentId)) {
            return countReservationsByPartOfDay(MORNING);
        } else {
            throw new NotFoundException("Reservation with this id: " + establishmentId + " not exist");
        }
    }

    @Override
    public LinkedHashMap<String, Long> getAvailableTimeDayForEstablishment(Long establishmentId) {
        if(reservationDAO.existsById(establishmentId)) {
            return countReservationsByPartOfDay(DAY);
        } else {
            throw new NotFoundException("Reservation with this id: " + establishmentId + " not exist");
        }
    }

    @Override
    public LinkedHashMap<String, Long> getAvailableTimeEveningForEstablishment(Long establishmentId) {
        if(reservationDAO.existsById(establishmentId)) {
            return countReservationsByPartOfDay(EVENING);
        } else {
            throw new NotFoundException("Reservation with this id: " + establishmentId + " not exist");
        }
    }

    @Override
    public boolean existsByUserAndEstablAndBegins(Long userId, Long establishmentId, int beginning) {
        return reservationDAO.existsByUserIdAndEstablishmentIdAndBeginning(userId, establishmentId, beginning);
    }

    private LinkedHashMap<String, Long> countReservationsByPartOfDay(String partOfDay) {
        LinkedHashMap<String, Long> map = new LinkedHashMap<>();
        switch (partOfDay) {
            case (MORNING):
                for (int i = 6; i <= 12; i++){
                    map.put(i + ":00", countReservationsByHour(i));
                }
                break;
            case (DAY):
                for (int i = 13; i <= 17; i++){
                    map.put(i + ":00", countReservationsByHour(i));
                }
                break;
            case (EVENING):
                for (int i = 18; i <= 23; i++){
                    map.put(i + ":00", countReservationsByHour(i));
                }
                break;
        }
        return map;
    }

    private Long countReservationsByHour(int i) {
        Long numberOfReservations = reservationDAO.countByBeginning(i);
        numberOfReservations += reservationDAO.countByBeginningAndNumberOfHours(i-1, 2);
        numberOfReservations += reservationDAO.countByBeginningAndNumberOfHours(i-2, 3);
        numberOfReservations += reservationDAO.countByBeginningAndNumberOfHours(i-3, 3);
        return numberOfReservations;
    }

    private ReservationDTO toDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDTO.class);
    }

    private Reservation toEntity(ReservationDTO reservationDTO) {
        return modelMapper.map(reservationDTO, Reservation.class);
    }
}
