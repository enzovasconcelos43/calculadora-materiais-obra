package com.exemplo.obra.dto;

import java.math.BigDecimal;

// Essa classe é utilizada para modelar a estrutura de resposta do canclulo de consumo de tijolos a ser utilizado

public class TijoloResponse {

    private BigDecimal areaTotalParedes;
    private BigDecimal areaLiquidaParedes;
    private BigDecimal areaAberturas;
    private int quantidadeTijolos;
    private int quantidadeTijolosComPerda;
    private String mensagem;

    public TijoloResponse() {
    }

    public TijoloResponse(BigDecimal areaTotalParedes,
                          BigDecimal areaLiquidaParedes,
                          BigDecimal areaAberturas,
                          int quantidadeTijolos,
                          int quantidadeTijolosComPerda,
                          String mensagem) {
        this.areaTotalParedes = areaTotalParedes;
        this.areaLiquidaParedes = areaLiquidaParedes;
        this.areaAberturas = areaAberturas;
        this.quantidadeTijolos = quantidadeTijolos;
        this.quantidadeTijolosComPerda = quantidadeTijolosComPerda;
        this.mensagem = mensagem;
    }

    public BigDecimal getAreaTotalParedes() {
        return areaTotalParedes;
    }

    public void setAreaTotalParedes(BigDecimal areaTotalParedes) {
        this.areaTotalParedes = areaTotalParedes;
    }

    public BigDecimal getAreaLiquidaParedes() {
        return areaLiquidaParedes;
    }

    public void setAreaLiquidaParedes(BigDecimal areaLiquidaParedes) {
        this.areaLiquidaParedes = areaLiquidaParedes;
    }

    public BigDecimal getAreaAberturas() {
        return areaAberturas;
    }

    public void setAreaAberturas(BigDecimal areaAberturas) {
        this.areaAberturas = areaAberturas;
    }

    public int getQuantidadeTijolos() {
        return quantidadeTijolos;
    }

    public void setQuantidadeTijolos(int quantidadeTijolos) {
        this.quantidadeTijolos = quantidadeTijolos;
    }

    public int getQuantidadeTijolosComPerda() {
        return quantidadeTijolosComPerda;
    }

    public void setQuantidadeTijolosComPerda(int quantidadeTijolosComPerda) {
        this.quantidadeTijolosComPerda = quantidadeTijolosComPerda;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
