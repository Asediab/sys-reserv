package com.sys.establishment.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sys.establishment.dto.EstablishmentDTO;
import com.sys.establishment.model.Establishment;
import com.sys.establishment.service.EstablishmentService;
import com.sys.establishment.service.FileUploadService;
import com.sys.establishment.service.impl.EstablishmentServiceImpl;
import com.sys.establishment.web.exception.EstablishmentExistException;
import com.sys.establishment.web.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("apiEst/establishment")
public class EstablishmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstablishmentController.class);

    @Autowired
    private EstablishmentService establishmentService;

    @Autowired
    FileUploadService fileUploadService;

    @GetMapping()
    public List<EstablishmentDTO> getEstablishments(@RequestParam (name = "name", defaultValue = "", required = false) String name,
                                                @RequestParam (name = "type", defaultValue = "", required = false) String type) {
        if (name.isBlank() && type.isBlank()) {
            return establishmentService.getAll();
        }else {
            return establishmentService.searchBy(name, type);
        }
    }

    @GetMapping("{id}")
    public EstablishmentDTO getEstablishmentByID(@PathVariable("id") Long id) {
        return establishmentService.getOne(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
                                MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> saveEstablishment(@RequestPart ("establishment") String establishmentJSON,
                                                  @RequestPart("file") MultipartFile file){
        EstablishmentDTO jsonEstablishment;
        try {
            jsonEstablishment = establishmentService.jsonToEntity(establishmentJSON);
            jsonEstablishment.setPicture(fileUploadService.saveFile(file, jsonEstablishment));
            EstablishmentDTO estab = establishmentService.save(jsonEstablishment);
            if (estab == null) {
                LOGGER.error("Establishment exist");
                throw new EstablishmentExistException("Establishment exist");
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (JsonProcessingException e) {
            LOGGER.error("Error:" + e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }


    @DeleteMapping()
    public ResponseEntity<Void> deleteEstablishment(@RequestBody EstablishmentDTO establishmentDTO){
        try {
            establishmentService.delete(establishmentDTO);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            LOGGER.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }



}
