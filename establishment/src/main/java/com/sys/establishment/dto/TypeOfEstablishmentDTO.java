package com.sys.establishment.dto;

import java.io.Serializable;

public class TypeOfEstablishmentDTO implements Serializable {

    private Long id;

    private String type;

    public TypeOfEstablishmentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
