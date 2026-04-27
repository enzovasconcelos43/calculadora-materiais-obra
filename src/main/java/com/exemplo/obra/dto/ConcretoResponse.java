package com.exemplo.obra.dto;

import java.math.BigDecimal;

public class ConcretoResponse {

    private BigDecimal volumeTotal;
    private int quantidadeArestasProcessadas;
    private String mensagem;

    // Essa classe é utilizada para modelar a estrutura da resposta do Volume da Viga Baldrame

    public ConcretoResponse() {
    }

    public ConcretoResponse(BigDecimal volumeTotal, int quantidadeArestasProcessadas, String mensagem) {
        this.volumeTotal = volumeTotal;
        this.quantidadeArestasProcessadas = quantidadeArestasProcessadas;
        this.mensagem = mensagem;
    }

    public BigDecimal getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(BigDecimal volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public int getQuantidadeArestasProcessadas() {
        return quantidadeArestasProcessadas;
    }

    public void setQuantidadeArestasProcessadas(int quantidadeArestasProcessadas) {
        this.quantidadeArestasProcessadas = quantidadeArestasProcessadas;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
