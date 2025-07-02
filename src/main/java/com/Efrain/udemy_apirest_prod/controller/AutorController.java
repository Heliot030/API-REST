package com.Efrain.udemy_apirest_prod.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.Efrain.udemy_apirest_prod.dto.AutorDTO;
import com.Efrain.udemy_apirest_prod.entity.Autor;
import com.Efrain.udemy_apirest_prod.service.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorController {
    private AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    // Maneja tipo POST
    @PostMapping
    public ResponseEntity<AutorDTO> save(@RequestBody AutorDTO autorDto) {
        //DTO a autor
        Autor autor = new Autor();
        autor.setNombre(autorDto.getNombre());
        autor.setApellido(autorDto.getApellido());
        autor.setTelefono(autorDto.getTelefono());
        // guardamos  en BDD
        Autor autorSaved =  autorService.save(autor);

        //Autor a Autor DTO
        AutorDTO autorDTOSaved = new AutorDTO();
        autorDTOSaved.setId(autorSaved.getId());
        autorDTOSaved.setNombre(autorSaved.getNombre());
        autorDTOSaved.setApellido(autorSaved.getTelefono());

        return new ResponseEntity<> (autorDTOSaved, HttpStatus.CREATED);
    }

    // entrgea todos los registros con http:.../autores
    @GetMapping
    public ResponseEntity<List<AutorDTO>> findAll(){
        List<AutorDTO> autoresDTOS = autorService.findAll().stream().map(
            autor -> {
                AutorDTO autorDTO = new AutorDTO();
                autorDTO.setId(autor.getId());
                autorDTO.setNombre(autor.getNombre());
                autorDTO.setApellido(autor.getApellido());
                autorDTO.setTelefono(autor.getTelefono());
                return autorDTO;
            }
        ).collect(Collectors.toList());
        if (autoresDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(autoresDTOS);
    }

    // entrgea por id los registros con http:.../autores/1
    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> findById(@PathVariable Long id) {

        Optional<Autor> autorOptional = autorService.findById(id);
        return autorOptional.map(
            autorDB-> {
                AutorDTO autorDTO = new AutorDTO();
                autorDTO.setId(autorDB.getId());
                autorDTO.setNombre(autorDB.getNombre());
                autorDTO.setApellido(autorDB.getApellido());
                autorDTO.setTelefono(autorDB.getTelefono());
                return ResponseEntity.ok(autorDTO);

            }
        
        ).orElseGet(
            ()-> ResponseEntity.notFound().build()
        );
    }

    // entrgea por id los registros con http:.../autores/1
    @PutMapping
    public ResponseEntity<AutorDTO> update(@RequestBody AutorDTO autorDTO) {
        //autor a autordto
         Autor autor = new Autor();
        autor.setId(autorDTO.getId());
        autor.setNombre(autorDTO.getNombre());
        autor.setApellido(autorDTO.getApellido());
        autor.setTelefono(autorDTO.getTelefono());

        return autorService.update(autor).map(

        autorUpdate ->{
            AutorDTO autorDTOUpdate = new AutorDTO();
            autorDTOUpdate.setId(autorDTOUpdate.getId());
            autorDTOUpdate.setNombre(autorDTOUpdate.getNombre());
            autorDTOUpdate.setApellido(autorDTOUpdate.getApellido());
            autorDTOUpdate.setTelefono(autorDTOUpdate.getTelefono());
            return ResponseEntity.ok(autorDTOUpdate);
        }
            
        ).orElseGet(
            ()-> ResponseEntity.notFound().build());
        
            
    }

    // entrgea por id los registros con http:.../autores/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (autorService.deleteById(id))
        return ResponseEntity.noContent().build();
    return ResponseEntity.notFound().build();
    }
}
