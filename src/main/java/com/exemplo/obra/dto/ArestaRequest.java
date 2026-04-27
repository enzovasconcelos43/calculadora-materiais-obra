package com.exemplo.obra.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Essa classe é utilizada para modelar e ser utilizada como referência da estrutura esperada para obter os atributos necessários para o objeto Aresta que compõe a planta

public class ArestaRequest {

    @NotBlank(message = "O id da aresta é obrigatório.")
    private String id;

    @NotBlank(message = "O vértice de origem é obrigatório.")
    private String verticeOrigem;

    @NotBlank(message = "O vértice de destino é obrigatório.")
    private String verticeDestino;

    @NotNull(message = "O comprimento é obrigatório.")
    @DecimalMin(value = "0.0001", message = "O comprimento deve ser maior que zero.")
    private Double comprimento;

    @NotNull(message = "A espessura é obrigatória.")
    @DecimalMin(value = "0.0001", message = "A espessura deve ser maior que zero.")
    private Double espessura;

    @NotNull(message = "A altura da parede é obrigatória.")
    @DecimalMin(value = "0.0001", message = "A altura da parede deve ser maior que zero.")
    private Double alturaParede;

    private boolean possuiPorta;
    private Double alturaPorta = 0.0;
    private Double larguraPorta = 0.0;

    private boolean possuiJanela;
    private Double alturaJanela = 0.0;
    private Double larguraJanela = 0.0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVerticeOrigem() {
        return verticeOrigem;
    }

    public void setVerticeOrigem(String verticeOrigem) {
        this.verticeOrigem = verticeOrigem;
    }

    public String getVerticeDestino() {
        return verticeDestino;
    }

    public void setVerticeDestino(String verticeDestino) {
        this.verticeDestino = verticeDestino;
    }

    public Double getComprimento() {
        return comprimento;
    }

    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento;
    }

    public Double getEspessura() {
        return espessura;
    }

    public void setEspessura(Double espessura) {
        this.espessura = espessura;
    }

    public Double getAlturaParede() {
        return alturaParede;
    }

    public void setAlturaParede(Double alturaParede) {
        this.alturaParede = alturaParede;
    }

    public boolean isPossuiPorta() {
        return possuiPorta;
    }

    public void setPossuiPorta(boolean possuiPorta) {
        this.possuiPorta = possuiPorta;
    }

    public Double getAlturaPorta() {
        return alturaPorta;
    }

    public void setAlturaPorta(Double alturaPorta) {
        this.alturaPorta = alturaPorta;
    }

    public Double getLarguraPorta() {
        return larguraPorta;
    }

    public void setLarguraPorta(Double larguraPorta) {
        this.larguraPorta = larguraPorta;
    }

    public boolean isPossuiJanela() {
        return possuiJanela;
    }

    public void setPossuiJanela(boolean possuiJanela) {
        this.possuiJanela = possuiJanela;
    }

    public Double getAlturaJanela() {
        return alturaJanela;
    }

    public void setAlturaJanela(Double alturaJanela) {
        this.alturaJanela = alturaJanela;
    }

    public Double getLarguraJanela() {
        return larguraJanela;
    }

    public void setLarguraJanela(Double larguraJanela) {
        this.larguraJanela = larguraJanela;
    }
}
