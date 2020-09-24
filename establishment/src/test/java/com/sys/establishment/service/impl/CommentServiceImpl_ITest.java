package com.sys.establishment.service.impl;

import com.sys.establishment.dao.CommentDAO;
import com.sys.establishment.dao.EstablishmentDAO;
import com.sys.establishment.dto.CommentDTO;
import com.sys.establishment.model.Establishment;
import com.sys.establishment.web.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceImpl_ITest {


    @Autowired
    CommentDAO commentDAO;
    @Autowired
    EstablishmentDAO establishmentDAO;
    @Autowired
    CommentServiceImpl commentServiceImpl = new CommentServiceImpl(commentDAO, establishmentDAO);

    static CommentDTO commentDTO = new CommentDTO();
    static Date crete = Calendar.getInstance().getTime();
    static Long id;

    @BeforeEach
    void setUp() {
        Establishment establishment = new Establishment();
        establishment.setId(1L);
        commentDTO.setAuthor("Author");
        commentDTO.setEstablishmentId(1L);
        commentDTO.setText("Comment Text");
        commentDTO.setUserId(1L);
        commentDTO.setDateCreated(crete);
    }

    @Test
    @DisplayName("Test methode save()")
    void testSave() {
        CommentDTO result = commentServiceImpl.save(commentDTO);
        id = result.getId();
        assertTrue(commentDAO.existsById(result.getId()));

    }

    @Test
    @DisplayName("Test methode getOne() Id exist")
    void testDetOneIdExist() {
        assertThatCode(() -> commentServiceImpl.detOne(id))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Test methode getOne() Id not exist")
    void testDetOneIdNotExist() {
        assertThrows(NotFoundException.class, () -> {
            commentServiceImpl.detOne(100000L);
        });
    }

    @Test
    @DisplayName("Test methode delete() comment exist")
    void testDeleteCommentExist() {
        assertThatCode(() -> commentServiceImpl.delete(id))
                .doesNotThrowAnyException();
        assertFalse(commentDAO.existsById(id));
    }

    @Test
    @DisplayName("Test methode delete() comment not exist")
    void testDeleteCommentNotExist() {
        assertThrows(NotFoundException.class, () -> {
            commentServiceImpl.delete(100000L);
        });
    }
}
