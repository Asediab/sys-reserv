package com.sys.establishment;

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
    TypeOfEstablishment typeOfEstablishment = new TypeOfEstablishment();
    TimeTable timeTable = new TimeTable();

    @BeforeEach
    void setUp() {
        comment.setAuthor("User");
        comment.setEstablishment(establishment1);
        comment.setText("Text");
        comment.setUserId(2L);

        typeOfEstablishment.setId(1L);
        typeOfEstablishment.setType("Type");

        timeTable.setEstablishment(establishment1);
        timeTable.setTuesday("1000-2000");
        timeTable.setFriday("1000-2000");
        timeTable.setMonday("1000-2000");
        timeTable.setSaturday("1000-2000");
        timeTable.setSunday("1000-2000");
        timeTable.setThursday("1000-2000");
        timeTable.setWednesday("1000-2000");

        establishment1.setAddress("dfefefef");
        establishment1.setClients_limit(200);
        establishment1.setComments(List.of(comment));
        establishment1.setName("NAme");
        establishment1.setTimeTable(timeTable);
        establishment1.setTypeOfEstablishment(typeOfEstablishment);
        establishment1.setDescription("asdefe");
        establishment1.setPicture("defrrg");
    }


    @Test
    void toDto() {

    }

}
