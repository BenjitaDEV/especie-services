package com.caleta.especie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caleta.especie.model.Especie;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long> {

    @Query(value = "SELECT * FROM especies WHERE nombre_especie = :nombre", nativeQuery = true)
    List<Especie> selectporNombre(@Param("nombre") String nombre);

    default int totalEspecies(){
        return (int) this.count();
    }
}
