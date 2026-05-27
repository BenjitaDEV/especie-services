package com.caleta.especie.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateEspecieRequest (
    @NotBlank(message = "El nombre de la especie es obligatorio") String nombre,
    @NotBlank(message = "El estado de veda es obligatorio") boolean enVeda
){

}
