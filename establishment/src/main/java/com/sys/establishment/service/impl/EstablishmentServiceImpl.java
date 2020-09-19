package com.sys.establishment.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.BooleanBuilder;
import com.sys.establishment.dao.EstablishmentDAO;
import com.sys.establishment.dto.EstablishmentDTO;
import com.sys.establishment.model.Comment;
import com.sys.establishment.model.Establishment;
import com.sys.establishment.model.QEstablishment;
import com.sys.establishment.service.EstablishmentService;
import com.sys.establishment.web.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstablishmentServiceImpl.class);
    private final ModelMapper modelMapper = new ModelMapper();

    private final EstablishmentDAO establishmentDAO;

    public EstablishmentServiceImpl(EstablishmentDAO establishmentDAO) {
        this.establishmentDAO = establishmentDAO;
    }

    @Override
    public List<EstablishmentDTO> getAll() {
        List<EstablishmentDTO> dtos = new ArrayList<>();
        List<Establishment> entityList = establishmentDAO.findAll();

        for (Establishment e : entityList) {
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
    public List<EstablishmentDTO> searchBy(String name, String type) {
        List<EstablishmentDTO> dtos = new ArrayList<>();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        final QEstablishment qEstablishment = QEstablishment.establishment;
        if (!name.isBlank()) {
            booleanBuilder.and(qEstablishment.name.containsIgnoreCase(name));
        }
        if (!type.isBlank()) {
            booleanBuilder.and(qEstablishment.typeOfEstablishment.eq(TypeOfEstablishment.valueOf(type)));
        }
        LOGGER.info("Establishment searched");
        List<Establishment> entityList = (List<Establishment>) establishmentDAO.findAll(booleanBuilder);
        for (Establishment e : entityList) {
            dtos.add(toDto(e));
        }
        return dtos;
    }

    @Override
    public EstablishmentDTO save(EstablishmentDTO establishment) {
        LOGGER.info("Establishment saved");
        return toDto(establishmentDAO.save(toEntity(establishment)));
    }

    @Override
    public void delete(Long id) {
        if (!establishmentDAO.existsById(id)){
            throw new NotFoundException("Establishment with this id not exist");
        } else {
            establishmentDAO.deleteById(id);
            LOGGER.info("Establishment deleted");
        }
    }

    @Override
    public EstablishmentDTO getOne(Long id) {
        Establishment establishment = establishmentDAO.findById(id).orElseThrow(() -> new NotFoundException("Establishment with this Id not found"));
        return toDto(establishment);
    }


    private EstablishmentDTO toDto(Establishment establishment) {
        EstablishmentDTO establishmentDTO = modelMapper.map(establishment, EstablishmentDTO.class);
        return establishmentDTO;
    }

    private Establishment toEntity(EstablishmentDTO establishmentDTO) {
        Establishment establishment = modelMapper.map(establishmentDTO, Establishment.class);
        if (establishmentDTO.getComments() != null) {
            List<Comment> comments = establishment.getComments();
            for (Comment comment:comments){
                comment.setEstablishment(establishment);
            }
        }
        return establishment;
    }

    public EstablishmentDTO jsonToEntity(String establishmentJSON) throws JsonProcessingException {
        EstablishmentDTO establishment;
        ObjectMapper obj = new ObjectMapper();
        obj.findAndRegisterModules();
        establishment = obj.readValue(establishmentJSON, EstablishmentDTO.class);
        return establishment;
    }
}
