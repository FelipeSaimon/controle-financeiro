package com.saimon.controle_financeiro.controller;

import com.saimon.controle_financeiro.Domain.model.Movimentacao;
import com.saimon.controle_financeiro.service.MovimentacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService){
        this.movimentacaoService = movimentacaoService;
    }


    @PostMapping
    public ResponseEntity<Movimentacao> save(Long id){
        var movimentacao = movimentacaoService.create(id);

        return ResponseEntity.ok(movimentacao);
    }


}
