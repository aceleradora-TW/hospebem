package com.thoughtworks.aceleradora.solicitacao.controller;

import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.ResponseEntity.ok;


@Controller
@RequestMapping(value = "/solicitacao")
public class CsvControllers {

    private final SolicitacaoCsvService solicitacaoCsvService;

    @Autowired
    public CsvControllers(SolicitacaoCsvService solicitacaoCsvService){
        this.solicitacaoCsvService = solicitacaoCsvService;
    }

    @GetMapping(value = "/negadas/csv", produces = "text/csv")
    public ResponseEntity<String> negadas() {
        return constroiResposta(solicitacaoCsvService.solicitacoesNegadas());
    }

    @GetMapping(value = "/aceitas/csv", produces = "text/csv")
    public ResponseEntity<String> aceitas() {
        return constroiResposta(solicitacaoCsvService.solicitacoesAceitas());
    }

    private ResponseEntity<String> constroiResposta(String corpo) {
        return ok()
                .header(CONTENT_DISPOSITION, "attachement; filename=arquivo.csv")
                .body("Nome,Status,Genero,Data de Nascimento,Situação,Orgão,Rua,Número,Cidade,Bairro,UF,Cadeirante,Telefone \n"+ corpo);
    }


}

