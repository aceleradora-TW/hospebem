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
        return ok()
                .header(CONTENT_DISPOSITION, "attachement; filename=arquivo.csv")
                .body(solicitacaoCsvService.solicitacoesRelatorio());
    }

}

