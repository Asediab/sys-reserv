package com.sys.user.dto;


import com.sys.user.model.Role;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class UserDTO implements Serializable {

    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private boolean active;

    private Long establishmentId;

    @NotEmpty
    private String password;

    private Set<Role> roles;

    @NotEmpty
    private String email;

    public UserDTO(@NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty String password, @NotEmpty String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.establishmentId = -1L;
        this.email = email;
        this.active = true;
    }

    public UserDTO(@NotEmpty String firstName, @NotEmpty String lastName, Long establishmentId, @NotEmpty String password, @NotEmpty String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.establishmentId = establishmentId;
        this.password = password;
        this.email = email;
        this.active = true;
    }

    public UserDTO() {
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(Long establishmentId) {
        this.establishmentId = establishmentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
