package com.exemplo.obra.service;

import com.exemplo.obra.entity.Orcamento;
import com.exemplo.obra.repository.OrcamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Serviço responsável por gerar o número do orçamento e salvá-lo no banco.
 * Centraliza essa regra para que os beans de tela fiquem simples.
 */
@Service
public class OrcamentoService {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("yyyyMMdd");

    /** Contador simples para compor um sufixo sequencial dentro da execução. */
    private final AtomicInteger contador = new AtomicInteger(0);

    private final OrcamentoRepository repository;

    public OrcamentoService(OrcamentoRepository repository) {
        this.repository = repository;
    }

    /**
     * Gera um número de orçamento legível e único no formato:
     * ORC-AAAAMMDD-0001
     */
    public String gerarNumero() {
        String data = LocalDateTime.now().format(FORMATO_DATA);
        int sequencial = contador.incrementAndGet();
        return String.format("ORC-%s-%04d", data, sequencial);
    }

    /** Monta e salva um novo orçamento no banco de dados. */
    public Orcamento salvar(String nomeUsuario, String tipoCalculo,
                            String descricaoEntrada, String resultado) {
        Orcamento orcamento = new Orcamento(
                gerarNumero(),
                nomeUsuario,
                tipoCalculo,
                descricaoEntrada,
                resultado,
                LocalDateTime.now()
        );
        return repository.save(orcamento);
    }
}
