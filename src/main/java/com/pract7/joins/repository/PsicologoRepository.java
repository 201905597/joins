package com.pract7.joins.repository;

import com.pract7.joins.model.PsicologoTable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface PsicologoRepository extends CrudRepository<PsicologoTable,Long> {
    @Query("SELECT * FROM PSICOLOGOS P, CENTROS C WHERE P.EMPLOYER_ID = C.EMPLOYER_ID")
    public Iterable<PsicologoTable> getPsicologosConCentro();
}
