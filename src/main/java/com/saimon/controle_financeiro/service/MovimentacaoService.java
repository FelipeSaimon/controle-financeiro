package com.saimon.controle_financeiro.service;

import com.saimon.controle_financeiro.Domain.Enum.LogTipoEvento;
import com.saimon.controle_financeiro.audit.LoggerServiceInterface;
import com.saimon.controle_financeiro.audit.LoggerServiceInterface;
import com.saimon.controle_financeiro.dto.MovimentacaoDTO;
import com.saimon.controle_financeiro.dto.ResumoValoresDTO;
import com.saimon.controle_financeiro.Domain.Enum.TipoMovimentacao;
import com.saimon.controle_financeiro.Domain.model.Movimentacao;
import com.saimon.controle_financeiro.Domain.model.Usuario;
import com.saimon.controle_financeiro.Domain.repository.MovimentacaoRepository;
import com.saimon.controle_financeiro.Domain.repository.UsuarioRepository;
import com.saimon.controle_financeiro.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LoggerServiceInterface loggerService;
    private MovimentacaoDTO movimentacaoDTO;

    // Construtor
    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, UsuarioRepository usuarioRepository, LoggerServiceInterface loggerService){
        this.movimentacaoRepository = movimentacaoRepository;
        this.usuarioRepository = usuarioRepository;
	    this.loggerService = loggerService;
    }

    // Cria uma movimentação
    public Movimentacao create(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário com ID " + id + " não encontrado"));

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setUsuario(usuario);
        movimentacao.setDataDeCriacao(LocalDateTime.now());

        loggerService.logEvento(usuario, LogTipoEvento.MOVIMENTACAO_CRIADA, "Movimentação criada com ID " + movimentacao.getId());

        return movimentacaoRepository.save(movimentacao);
    }

    // Lista as movimentações
    public List<Movimentacao> findAll(String email){
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário com email " + email + " não encontrado"));

        return movimentacaoRepository.findAllByUsuarioEmail(email);
    }

    // Remover movimentacao da lista
    public void removeMovement(Long id){
        movimentacaoRepository.findById(id);
    }

    // --------------------------------------------------------------------
    // Recalcula o total de entrada e saida
    public ResumoValoresDTO recalcularValores(String email){
        List<Movimentacao> movimentacoes = movimentacaoRepository.findAllByUsuarioEmail(email);

        BigDecimal totalEntradas = BigDecimal.ZERO;
        BigDecimal totalSaidas = BigDecimal.ZERO;

        for (Movimentacao movimentacao : movimentacoes){
            if (movimentacao.getTipo() == TipoMovimentacao.ENTRADA){
                totalEntradas = totalEntradas.add(movimentacao.getValorMovimentacao());
            }
            if (movimentacao.getTipo() == TipoMovimentacao.SAIDA){
                totalSaidas = totalSaidas.add(movimentacao.getValorMovimentacao());
            }
        }

        ResumoValoresDTO resumoValoresDTO = new ResumoValoresDTO();
        resumoValoresDTO.setTotalEntradas(totalEntradas);
        resumoValoresDTO.setTotalSaidas(totalSaidas);
        resumoValoresDTO.setTotal(totalEntradas.add(totalSaidas));

        return resumoValoresDTO;
    }
}
