package com.exemplo.obra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

/**
 * Entidade que representa um ORÇAMENTO calculado e salvo no banco de dados.
 *
 * Atende a Etapa 1 / Passo 2 e 3 da atividade:
 * "Crie no banco de dados tabelas responsáveis por manter os orçamentos
 *  calculados, e os identificadores que permitam buscar orçamentos por meio
 *  do número de orçamento ou nome do usuário."
 *
 * Identificadores de busca: numero (número do orçamento) e nomeUsuario.
 */
@Entity
@Table(name = "orcamento")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Número do orçamento (identificador legível usado nas buscas). Ex.: ORC-20260629-0001 */
    @Column(nullable = false, unique = true, length = 40)
    private String numero;

    /** Nome do usuário/cliente (também usado como identificador de busca). */
    @Column(nullable = false, length = 120)
    private String nomeUsuario;

    /** Tipo do cálculo: CONCRETO ou TIJOLOS. */
    @Column(nullable = false, length = 20)
    private String tipoCalculo;

    /** Resumo dos dados de entrada informados pelo usuário (texto legível). */
    @Column(length = 1000)
    private String descricaoEntrada;

    /** Resultado calculado (texto legível). */
    @Column(length = 1000)
    private String resultado;

    /** Data e hora em que o orçamento foi gerado. */
    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    public Orcamento() {
    }

    public Orcamento(String numero, String nomeUsuario, String tipoCalculo,
                     String descricaoEntrada, String resultado, LocalDateTime dataCriacao) {
        this.numero = numero;
        this.nomeUsuario = nomeUsuario;
        this.tipoCalculo = tipoCalculo;
        this.descricaoEntrada = descricaoEntrada;
        this.resultado = resultado;
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTipoCalculo() {
        return tipoCalculo;
    }

    public void setTipoCalculo(String tipoCalculo) {
        this.tipoCalculo = tipoCalculo;
    }

    public String getDescricaoEntrada() {
        return descricaoEntrada;
    }

    public void setDescricaoEntrada(String descricaoEntrada) {
        this.descricaoEntrada = descricaoEntrada;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
