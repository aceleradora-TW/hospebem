package com.thoughtworks.aceleradora.solicitacao.dominio;

import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import org.springframework.data.repository.CrudRepository;

public interface ListaSolicitacaoCasaRepository extends CrudRepository<Solicitacao, Long> {
}