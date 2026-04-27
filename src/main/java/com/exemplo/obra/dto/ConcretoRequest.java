package com.exemplo.obra.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

// Essa classe é utilizada para modelar e ser utilizada como referência da estrutura esperada para obter os atributos necessários para o objeto Viga Baldrame

public class ConcretoRequest {

    @Valid
    @NotEmpty(message = "A lista de arestas não pode estar vazia.")
    private List<ArestaRequest> arestas;

    @NotNull(message = "A altura da viga é obrigatória.")
    @DecimalMin(value = "0.0001", message = "A altura da viga deve ser maior que zero.")
    private Double alturaViga;

    public List<ArestaRequest> getArestas() {
        return arestas;
    }

    public void setArestas(List<ArestaRequest> arestas) {
        this.arestas = arestas;
    }

    public Double getAlturaViga() {
        return alturaViga;
    }

    public void setAlturaViga(Double alturaViga) {
        this.alturaViga = alturaViga;
    }
}
