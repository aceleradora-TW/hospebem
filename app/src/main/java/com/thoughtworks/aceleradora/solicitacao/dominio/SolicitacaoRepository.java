package com.thoughtworks.aceleradora.solicitacao.dominio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface SolicitacaoRepository
        extends JpaRepository<Solicitacao,Long> {
List<Solicitacao> findAllByStatus(String title);
List<Solicitacao> findAllByNomeSolicitante(String nome);

}
