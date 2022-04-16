package com.pract7.joins.controller;

import com.pract7.joins.model.PsicologoTable;
import com.pract7.joins.service.PsicologoService;
import com.pract7.joins.service.dto.PsicologoDTO;
import com.pract7.joins.service.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PsicologoController {

    @Autowired
    private PsicologoService psicologoService;

    @GetMapping("/psicologos")
    public ResponseEntity<List<PsicologoDTO>> getAllPsicologos(){
        List<PsicologoDTO> psicologos = new ArrayList<PsicologoDTO>();
        psicologos = psicologoService.getPsicologos();
        return ResponseEntity.ok().body(psicologos);
    }

    @GetMapping("/psicologos/{id}")
    public ResponseEntity<PsicologoDTO> getPsicologoById(@PathVariable("id") long id){
        PsicologoDTO psicologoEncontrado = null;
        List<PsicologoDTO> psicologos = new ArrayList<PsicologoDTO>();
        psicologos = psicologoService.getPsicologos();
        for (PsicologoDTO psicologo : psicologos){
            if (psicologo.getId() == id)
                psicologoEncontrado = psicologo;
        }
        return ResponseEntity.ok().body(psicologoEncontrado);
    }

    @GetMapping("/psicologos/freelance")
    public ResponseEntity<List<PsicologoDTO>> getAllPsicologosFreelance(){
        List<PsicologoDTO> psicologos = new ArrayList<PsicologoDTO>();
        psicologos = psicologoService.getPsicologosFreelance();
        return ResponseEntity.ok().body(psicologos);
    }

    @GetMapping("/psicologos/concentro")
    public ResponseEntity<Iterable<PsicologoTable>> getPsicologosConCentro(){
        Iterable<PsicologoTable> psicologos = psicologoService.getPsicologosConCentro();
        return ResponseEntity.ok().body(psicologos);
    }

    /*@DeleteMapping("/psicologos/{id}")
    public ResponseEntity<HttpStatus> deletePsicologoById(@PathVariable("id") long id){
        PsicologoDTO psicologoEncontrado = null;
        List<PsicologoDTO> psicologos = new ArrayList<PsicologoDTO>();
        psicologos = psicologoService.getPsicologos();
        for (PsicologoDTO psicologo : psicologos){
            if (psicologo.getId() == id)
                psicologoEncontrado = psicologo;
        }
        HttpStatus httpStatus;
        if (psicologoEncontrado != null) {
            httpStatus = HttpStatus.OK;
            psicologoService.deletePsicologo(id);
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.ok().body(httpStatus);
    }*/

    @DeleteMapping("/psicologos/{id}")
    public ResponseEntity<PsicologoDTO> deletePsicologoById(@PathVariable("id") long id){
        psicologoService.deletePsicologo(id);
        return ResponseEntity.noContent().build();
    }
}
