package com.caleta.especie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caleta.especie.model.Especie;
import com.caleta.especie.repository.EspecieRepository;

@Service
public class EspecieService {
    @Autowired
    private EspecieRepository especieRepository;

    public List<Especie> getEspecies() {
        return especieRepository.findAll();
    }

    public Especie saveEspecie(Especie especie){
        //No se pueden guardar especies con el mismo nombre
        if(!especieRepository.selectporNombre(especie.getNombre()).isEmpty()){
            throw new RuntimeException("la especie ya existe");
        }

        return especieRepository.save(especie);
    }

    public Especie getEspecieById(Long id) {
        return especieRepository.findById(id).orElse(null);
    }

    //Consistencia update
public Especie updateEspecie(Long id, Especie especieAct) {

    Especie especie = especieRepository.findById(id).orElseThrow(() -> new RuntimeException("Especie no encontrada"));

    especie.setNombre(especieAct.getNombre());
    especie.setEnVeda(especieAct.isEnVeda());

    return especieRepository.save(especie);
}

    public String deleteEspecie(long id){
        especieRepository.deleteById(id);
        return "Especie" + id + " eliminada";
    }
}
