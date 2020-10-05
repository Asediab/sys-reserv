package com.sys.establishment.service;

import com.sys.establishment.dto.CommentDTO;
import com.sys.establishment.web.exception.NotFoundException;

public interface CommentService {

    CommentDTO save(CommentDTO commentDTO);
    void delete(Long id) throws NotFoundException;
    CommentDTO detOne(Long id);
}
