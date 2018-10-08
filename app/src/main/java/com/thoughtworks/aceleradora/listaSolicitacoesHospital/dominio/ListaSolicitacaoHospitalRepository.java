package com.thoughtworks.aceleradora.listaSolicitacoesHospital.dominio;

import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import org.springframework.data.repository.CrudRepository;

public interface ListaSolicitacaoHospitalRepository extends CrudRepository<Solicitacao, Long> {
}
