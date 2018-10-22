package com.thoughtworks.aceleradora.solicitacao.dominio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcompanhanteRepository extends CrudRepository<Acompanhante, Long>{

}
