package com.caleta.especie.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateEspecieRequest (
    @NotBlank(message = "El nombre de la especie es obligatorio") String nombre,
    @NotNull(message = "El estado de veda es obligatorio") boolean enVeda

) {

}
