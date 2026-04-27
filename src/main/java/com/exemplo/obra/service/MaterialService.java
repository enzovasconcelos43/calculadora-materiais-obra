package com.exemplo.obra.service;

import com.exemplo.obra.dto.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class MaterialService {

    
    public ConcretoResponse calcularVolumeConcreto(ConcretoRequest request) {

        List<ArestaRequest> arestas = request.getArestas();
        double alturaViga = request.getAlturaViga();

        double volumeTotal = 0.0;

        for (ArestaRequest aresta : arestas) {
            // Cada parede contribui com: espessura × altura da viga × comprimento
            double volumeAresta = aresta.getEspessura()
                                * alturaViga
                                * aresta.getComprimento();

            volumeTotal += volumeAresta;
        }

        // Arredonda para 4 casas decimais (padrão de engenharia)
        BigDecimal volumeArredondado = BigDecimal
                .valueOf(volumeTotal)
                .setScale(4, RoundingMode.HALF_UP);

        return new ConcretoResponse(
                volumeArredondado,
                arestas.size(),
                "Volume de concreto calculado com sucesso."
        );
    }

    
    public TijoloResponse calcularQuantidadeTijolos(TijoloRequest request) {

        List<ArestaRequest> arestas = request.getArestas();
        double alturaTijolo   = request.getAlturaTijolo();
        double larguraTijolo  = request.getLarguraTijolo();
        double percentualPerda = request.getPercentualPerda();

        double areaTotalParedes = 0.0;
        double areaAberturas    = 0.0;

        for (ArestaRequest aresta : arestas) {

            // 1) Área bruta desta parede
            double areaBruta = aresta.getComprimento() * aresta.getAlturaParede();
            areaTotalParedes += areaBruta;

            // 2) Desconta a porta (se houver)
            if (aresta.isPossuiPorta()) {
                areaAberturas += aresta.getLarguraPorta() * aresta.getAlturaPorta();
            }

            // 3) Desconta a janela (se houver)
            if (aresta.isPossuiJanela()) {
            areaAberturas += aresta.getLarguraJanela() * aresta.getAlturaJanela();
            }
        }

        
        double areaLiquida = areaTotalParedes - areaAberturas;

        
        double areaTijolo = larguraTijolo * alturaTijolo;

        
        int quantidadeSemPerda = (int) Math.ceil(areaLiquida / areaTijolo);

        
        int quantidadeComPerda = (int) Math.ceil(quantidadeSemPerda * (1.0 + percentualPerda / 100.0));

        
        BigDecimal bdAreaTotal    = BigDecimal.valueOf(areaTotalParedes).setScale(4, RoundingMode.HALF_UP);
        BigDecimal bdAreaLiquida  = BigDecimal.valueOf(areaLiquida).setScale(4, RoundingMode.HALF_UP);
        BigDecimal bdAreaAbertura = BigDecimal.valueOf(areaAberturas).setScale(4, RoundingMode.HALF_UP);

        return new TijoloResponse(
                bdAreaTotal,
                bdAreaLiquida,
                bdAreaAbertura,
                quantidadeSemPerda,
                quantidadeComPerda,
                "Quantidade de tijolos calculada com sucesso."
        );
    }
}