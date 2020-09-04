package com.sys.establishment.service;

import com.sys.establishment.dto.EstablishmentDTO;
import com.sys.establishment.model.Establishment;

import java.util.List;

public interface EstablishmentService {

    List<EstablishmentDTO> getAll();
    List<EstablishmentDTO> searchBy(String name, String typeId);
    EstablishmentDTO save(EstablishmentDTO establishment);
    void delete(EstablishmentDTO establishment);
    EstablishmentDTO getOne(Long id);

}
