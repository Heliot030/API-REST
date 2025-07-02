package com.Efrain.udemy_apirest_prod.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Efrain.udemy_apirest_prod.entity.Autor;
import com.Efrain.udemy_apirest_prod.repository.AutorRepository;

@Service
public class AutorService {

    public AutorRepository autorRepository;
    
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor save(Autor autor){

        return autorRepository.save(autor);
    }

    public List<Autor> findAll(){
        return autorRepository.findAll();
    }

    public Optional <Autor> findById(Long id){
        return autorRepository.findById(id);
    }

      public Boolean deleteById(Long id){
          return autorRepository.findById(id).map(
            autor ->  {
            autorRepository.delete(autor);
            return true;
         }
        ).orElse(false);
    }

    public Optional <Autor> update(Autor autor){
       
        return autorRepository.findById(autor.getId()).map(
        autorBD ->{
        autorBD.setNombre(autor.getNombre());  
        autorBD.setApellido(autor.getApellido());
        autorBD.setTelefono(autor.getTelefono());
        return autorRepository.save(autorBD);
        }

      );
    }

}
