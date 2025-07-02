package com.Efrain.udemy_apirest_prod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Efrain.udemy_apirest_prod.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor , Long >{

    
}
