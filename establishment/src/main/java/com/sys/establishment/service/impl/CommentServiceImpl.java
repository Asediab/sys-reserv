package com.sys.establishment.service.impl;

import com.sys.establishment.dao.CommentDAO;
import com.sys.establishment.dao.EstablishmentDAO;
import com.sys.establishment.dto.CommentDTO;
import com.sys.establishment.model.Comment;
import com.sys.establishment.service.CommentService;
import com.sys.establishment.web.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final ModelMapper modelMapper = new ModelMapper();

    private final CommentDAO commentDAO;

    private final EstablishmentDAO establishmentDAO;

    public CommentServiceImpl(CommentDAO commentDAO, EstablishmentDAO establishmentDAO) {
        this.commentDAO = commentDAO;
        this.establishmentDAO = establishmentDAO;
    }

    @Override
    public CommentDTO save(CommentDTO commentDTO) {
        LOGGER.info("Comment saved");
        return toDto(commentDAO.save(toEntity(commentDTO)));
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if (!commentDAO.existsById(id)){
            throw new NotFoundException("Comment with this id not exist");
        } else {
            commentDAO.deleteById(id);
            LOGGER.info("Comment deleted");
        }
    }

    @Override
    public CommentDTO detOne(Long id) {
        Comment comment = commentDAO.findById(id).orElseThrow(() -> new NotFoundException("Comment with this Id not found"));
        return toDto(comment);
    }

    private CommentDTO toDto(Comment comment) {
        return modelMapper.map(comment, CommentDTO.class);
    }

    private Comment toEntity(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        Long id = commentDTO.getEstablishmentId();
        comment.setEstablishment(establishmentDAO.getOne(id));
        return comment;
    }
}
