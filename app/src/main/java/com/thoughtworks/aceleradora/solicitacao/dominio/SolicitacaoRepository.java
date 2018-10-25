package com.thoughtworks.aceleradora.solicitacao.dominio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SolicitacaoRepository extends CrudRepository<Solicitacao,Long> {

}

