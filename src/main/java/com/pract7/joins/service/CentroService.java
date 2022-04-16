package com.pract7.joins.service;

import com.pract7.joins.service.dto.CentroDTO;

import java.util.List;

public interface CentroService {
    //All data from Centros Table
    List<CentroDTO> getCentros();
}
