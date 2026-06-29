package com.exemplo.obra.web;

import com.exemplo.obra.dto.ArestaRequest;
import com.exemplo.obra.dto.ConcretoRequest;
import com.exemplo.obra.dto.ConcretoResponse;
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
 * Bean de tela (front-end JSF) para o cálculo de VOLUME DE CONCRETO.
 *
 * Ele NÃO recalcula nada: apenas coleta os dados da tela, chama o back-end
 * já existente (MaterialService) e salva o resultado como um orçamento.
 *
 * @SessionScope mantém o que o usuário digitou enquanto ele adiciona várias
 * paredes (cada postback do formulário reaproveita o mesmo objeto).
 */
@Component("concretoBean")
@SessionScope
public class ConcretoBean implements Serializable {

    private final MaterialService materialService;
    private final OrcamentoService orcamentoService;

    // ----- Dados de entrada (preenchidos na tela) -----
    private String nomeUsuario;
    private Double alturaViga = 0.30;
    private List<ArestaRequest> paredes = new ArrayList<>();

    // ----- Resultado (preenchido após calcular) -----
    private ConcretoResponse resposta;
    private String numeroOrcamento;

    public ConcretoBean(MaterialService materialService, OrcamentoService orcamentoService) {
        this.materialService = materialService;
        this.orcamentoService = orcamentoService;
    }

    @PostConstruct
    public void init() {
        adicionarParede();
    }

    /** Adiciona uma nova parede (aresta) à planta com valores padrão. */
    public void adicionarParede() {
        ArestaRequest parede = new ArestaRequest();
        parede.setId("P" + (paredes.size() + 1));
        parede.setVerticeOrigem("V" + (paredes.size() + 1));
        parede.setVerticeDestino("V" + (paredes.size() + 2));
        paredes.add(parede);
    }

    /** Remove uma parede específica da planta. */
    public void removerParede(ArestaRequest parede) {
        paredes.remove(parede);
        if (paredes.isEmpty()) {
            adicionarParede();
        }
    }

    /** Chama o back-end, exibe o resultado e salva o orçamento. */
    public void calcular() {
        if (!validar()) {
            this.resposta = null;
            return;
        }
        try {
            ConcretoRequest request = new ConcretoRequest();
            request.setAlturaViga(alturaViga);
            request.setArestas(paredes);

            this.resposta = materialService.calcularVolumeConcreto(request);

            String entrada = String.format(
                    "Altura da viga: %.2f m | Paredes: %d", alturaViga, paredes.size());
            String resultadoTexto = String.format(
                    "Volume de concreto: %s m³ (%d arestas processadas)",
                    resposta.getVolumeTotal(), resposta.getQuantidadeArestasProcessadas());

            Orcamento salvo = orcamentoService.salvar(
                    nomeUsuario, "CONCRETO", entrada, resultadoTexto);
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
        if (alturaViga == null || alturaViga <= 0) {
            addMensagem(FacesMessage.SEVERITY_ERROR, "A altura da viga deve ser maior que zero.");
            ok = false;
        }
        for (int i = 0; i < paredes.size(); i++) {
            ArestaRequest p = paredes.get(i);
            int n = i + 1;
            if (p.getComprimento() == null || p.getComprimento() <= 0) {
                addMensagem(FacesMessage.SEVERITY_ERROR, "Parede " + n + ": comprimento deve ser maior que zero.");
                ok = false;
            }
            if (p.getEspessura() == null || p.getEspessura() <= 0) {
                addMensagem(FacesMessage.SEVERITY_ERROR, "Parede " + n + ": espessura deve ser maior que zero.");
                ok = false;
            }
        }
        return ok;
    }

    /** Limpa o formulário para um novo orçamento. */
    public void limpar() {
        this.nomeUsuario = null;
        this.alturaViga = 0.30;
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

    public Double getAlturaViga() {
        return alturaViga;
    }

    public void setAlturaViga(Double alturaViga) {
        this.alturaViga = alturaViga;
    }

    public List<ArestaRequest> getParedes() {
        return paredes;
    }

    public void setParedes(List<ArestaRequest> paredes) {
        this.paredes = paredes;
    }

    public ConcretoResponse getResposta() {
        return resposta;
    }

    public String getNumeroOrcamento() {
        return numeroOrcamento;
    }
}
