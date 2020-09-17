package com.sys.establishment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sys.establishment.dto.EstablishmentDTO;
import com.sys.establishment.model.Establishment;
import com.sys.establishment.model.TypeOfEstablishment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EstablishmentService {

    List<EstablishmentDTO> getAll();
    List<EstablishmentDTO> searchBy(String name, String typeId);
    EstablishmentDTO save(EstablishmentDTO establishment);
    void delete(Long id);
    EstablishmentDTO getOne(Long id);
    EstablishmentDTO jsonToEntity(String establishmentJSON) throws JsonProcessingException;

}
