package com.sys.reservation.web.controller;

import com.sys.reservation.dao.ReservationDAO;
import com.sys.reservation.dto.ReservationDTO;
import com.sys.reservation.model.Reservation;
import com.sys.reservation.service.ReservationService;
import com.sys.reservation.web.exception.NotFoundException;
import com.sys.reservation.web.exception.ReservationExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("apiRes/reservation")
public class ReservationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService service;


    @GetMapping("byUser/{id}")
    public List<ReservationDTO> getReservationsByUser(@PathVariable("id") Long id) {
        return service.getAllByUserId(id);
    }

    @GetMapping("byEstabl/{id}")
    public List<ReservationDTO> getReservationsByEstabl(@PathVariable("id") Long id) {
        return service.getAllByEstablishmentId(id);
    }

    @PostMapping("add")
    public ResponseEntity<Void> saveReservation(@RequestBody ReservationDTO reservationDTO,
                                                  @RequestParam("limit") int limit){
        if (service.existsByUserAndEstablAndBegins(reservationDTO.getUserId(), reservationDTO.getEstablishmentId(), reservationDTO.getBeginning())) {
            LOGGER.error("Reservation exist");
            throw new ReservationExistException("Reservation exist");
        } else {
            ReservationDTO reservation = service.createReservation(reservationDTO, limit);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteReservation(@RequestBody ReservationDTO reservationDTO){
        try {
            service.deleteReservation(reservationDTO);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            LOGGER.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("valid/{validateNumb}")
    public ResponseEntity<Void> validateReservation(@PathVariable("validateNumb") String validateNumb){
        if (!service.existByValidNumb(validateNumb)) {
            LOGGER.error("Reservation with validateNumber: " + validateNumb + " not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            service.validateReservation(validateNumb);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @GetMapping("accessibilityDay/{id}")
    public LinkedHashMap<String, Long> getListAccessibilityDay(@PathVariable("id") Long id) {
        return service.getAvailableTimeDayForEstablishment(id);
    }

    @GetMapping("accessibilityMorning/{id}")
    public LinkedHashMap<String, Long> getListAccessibilityMorning(@PathVariable("id") Long id) {
        return service.getAvailableTimeMorningForEstablishment(id);
    }

    @GetMapping("accessibilityEvening/{id}")
    public LinkedHashMap<String, Long> getListAccessibilityEvening(@PathVariable("id") Long id) {
        return service.getAvailableTimeEveningForEstablishment(id);
    }

}
