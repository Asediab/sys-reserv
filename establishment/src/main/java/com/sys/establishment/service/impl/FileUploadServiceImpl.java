package com.sys.establishment.service.impl;

import com.sys.establishment.dto.EstablishmentDTO;
import com.sys.establishment.dto.TypeOfEstablishmentDTO;
import com.sys.establishment.service.FileUploadService;
import com.sys.establishment.web.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${upload.path.img}")
    private String uploadPath;

    private final String DEFAULT_PICTURE_POOL = "pool.jpg";
    private final String DEFAULT_PICTURE_SPORTS_COMPLEX = "sports_complex.jpg";
    private final String DEFAULT_PICTURE_MEDIA_LIBRARY = "media_library.jpg";
    private final String DEFAULT_PICTURE = "default.jpg";

    private final String POOL = "Pool";
    private final String SPORTS_COMPLEX = "Sport complex";
    private final String MEDIA_LIBRARY = "Media library";

    private static final String FILE_REGEX = "^\\p{ASCII}*.(png|jpg|gif|bmp)";

    @Override
    public String saveFile(MultipartFile file, EstablishmentDTO establishment) {
        if(file == null && establishment.getPicture() == null) {
            return setDefaultPicture(establishment);
        } else if (file != null && establishment.getPicture() == null){
            return upload(file);
        }else if (file != null && establishment.getPicture() != null){
            return changeFile(file, establishment);
        }
        return establishment.getPicture();
    }

    @Override
    public Resource getFile(String name) {
        File file = new File(uploadPath + "/" + name);
        if (file.isFile()){
            return new FileSystemResource(file);
        }else {
            throw new NotFoundException("File not fount");
        }
    }

    private String setDefaultPicture(EstablishmentDTO establishmentDTO) {
        if (establishmentDTO.getTypeOfEstablishment().getType().equals(POOL)){
            return DEFAULT_PICTURE_POOL;
        }
        if (establishmentDTO.getTypeOfEstablishment().getType().equals(SPORTS_COMPLEX)){
            return DEFAULT_PICTURE_SPORTS_COMPLEX;
        }
        if (establishmentDTO.getTypeOfEstablishment().getType().equals(MEDIA_LIBRARY)){
            return DEFAULT_PICTURE_MEDIA_LIBRARY;
        }
        return DEFAULT_PICTURE;
    }

    private String upload(MultipartFile file){
        File uploadDirectory = new File(uploadPath);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }
        try {
            file.transferTo(new File(uploadPath + "/" + file.getOriginalFilename()));
            return file.getOriginalFilename();
        } catch (IOException e) {
           throw  new NotFoundException("Could not store file " + file.getOriginalFilename() + ". Please try again!");
        }
    }

    private String changeFile(MultipartFile file, EstablishmentDTO establishmentDTO) {
        if (Objects.equals(file.getOriginalFilename(), establishmentDTO.getPicture())) {
            return file.getOriginalFilename();
        }else {
            return upload(file);
        }
    }
}
