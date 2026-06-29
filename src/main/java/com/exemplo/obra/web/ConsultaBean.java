package com.exemplo.obra.web;

import com.exemplo.obra.entity.Orcamento;
import com.exemplo.obra.repository.OrcamentoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Bean de tela para CONSULTAR orçamentos salvos no banco de dados,
 * buscando por número do orçamento ou por nome do usuário.
 */
@Component("consultaBean")
@SessionScope
public class ConsultaBean implements Serializable {

    private final OrcamentoRepository repository;

    private String termoBusca;
    private List<Orcamento> resultados = new ArrayList<>();

    public ConsultaBean(OrcamentoRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        listarTodos();
    }

    public void buscarPorNumero() {
        resultados = new ArrayList<>();
        if (termoBusca == null || termoBusca.isBlank()) {
            addMensagem(FacesMessage.SEVERITY_WARN, "Digite o número do orçamento.");
            return;
        }
        Optional<Orcamento> encontrado = repository.findByNumero(termoBusca.trim());
        if (encontrado.isPresent()) {
            resultados.add(encontrado.get());
        } else {
            addMensagem(FacesMessage.SEVERITY_WARN,
                    "Nenhum orçamento encontrado com o número '" + termoBusca + "'.");
        }
    }

    public void buscarPorNome() {
        if (termoBusca == null || termoBusca.isBlank()) {
            addMensagem(FacesMessage.SEVERITY_WARN, "Digite o nome do usuário.");
            resultados = new ArrayList<>();
            return;
        }
        resultados = repository
                .findByNomeUsuarioContainingIgnoreCaseOrderByDataCriacaoDesc(termoBusca.trim());
        if (resultados.isEmpty()) {
            addMensagem(FacesMessage.SEVERITY_WARN,
                    "Nenhum orçamento encontrado para o nome '" + termoBusca + "'.");
        }
    }

    public void listarTodos() {
        this.termoBusca = null;
        this.resultados = repository.findAllByOrderByDataCriacaoDesc();
    }

    private void addMensagem(FacesMessage.Severity severidade, String texto) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severidade, texto, null));
    }

    public String getTermoBusca() {
        return termoBusca;
    }

    public void setTermoBusca(String termoBusca) {
        this.termoBusca = termoBusca;
    }

    public List<Orcamento> getResultados() {
        return resultados;
    }
}
