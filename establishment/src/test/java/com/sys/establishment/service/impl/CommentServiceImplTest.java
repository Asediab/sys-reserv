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
    TypeOfEstablishment typeOfEstablishment = new TypeOfEstablishment();
    TimeTable timeTable = new TimeTable();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        comment.setUserId(1L);
        comment.setEstablishment(new Establishment());



        typeOfEstablishment.setId(1L);
        typeOfEstablishment.setType("Type");

        timeTable.setEstablishment(establishment);
        timeTable.setTuesday("1000-2000");
        timeTable.setFriday("1000-2000");
        timeTable.setMonday("1000-2000");
        timeTable.setSaturday("1000-2000");
        timeTable.setSunday("1000-2000");
        timeTable.setThursday("1000-2000");
        timeTable.setWednesday("1000-2000");

        establishment.setAddress("Address");
        establishment.setClients_limit(200);
        establishment.setComments(Collections.emptyList());
        establishment.setName("Name");
        establishment.setTimeTable(timeTable);
        establishment.setTypeOfEstablishment(typeOfEstablishment);
        establishment.setDescription("Description");
        establishment.setPicture("Pict");
    }
}