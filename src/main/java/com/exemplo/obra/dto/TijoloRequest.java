package com.exemplo.obra.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

// Essa classe é utilizada para modelar a estrutura de especificação do tijolo a ser utilizado

public class TijoloRequest {

    @Valid
    @NotEmpty(message = "A lista de arestas não pode estar vazia.")
    private List<ArestaRequest> arestas;

    @NotNull(message = "A altura do tijolo é obrigatória.")
    @DecimalMin(value = "0.0001", message = "A altura do tijolo deve ser maior que zero.")
    private Double alturaTijolo;

    @NotNull(message = "A largura do tijolo é obrigatória.")
    @DecimalMin(value = "0.0001", message = "A largura do tijolo deve ser maior que zero.")
    private Double larguraTijolo;

    @NotNull(message = "O percentual de perda é obrigatório.")
    @DecimalMin(value = "0.0", message = "O percentual de perda não pode ser negativo.")
    private Double percentualPerda;

    public List<ArestaRequest> getArestas() {
        return arestas;
    }

    public void setArestas(List<ArestaRequest> arestas) {
        this.arestas = arestas;
    }

    public Double getAlturaTijolo() {
        return alturaTijolo;
    }

    public void setAlturaTijolo(Double alturaTijolo) {
        this.alturaTijolo = alturaTijolo;
    }

    public Double getLarguraTijolo() {
        return larguraTijolo;
    }

    public void setLarguraTijolo(Double larguraTijolo) {
        this.larguraTijolo = larguraTijolo;
    }

    public Double getPercentualPerda() {
        return percentualPerda;
    }

    public void setPercentualPerda(Double percentualPerda) {
        this.percentualPerda = percentualPerda;
    }
}
