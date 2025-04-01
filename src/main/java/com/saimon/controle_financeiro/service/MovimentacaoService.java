package com.saimon.controle_financeiro.service;

import com.saimon.controle_financeiro.Domain.model.Movimentacao;
import com.saimon.controle_financeiro.Domain.model.Usuario;
import com.saimon.controle_financeiro.Domain.repository.MovimentacaoRepository;
import com.saimon.controle_financeiro.Domain.repository.UsuarioRepository;
import com.saimon.controle_financeiro.controller.DTO.MovimentacaoDTO;
import com.saimon.controle_financeiro.controller.MovimentacaoController;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, UsuarioRepository usuarioRepository){
        this.movimentacaoRepository = movimentacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Movimentacao create(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado"));

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setUsuario(usuario);
        movimentacao.setDataDeCriacao(LocalDateTime.now());

        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> findByUsuarioId(Long id){
        return movimentacaoRepository.findAll();
    }


}
