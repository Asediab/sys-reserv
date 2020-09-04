package com.sys.establishment.web.controller;

import com.sys.establishment.dto.EstablishmentDTO;
import com.sys.establishment.model.Establishment;
import com.sys.establishment.service.EstablishmentService;
import com.sys.establishment.service.impl.EstablishmentServiceImpl;
import com.sys.establishment.web.exception.EstablishmentExistException;
import com.sys.establishment.web.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.reflections.Reflections.log;

@RestController
@RequestMapping("apiEst/establishment")
public class EstablishmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstablishmentController.class);

    @Autowired
    private EstablishmentService establishmentService;

    @GetMapping()
    public List<EstablishmentDTO> getEstablishments(@RequestParam (name = "name", defaultValue = "", required = false) String name,
                                                @RequestParam (name = "type", defaultValue = "", required = false) String typeId) {
        if (name.isBlank() && typeId.isBlank()) {
            return establishmentService.getAll();
        }else {
            return establishmentService.searchBy(name, typeId);
        }
    }

    @GetMapping("{id}")
    public EstablishmentDTO getEstablishmentByID(@PathVariable("id") Long id) {
        return establishmentService.getOne(id);
    }

    @PostMapping("save")
    public ResponseEntity<Void> saveEstablishment(@RequestBody EstablishmentDTO establishmentDTO){
        EstablishmentDTO establ = establishmentService.save(establishmentDTO);
        if (establ == null) {
            LOGGER.error("Establishment exist");
            throw new EstablishmentExistException("Establishment exist");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("delete")
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