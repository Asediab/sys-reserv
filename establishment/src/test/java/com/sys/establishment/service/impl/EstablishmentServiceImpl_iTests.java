package com.sys.establishment.service.impl;

import com.sys.establishment.dao.EstablishmentDAO;
import com.sys.establishment.dto.EstablishmentDTO;
import com.sys.establishment.dto.TimeTableDTO;
import com.sys.establishment.model.TypeOfEstablishment;
import com.sys.establishment.web.exception.NotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EstablishmentServiceImpl_iTests {

    @Autowired
    EstablishmentDAO establishmentDAO;

    @Autowired
    EstablishmentServiceImpl establishmentService = new EstablishmentServiceImpl(establishmentDAO);

    static EstablishmentDTO establishmentDTOTest;
    static TimeTableDTO timeTable;
    static EstablishmentDTO result;

    @BeforeAll
    static void start() {
        timeTable = new TimeTableDTO();
        timeTable.setFridayAMEnd(Calendar.getInstance().getTime());
        timeTable.setFridayAMStart(Calendar.getInstance().getTime());
        timeTable.setFridayPMEnd(Calendar.getInstance().getTime());
        timeTable.setFridayPMStart(Calendar.getInstance().getTime());
        timeTable.setMondayAMEnd(Calendar.getInstance().getTime());
        timeTable.setMondayAMStart(Calendar.getInstance().getTime());
        timeTable.setMondayPMEnd(Calendar.getInstance().getTime());
        timeTable.setMondayPMStart(Calendar.getInstance().getTime());
        timeTable.setSaturdayAMEnd(Calendar.getInstance().getTime());
        timeTable.setSaturdayAMStart(Calendar.getInstance().getTime());
        timeTable.setSaturdayPMEnd(Calendar.getInstance().getTime());
        timeTable.setSaturdayPMStart(Calendar.getInstance().getTime());
        timeTable.setSundayAMEnd(Calendar.getInstance().getTime());
        timeTable.setSundayAMStart(Calendar.getInstance().getTime());
        timeTable.setSundayPMEnd(Calendar.getInstance().getTime());
        timeTable.setSundayPMStart(Calendar.getInstance().getTime());
        timeTable.setThursdayAMEnd(Calendar.getInstance().getTime());
        timeTable.setThursdayAMStart(Calendar.getInstance().getTime());
        timeTable.setThursdayPMEnd(Calendar.getInstance().getTime());
        timeTable.setThursdayPMStart(Calendar.getInstance().getTime());
        timeTable.setTuesdayAMEnd(Calendar.getInstance().getTime());
        timeTable.setTuesdayAMStart(Calendar.getInstance().getTime());
        timeTable.setTuesdayPMEnd(Calendar.getInstance().getTime());
        timeTable.setTuesdayPMStart(Calendar.getInstance().getTime());
        timeTable.setWednesdayAMEnd(Calendar.getInstance().getTime());
        timeTable.setWednesdayAMStart(Calendar.getInstance().getTime());
        timeTable.setWednesdayPMEnd(Calendar.getInstance().getTime());
        timeTable.setWednesdayPMStart(Calendar.getInstance().getTime());
    }

    @BeforeEach
    void setUp() {
        establishmentDTOTest = new EstablishmentDTO();
        establishmentDTOTest.setAddress("Address");
        establishmentDTOTest.setClients_limit(5);
        establishmentDTOTest.setDescription("Description");
        establishmentDTOTest.setName("Name");
        establishmentDTOTest.setTimeTable(timeTable);
        establishmentDTOTest.setTypeOfEstablishment(TypeOfEstablishment.POOL);
        establishmentDTOTest.setPicture("pool.jpg");
    }

    @Test
    @Order(1)
    @DisplayName("Test methode save()")
    void testSave() {
        result = establishmentService.save(establishmentDTOTest);
        assertTrue(establishmentDAO.existsById(result.getId()));
    }

    @Test
    @Order(2)
    @DisplayName("Test methode search() by name")
    void testSearchByName() {
        List<EstablishmentDTO> search = establishmentService.searchBy("ame","");
        assertNotNull(search);
    }

    @Test
    @Order(3)
    @DisplayName("Test methode search() by name and type")
    void testSearchByNameAndType() {
        List<EstablishmentDTO> search = establishmentService.searchBy("ame","POOL");
        assertNotNull(search);
    }

    @Test
    @Order(4)
    @DisplayName("Test methode search() by type")
    void testSearchByType() {
        List<EstablishmentDTO> search = establishmentService.searchBy("","POOL");
        assertNotNull(search);
    }

    @Test
    @Order(5)
    @DisplayName("Test methode getOne() Id exist")
    void testGetOneIdExist() {
        assertThatCode(() -> establishmentService.getOne(result.getId()))
                .doesNotThrowAnyException();
    }

    @Test
    @Order(6)
    @DisplayName("Test methode getOne() Id not exist")
    void testDetOneIdNotExist() {
        assertThrows(NotFoundException.class, () -> {
            establishmentService.getOne(100000L);
        });
    }


    @Test
    @Order(7)
    @DisplayName("Test methode delete() comment not exist")
    void testDeleteEstablishmentNotExist() {
        assertThrows(NotFoundException.class, () -> {
            establishmentService.delete(100000L);
        });
    }

    @Test
    @Order(8)
    @DisplayName("Test methode getAll()")
    void testGetAll() {
        assertThatCode(() -> establishmentService.getAll())
                .doesNotThrowAnyException();
    }

    @Test
    @Order(9)
    @DisplayName("Test methode delete() comment exist")
    void testDeleteEstablishmentExist() {
        assertThatCode(() -> establishmentService.delete(result.getId()))
                .doesNotThrowAnyException();
        assertFalse(establishmentDAO.existsById(result.getId()));
    }

}
