package com.sys.establishment.service;

import com.sys.establishment.dto.CommentDTO;

public interface CommentService {

    CommentDTO save(CommentDTO commentDTO);
    void delete(CommentDTO commentDTO);
    CommentDTO detOne(Long id);
}
