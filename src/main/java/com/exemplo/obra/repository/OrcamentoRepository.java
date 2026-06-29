package com.exemplo.obra.repository;

import com.exemplo.obra.entity.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório (ORM) para a entidade Orcamento.
 *
 * O Spring Data JPA gera a implementação automaticamente a partir do nome
 * dos métodos. Aqui ficam os dois identificadores de busca exigidos pela
 * atividade: por NÚMERO do orçamento e por NOME do usuário.
 */
@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

    /** Busca exata pelo número do orçamento (ex.: ORC-20260629-0001). */
    Optional<Orcamento> findByNumero(String numero);

    /** Busca por parte do nome do usuário, ignorando maiúsculas/minúsculas. */
    List<Orcamento> findByNomeUsuarioContainingIgnoreCaseOrderByDataCriacaoDesc(String nomeUsuario);

    /** Lista todos os orçamentos, do mais recente para o mais antigo. */
    List<Orcamento> findAllByOrderByDataCriacaoDesc();
}
