package com.sys.user.web.service.impl;

import com.sys.user.dao.UserDAO;
import com.sys.user.dto.UserDTO;
import com.sys.user.model.Role;
import com.sys.user.model.User;
import com.sys.user.web.exeption.NotFoundException;
import com.sys.user.web.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserDAO repository;

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public UserDTO createUser(UserDTO user) {

        if (repository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User already exists: " + user.getEmail());
        }
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        user.setActive(Boolean.TRUE);
        if (user.getEstablishmentId() != null && user.getEstablishmentId() > 0) {
            user.setRoles(Role.EMPLOYEE);
            LOGGER.info("Employee was created");
        } else if (user.getEstablishmentId() != null && user.getEstablishmentId() < 0) {
            user.setRoles(Role.USER);
            LOGGER.info("User was created");
        } else {
            user.setRoles(Role.ADMIN);
            LOGGER.info("Admin was created");
        }
        return toDto(repository.save(toEntity(user)));
    }

    @Override
    public UserDTO findById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException("User with this Id not found"));
        return toDto(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException("User with this Id not found"));
        if (user != null) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserDTO> usersByEstablishmentId(Long establishmentId) {
        List<UserDTO> dtos = new ArrayList<>();
        List<User> entityList = repository.findAllByEstablishmentIdAndActiveIsTrue(establishmentId);

        for (User e : entityList) {
            dtos.add(toDto(e));
        }

        if (!dtos.isEmpty()) {
            return dtos;
        } else {
            LOGGER.error("Data Base is empty");
            throw new NotFoundException("Data Base is empty");
        }
    }

    private UserDTO toDto(User userToDto) {
        UserDTO userDTO = modelMapper.map(userToDto, UserDTO.class);
        userDTO.setPassword("******");
        return userDTO;
    }

    private User toEntity(UserDTO userDTOToEntity) {
        User user = modelMapper.map(userDTOToEntity, User.class);
        return user;
    }
}

