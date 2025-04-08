package com.saimon.controle_financeiro.controller;

import com.saimon.controle_financeiro.Domain.model.Movimentacao;
import com.saimon.controle_financeiro.Domain.model.Usuario;
import com.saimon.controle_financeiro.Domain.repository.MovimentacaoRepository;
import com.saimon.controle_financeiro.Domain.repository.UsuarioRepository;
import com.saimon.controle_financeiro.DTO.MovimentacaoDTO;
import com.saimon.controle_financeiro.service.MovimentacaoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;
    private final UsuarioRepository usuarioRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoController(MovimentacaoService movimentacaoService, UsuarioRepository usuarioRepository, MovimentacaoRepository movimentacaoRepository){
        this.movimentacaoService = movimentacaoService;
        this.usuarioRepository = usuarioRepository;
        this.movimentacaoRepository = movimentacaoRepository;

    }

    @PostMapping
    public ResponseEntity<Movimentacao> create(@AuthenticationPrincipal String email, @RequestBody MovimentacaoDTO movimentacaoDTO) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setUsuario(usuario);
        movimentacao.setValorMovimentacao(movimentacaoDTO.getValorMovimentacao());
        movimentacao.setDataDeCriacao(LocalDateTime.now());

        Movimentacao savedMovimentacao = movimentacaoRepository.save(movimentacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovimentacao);
    }

    // BUSCAR TODAS MOVIMENTAÇÕES PELO USUARIO (AUTENTICADO)
    @GetMapping
    public ResponseEntity<List<Movimentacao>> findAll(@AuthenticationPrincipal String email){
        List<Movimentacao> movimentacoes = movimentacaoRepository.findAllByUsuarioEmail(email);
        return ResponseEntity.ok(movimentacoes);
    }

}
