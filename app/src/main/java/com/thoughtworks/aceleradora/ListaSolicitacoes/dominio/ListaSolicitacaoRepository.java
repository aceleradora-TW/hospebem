package com.thoughtworks.aceleradora.ListaSolicitacoes.dominio;

import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import org.springframework.data.repository.CrudRepository;

public interface ListaSolicitacaoRepository extends CrudRepository<Solicitacao, Long> {
}
