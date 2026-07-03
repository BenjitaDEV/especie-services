package com.caleta.especie.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caleta.especie.dto.CreateEspecieRequest;
import com.caleta.especie.dto.UpdateEspecieRequest;
import com.caleta.especie.exception.ResourceNotFoundException;
import com.caleta.especie.mapper.EspecieMapper;
import com.caleta.especie.model.Especie;
import com.caleta.especie.service.EspecieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/especies")
@Tag(name = "Especies", description = "API para gestionar especies")
public class EspecieController {

    private final EspecieService especieService;

    public EspecieController(EspecieService especieService) {
        this.especieService = especieService;
    }

    @Operation(
            summary = "Listar especies",
            description = "Obtiene todas las especies registradas"
    )
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<Especie>> listarEspecies() {

        List<Especie> especies = especieService.getEspecies();

        return ResponseEntity.ok(especies);
    }

    @Operation(
            summary = "Crear especie",
            description = "Registra una nueva especie"
    )
    @ApiResponse(responseCode = "201", description = "Especie creada correctamente")
    @PostMapping
    public ResponseEntity<Especie> agregarEspecie(

            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos para registrar una especie",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de creación",
                                    summary = "Crear especie",
                                    value = """
                                    {
                                      "nombre": "Merluza",
                                      "enVeda": false
                                    }
                                    """
                            )
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody CreateEspecieRequest request) {

        Especie nuevaEspecie = especieService.saveEspecie(EspecieMapper.toModel(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEspecie);
    }

    @Operation(
            summary = "Buscar especie por ID",
            description = "Obtiene una especie mediante su ID"
    )
    @ApiResponse(responseCode = "200", description = "Especie encontrada")
    @ApiResponse(responseCode = "404", description = "Especie no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Especie> buscarEspecie(@PathVariable Long id) {

        Especie especie = especieService.getEspecieById(id);

        if (especie == null) {
            throw new ResourceNotFoundException("Especie no encontrada por id: " + id);
        }

        return ResponseEntity.ok(especie);
    }

    @Operation(
            summary = "Actualizar especie",
            description = "Actualiza la información de una especie"
    )
    @ApiResponse(responseCode = "200", description = "Especie actualizada correctamente")
    @ApiResponse(responseCode = "404", description = "Especie no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<Especie> actualizarEspecie(

            @PathVariable Long id,

            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos para actualizar una especie",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de actualización",
                                    summary = "Actualizar especie",
                                    value = """
                                    {
                                      "nombre": "Jurel",
                                      "enVeda": true
                                    }
                                    """
                            )
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody UpdateEspecieRequest request) {

        Especie especie = EspecieMapper.toModel(id, request);

        Especie especieActualizada = especieService.updateEspecie(id, especie);

        if (especieActualizada == null) {
            throw new ResourceNotFoundException("Especie no encontrada por id: " + id);
        }

        return ResponseEntity.ok(especieActualizada);
    }

    @Operation(
            summary = "Eliminar especie",
            description = "Elimina una especie por su ID"
    )
    @ApiResponse(responseCode = "204", description = "Especie eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Especie no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEspecie(@PathVariable Long id) {

        especieService.deleteEspecie(id);

        return ResponseEntity.noContent().build();
    }

}