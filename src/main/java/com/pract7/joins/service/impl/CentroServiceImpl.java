package com.pract7.joins.service.impl;

import com.pract7.joins.repository.CentroRepository;
import com.pract7.joins.service.CentroService;
import com.pract7.joins.service.dto.CentroDTO;
import com.pract7.joins.service.dto.PsicologoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class CentroServiceImpl implements CentroService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CentroRepository centroRepository;

    @Override
    public List<CentroDTO> getCentros() {
        return StreamSupport.stream(centroRepository.findAll().spliterator(),false)
                .map(obj -> new CentroDTO(
                        obj.getEmployerId(),
                        obj.getEmployerName(),
                        obj.getPostalCode()))
                .toList();
    }
}
