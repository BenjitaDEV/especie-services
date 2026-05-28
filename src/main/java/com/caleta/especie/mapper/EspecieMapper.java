package com.caleta.especie.mapper;

import com.caleta.especie.dto.CreateEspecieRequest;
import com.caleta.especie.dto.UpdateEspecieRequest;
import com.caleta.especie.model.Especie;

public class EspecieMapper {

    //CREATE
    public static Especie toModel(CreateEspecieRequest request){
        return new Especie(
                0,
                request.nombre(),
                request.enVeda()
                );
    }

    //UPDATE
    public static Especie toModel(long id, UpdateEspecieRequest request){
        return new Especie(
                id,
                request.nombre(),
                request.enVeda()
        );
    }



}
