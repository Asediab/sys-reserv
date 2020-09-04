package com.sys.establishment.service;

import com.sys.establishment.dto.EstablishmentDTO;
import com.sys.establishment.dto.TypeOfEstablishmentDTO;
import com.sys.establishment.model.TypeOfEstablishment;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    String saveFile(MultipartFile file, EstablishmentDTO Establishment);

    Resource getFile(String name);
}
