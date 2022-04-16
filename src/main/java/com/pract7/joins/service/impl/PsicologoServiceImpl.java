package com.pract7.joins.service.impl;

import com.pract7.joins.model.PsicologoTable;
import com.pract7.joins.repository.PsicologoRepository;
import com.pract7.joins.service.PsicologoService;
import com.pract7.joins.service.dto.PsicologoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class PsicologoServiceImpl implements PsicologoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PsicologoRepository psicologoRepository;

    @Override
    public List<PsicologoDTO> getPsicologos() {
        return StreamSupport.stream(psicologoRepository.findAll().spliterator(),false)
                .map(obj -> new PsicologoDTO(
                        obj.getId(),
                        obj.getPsicName(),
                        obj.getPsicPwd(),
                        obj.getEmployerId()))
                .toList();
    }

    @Override
    public List<PsicologoDTO> getPsicologosFreelance() {

        String query=
                """
                SELECT ID, PSIC_NAME, PSIC_PWD, EMPLOYER
                FROM PSICOLOGOS 
                WHERE EMPLOYER_ID = 0;
                """;

        List<PsicologoDTO> psicologosFreelance = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new PsicologoDTO(
                                rs.getLong("ID"),
                                rs.getString("PSIC_NAME"),
                                rs.getString("PSIC_PWD"),
                                rs.getLong("EMPLOYER_ID"))
        );

        return psicologosFreelance;
    }

    /*@Override
    public void deletePsicologo(Long id){
        String query = "DELETE FROM PSICOLOGOS WHERE ID = " + id + ";";
        jdbcTemplate.update(query);
    }*/

    @Override
    public void deletePsicologo(Long id){
        psicologoRepository.deleteById(id);
    }

    @Override
    public Iterable<PsicologoTable> getPsicologosConCentro(){
        return psicologoRepository.getPsicologosConCentro();
    }
}
