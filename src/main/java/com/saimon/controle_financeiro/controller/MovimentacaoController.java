package com.saimon.controle_financeiro.controller;

import com.saimon.controle_financeiro.dto.ResumoValoresDTO;
import com.saimon.controle_financeiro.Domain.model.Movimentacao;
import com.saimon.controle_financeiro.Domain.model.Usuario;
import com.saimon.controle_financeiro.Domain.repository.MovimentacaoRepository;
import com.saimon.controle_financeiro.Domain.repository.UsuarioRepository;
import com.saimon.controle_financeiro.dto.MovimentacaoDTO;
import com.saimon.controle_financeiro.exceptions.UserNotFoundException;
import com.saimon.controle_financeiro.service.MovimentacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final UsuarioRepository usuarioRepository;
    private final MovimentacaoRepository movimentacaoRepository;
    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService, UsuarioRepository usuarioRepository, MovimentacaoRepository movimentacaoRepository, MovimentacaoService movimentacaoService1){
        this.usuarioRepository = usuarioRepository;
        this.movimentacaoRepository = movimentacaoRepository;
	    this.movimentacaoService = movimentacaoService;
    }

    // CRIA MOVIMENTACAO NO BANCO
    @PostMapping
    public ResponseEntity<Movimentacao> create(@AuthenticationPrincipal String email, @RequestBody MovimentacaoDTO movimentacaoDTO) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setUsuario(usuario);
            movimentacao.setValorMovimentacao(movimentacaoDTO.getValorMovimentacao());
            movimentacao.setTipo(movimentacaoDTO.getTipo());
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

    //DELETANDO UMA MOVIMENTAÇÃO
    @DeleteMapping("/remove/{id}")
    public void removeMoviment(@PathVariable Long id) throws Exception {
        Movimentacao movimentacao = movimentacaoRepository.findById(id).orElseThrow(NoSuchFieldException::new);

        movimentacaoRepository.delete(movimentacao);
    }

    // GERA O RESUMO DOS TOTAIS DE VALORES DE ENTRADA E DE SAIDAS
    @GetMapping("/resumo")
    public ResponseEntity<ResumoValoresDTO> generateSummary(@AuthenticationPrincipal String email){
        ResumoValoresDTO resumo = movimentacaoService.recalcularValores(email);

        return ResponseEntity.ok(resumo);
    }
}
