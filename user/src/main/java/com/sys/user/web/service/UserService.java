package com.sys.user.web.service;

import com.sys.user.dto.UserDTO;
import com.sys.user.model.User;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);

    User findByEmail(String email);

    UserDTO findById(Long id);

    void deactivatedEmployById(Long employeeId);

    void activateEmployeeById(Long employeeId);

    List<UserDTO> usersByEstablishmentId(Long establishmentId);

}
