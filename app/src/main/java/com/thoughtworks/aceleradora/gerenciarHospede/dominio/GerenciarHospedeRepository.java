package com.thoughtworks.aceleradora.gerenciarHospede.dominio;


import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenciarHospedeRepository
        extends CrudRepository<Solicitacao,Long> {
    Solicitacao findBySituacao(String title);

}
