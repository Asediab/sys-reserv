package com.sys.user.web.service.impl;

import com.sys.user.dto.UserDTO;
import com.sys.user.model.Role;
import com.sys.user.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userServiceImpl;

    User user = new User();

    @BeforeEach
    void start() {
        user.setId(1L);
        user.setFirstName("User");
        user.setLastName("User");
        user.setEmail("user@user.com");
        user.setPassword("$2a$10$a3w89n.b/aRcUYPwdPlO8.89WhanqcUYmDssnr0BgIQ98cK9bxN0q");
        user.setRoles(Role.USER);
        user.setEstablishmentId(-1L);
    }

    @Test
    @Tag("findByEmail")
    @DisplayName("Test findByEmail")
    void findByEmail() {
        try {
            User user1 = userServiceImpl.findByEmail("user@user.com");
            Assertions.assertNotNull(user1);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    @Tag("findById")
    @DisplayName("Test findById")
    void findByID() {
        try {
            UserDTO user1 = userServiceImpl.findById(1L);
            Assertions.assertNotNull(user1);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    @Tag("findByEstablishmentId")
    @DisplayName("Test findByEstablishmentId")
    void findByEstablishmentID() {
        try {
            List<UserDTO> user1 = userServiceImpl.usersByEstablishmentId(1L);
            Assertions.assertNotNull(user1);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    @Tag("create")
    @DisplayName("Test Create new User")
    void createUser() {
        UserDTO userSave = new UserDTO();
        userSave.setFirstName("User");
        userSave.setLastName("User");
        userSave.setEmail("user23@user.com");
        userSave.setPassword("$2a$10$a3w89n.b/aRcUYPwdPlO8.89WhanqcUYmDssnr0BgIQ98cK9bxN0q");
        userSave.setEstablishmentId(-1L);
        try {
            UserDTO user1 = userServiceImpl.createUser(userSave);
            Assertions.assertNotNull(user1);

            userServiceImpl.deleteUser(user1.getId());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    @Tag("create")
    @DisplayName("Test Create new Employee")
    void createEmployee() {
        UserDTO userSave = new UserDTO();
        userSave.setFirstName("Employ");
        userSave.setLastName("Employ");
        userSave.setEmail("Employ@user.com");
        userSave.setPassword("$2a$10$a3w89n.b/aRcUYPwdPlO8.89WhanqcUYmDssnr0BgIQ98cK9bxN0q");
        userSave.setEstablishmentId(2L);
        try {
            UserDTO user1 = userServiceImpl.createUser(userSave);
            Assertions.assertNotNull(user1);

            userServiceImpl.deleteUser(user1.getId());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
