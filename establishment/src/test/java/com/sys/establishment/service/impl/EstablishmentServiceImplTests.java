package com.sys.establishment.service.impl;

import com.sys.establishment.dao.EstablishmentDAO;
import com.sys.establishment.dto.EstablishmentDTO;
import com.sys.establishment.model.Comment;
import com.sys.establishment.model.Establishment;
import com.sys.establishment.model.TimeTable;
import com.sys.establishment.model.TypeOfEstablishment;
import com.sys.establishment.service.impl.EstablishmentServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

;import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;

@SpringBootTest
class EstablishmentServiceImplTests {

    @Mock
    EstablishmentDAO establishmentDAO = mock(EstablishmentDAO.class);

    @InjectMocks
    EstablishmentServiceImpl establishmentService = mock(EstablishmentServiceImpl.class);

    @Autowired
    EstablishmentServiceImpl test;

    Establishment establishment1 = new Establishment();
    Comment comment = new Comment();

    TimeTable timeTable = new TimeTable();

}
