package com.thoughtworks.aceleradora.quarto.dominio;

import org.springframework.data.repository.CrudRepository;

public interface QuartoRepository extends CrudRepository<Quarto,Long> {
    Quarto getOne(Long id);
}
