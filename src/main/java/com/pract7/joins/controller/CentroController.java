package com.pract7.joins.controller;

import com.pract7.joins.service.CentroService;
import com.pract7.joins.service.dto.CentroDTO;
import com.pract7.joins.service.dto.PsicologoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CentroController {

    @Autowired
    private CentroService centroService;

    @GetMapping("/centros")
    public ResponseEntity<List<CentroDTO>> getAllCentros(){
        List<CentroDTO> centros = new ArrayList<CentroDTO>();
        centros = centroService.getCentros();
        return ResponseEntity.ok().body(centros);
    }
}
