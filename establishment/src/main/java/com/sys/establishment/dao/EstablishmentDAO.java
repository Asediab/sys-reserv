package com.sys.establishment.dao;

import com.sys.establishment.model.Establishment;
import com.sys.establishment.model.TypeOfEstablishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface EstablishmentDAO extends JpaRepository<Establishment, Long>, QuerydslPredicateExecutor<Establishment> {

    List<Establishment> findAllByTypeOfEstablishmentOrderByNameDesc(TypeOfEstablishment typeOfEstablishment);

    List<Establishment> findAllByNameOrderByNameDesc(String name);

    List<Establishment> findAllByNameAndTypeOfEstablishmentOrderByNameDesc(String name, TypeOfEstablishment typeOfEstablishment);
}
