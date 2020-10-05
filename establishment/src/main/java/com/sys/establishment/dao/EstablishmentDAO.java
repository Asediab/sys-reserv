package com.sys.establishment.dao;

import com.sys.establishment.model.Establishment;
import com.sys.establishment.model.TypeOfEstablishment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstablishmentDAO extends JpaRepository<Establishment, Long>{

    List<Establishment> findAllByTypeOfEstablishmentOrderByNameDesc(TypeOfEstablishment typeOfEstablishment);

    List<Establishment> findAllByNameContainingOrderByNameDesc(String name);

    List<Establishment> findAllByNameContainingAndTypeOfEstablishmentOrderByNameDesc(String name, TypeOfEstablishment typeOfEstablishment);
}
