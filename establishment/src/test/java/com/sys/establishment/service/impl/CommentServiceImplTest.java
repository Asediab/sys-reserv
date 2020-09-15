package com.sys.establishment.service.impl;

import com.sys.establishment.dao.CommentDAO;
import com.sys.establishment.dao.EstablishmentDAO;
import com.sys.establishment.dto.CommentDTO;
import com.sys.establishment.model.Comment;
import com.sys.establishment.model.Establishment;
import com.sys.establishment.model.TimeTable;
import com.sys.establishment.model.TypeOfEstablishment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class CommentServiceImplTest {

    @Mock
    ModelMapper modelMapper;
    @Mock
    CommentDAO commentDAO;
    @Mock
    EstablishmentDAO establishmentDAO;
    @InjectMocks
    CommentServiceImpl commentServiceImpl;

    Comment comment;
    CommentDTO commentDTO;
    Establishment establishment = new Establishment();
    TimeTable timeTable = new TimeTable();

}
