package com.caleta.especie.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caleta.especie.dto.CreateEspecieRequest;
import com.caleta.especie.dto.UpdateEspecieRequest;
import com.caleta.especie.exception.ResourceNotFoundException;
import com.caleta.especie.mapper.EspecieMapper;
import com.caleta.especie.model.Especie;
import com.caleta.especie.service.EspecieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/especies")
public class EspecieController {

    private final EspecieService especieService;

    public EspecieController(EspecieService especieService) {
        this.especieService = especieService;
    }

    @GetMapping
    public ResponseEntity<List<Especie>> listarEspecies(){
        List<Especie> especies = especieService.getEspecies();
        return ResponseEntity.ok(especies);
    }

    @PostMapping
    public ResponseEntity<Especie> agregarEspecie(@Valid @RequestBody CreateEspecieRequest request){
        Especie nuevaEspecie = especieService.saveEspecie(EspecieMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEspecie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especie> buscarEspecie(@PathVariable long id){
        Especie especie = especieService.getEspecieById(id);
        if(especie == null){
            throw new ResourceNotFoundException("Especie no encontrado por id: " + id);
        }
        return ResponseEntity.ok(especie);
    }

@PutMapping("/{id}")
public ResponseEntity<Especie> actualizarEspecie(
        @PathVariable Long id,
        @RequestBody UpdateEspecieRequest request) {

    Especie especie = EspecieMapper.toModel(id, request);

    Especie especieActualizada = especieService.updateEspecie(id, especie);

    return ResponseEntity.ok(especieActualizada);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminarEspecie(@PathVariable Long id){
    especieService.deleteEspecie(id);
    return ResponseEntity.noContent().build();
}

}
