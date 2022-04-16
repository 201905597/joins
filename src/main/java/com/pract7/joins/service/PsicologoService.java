package com.pract7.joins.service;

import com.pract7.joins.model.PsicologoTable;
import com.pract7.joins.service.dto.PsicologoDTO;

import java.util.List;

public interface PsicologoService {

    //All data from Psicologos Table
    List<PsicologoDTO> getPsicologos();

    //Psicologos Freelance
    List<PsicologoDTO> getPsicologosFreelance();

    //Borrar psicologos
    void deletePsicologo(Long id);

    //Get Psicologos con Centro asociado
    Iterable<PsicologoTable> getPsicologosConCentro();

    //Insertar Psicologos
    //void addPsicologo(Long id, String psic_name,String psic_pwd, String employer);
}
