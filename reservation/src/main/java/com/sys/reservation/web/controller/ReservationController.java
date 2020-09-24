package com.sys.reservation.web.controller;

import com.sys.reservation.dto.ReservationDTO;
import com.sys.reservation.service.ReservationService;
import com.sys.reservation.web.exception.NotFoundException;
import com.sys.reservation.web.exception.ReservationExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apiRes/reservation")
public class ReservationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService service;

    @CrossOrigin
    @GetMapping("byUser/{id}")
    public List<ReservationDTO> getReservationsByUser(@PathVariable("id") Long id) {
        return service.getAllByUserId(id);
    }

    @CrossOrigin
    @GetMapping("byEstabl/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    public List<ReservationDTO> getReservationsByEstabl(@PathVariable("id") Long id) {
        return service.getAllByEstablishmentId(id);
    }

    @CrossOrigin
    @PostMapping("add")
    public ResponseEntity<Void> saveReservation(@RequestBody ReservationDTO reservationDTO,
                                                  @RequestParam("limit") int limit){
        if (service.existsByUserAndEstablAndStartOfReservation(reservationDTO.getUserId(), reservationDTO.getEstablishmentId(), reservationDTO.getStartOfReservation())) {
            LOGGER.error("Reservation exist");
            throw new ReservationExistException("Reservation exist");
        } else {
            ReservationDTO reservation = service.createReservation(reservationDTO, limit);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @CrossOrigin
    @PostMapping("nearestAvailable")
    public List<ReservationDTO> getNearestAvailableRes(@RequestBody ReservationDTO reservationDTO,
                                                       @RequestParam("limit") int limit) {
        return service.getListReservationsDispon(reservationDTO, limit);
    }

    @CrossOrigin
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id){
        try {
            service.deleteReservation(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            LOGGER.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping("valid/{validateNumb}")
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    public ResponseEntity<Void> validateReservation(@PathVariable("validateNumb") String validateNumb){
        if (!service.existByValidNumb(validateNumb)) {
            LOGGER.error("Reservation with validateNumber: " + validateNumb + " not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            service.validateReservation(validateNumb);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
