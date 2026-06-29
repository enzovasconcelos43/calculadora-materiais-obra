package com.exemplo.obra.web;

import com.exemplo.obra.dto.ArestaRequest;
import com.exemplo.obra.dto.TijoloRequest;
import com.exemplo.obra.dto.TijoloResponse;
import com.exemplo.obra.entity.Orcamento;
import com.exemplo.obra.service.MaterialService;
import com.exemplo.obra.service.OrcamentoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean de tela (front-end JSF) para o cálculo de QUANTIDADE DE TIJOLOS.
 * Coleta os dados, chama o back-end existente (MaterialService) e salva o orçamento.
 */
@Component("tijoloBean")
@SessionScope
public class TijoloBean implements Serializable {

    private final MaterialService materialService;
    private final OrcamentoService orcamentoService;

    // ----- Dados de entrada -----
    private String nomeUsuario;
    private Double alturaTijolo = 0.20;
    private Double larguraTijolo = 0.30;
    private Double percentualPerda = 10.0;
    private List<ArestaRequest> paredes = new ArrayList<>();

    // ----- Resultado -----
    private TijoloResponse resposta;
    private String numeroOrcamento;

    public TijoloBean(MaterialService materialService, OrcamentoService orcamentoService) {
        this.materialService = materialService;
        this.orcamentoService = orcamentoService;
    }

    @PostConstruct
    public void init() {
        adicionarParede();
    }

    public void adicionarParede() {
        ArestaRequest parede = new ArestaRequest();
        parede.setId("P" + (paredes.size() + 1));
        parede.setVerticeOrigem("V" + (paredes.size() + 1));
        parede.setVerticeDestino("V" + (paredes.size() + 2));
        paredes.add(parede);
    }

    public void removerParede(ArestaRequest parede) {
        paredes.remove(parede);
        if (paredes.isEmpty()) {
            adicionarParede();
        }
    }

    public void calcular() {
        if (!validar()) {
            this.resposta = null;
            return;
        }
        try {
            TijoloRequest request = new TijoloRequest();
            request.setAlturaTijolo(alturaTijolo);
            request.setLarguraTijolo(larguraTijolo);
            request.setPercentualPerda(percentualPerda);
            request.setArestas(paredes);

            this.resposta = materialService.calcularQuantidadeTijolos(request);

            String entrada = String.format(
                    "Tijolo: %.2f x %.2f m | Perda: %.1f%% | Paredes: %d",
                    larguraTijolo, alturaTijolo, percentualPerda, paredes.size());
            String resultadoTexto = String.format(
                    "Tijolos (com perda): %d | sem perda: %d | área líquida: %s m²",
                    resposta.getQuantidadeTijolosComPerda(),
                    resposta.getQuantidadeTijolos(),
                    resposta.getAreaLiquidaParedes());

            Orcamento salvo = orcamentoService.salvar(
                    nomeUsuario, "TIJOLOS", entrada, resultadoTexto);
            this.numeroOrcamento = salvo.getNumero();

            addMensagem(FacesMessage.SEVERITY_INFO,
                    "Orçamento " + numeroOrcamento + " calculado e salvo com sucesso!");
        } catch (Exception e) {
            this.resposta = null;
            addMensagem(FacesMessage.SEVERITY_ERROR,
                    "Não foi possível calcular: " + e.getMessage());
        }
    }

    /** Valida os campos antes de chamar o back-end. Retorna false se houver erro. */
    private boolean validar() {
        boolean ok = true;
        if (nomeUsuario == null || nomeUsuario.isBlank()) {
            addMensagem(FacesMessage.SEVERITY_ERROR, "Informe o nome do usuário/cliente.");
            ok = false;
        }
        if (alturaTijolo == null || alturaTijolo <= 0) {
            addMensagem(FacesMessage.SEVERITY_ERROR, "A altura do tijolo deve ser maior que zero.");
            ok = false;
        }
        if (larguraTijolo == null || larguraTijolo <= 0) {
            addMensagem(FacesMessage.SEVERITY_ERROR, "A largura do tijolo deve ser maior que zero.");
            ok = false;
        }
        if (percentualPerda == null || percentualPerda < 0) {
            addMensagem(FacesMessage.SEVERITY_ERROR, "O percentual de perda não pode ser negativo.");
            ok = false;
        }
        for (int i = 0; i < paredes.size(); i++) {
            ArestaRequest p = paredes.get(i);
            int n = i + 1;
            if (p.getComprimento() == null || p.getComprimento() <= 0) {
                addMensagem(FacesMessage.SEVERITY_ERROR, "Parede " + n + ": comprimento deve ser maior que zero.");
                ok = false;
            }
            if (p.getAlturaParede() == null || p.getAlturaParede() <= 0) {
                addMensagem(FacesMessage.SEVERITY_ERROR, "Parede " + n + ": altura deve ser maior que zero.");
                ok = false;
            }
            if (p.isPossuiPorta()
                    && (p.getLarguraPorta() == null || p.getLarguraPorta() <= 0
                        || p.getAlturaPorta() == null || p.getAlturaPorta() <= 0)) {
                addMensagem(FacesMessage.SEVERITY_ERROR, "Parede " + n + ": preencha largura e altura da porta.");
                ok = false;
            }
            if (p.isPossuiJanela()
                    && (p.getLarguraJanela() == null || p.getLarguraJanela() <= 0
                        || p.getAlturaJanela() == null || p.getAlturaJanela() <= 0)) {
                addMensagem(FacesMessage.SEVERITY_ERROR, "Parede " + n + ": preencha largura e altura da janela.");
                ok = false;
            }
        }
        return ok;
    }

    public void limpar() {
        this.nomeUsuario = null;
        this.alturaTijolo = 0.20;
        this.larguraTijolo = 0.30;
        this.percentualPerda = 10.0;
        this.paredes = new ArrayList<>();
        this.resposta = null;
        this.numeroOrcamento = null;
        adicionarParede();
    }

    private void addMensagem(FacesMessage.Severity severidade, String texto) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severidade, texto, null));
    }

    // ----- Getters e Setters -----
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
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

    public List<ArestaRequest> getParedes() {
        return paredes;
    }

    public void setParedes(List<ArestaRequest> paredes) {
        this.paredes = paredes;
    }

    public TijoloResponse getResposta() {
        return resposta;
    }

    public String getNumeroOrcamento() {
        return numeroOrcamento;
    }
}
