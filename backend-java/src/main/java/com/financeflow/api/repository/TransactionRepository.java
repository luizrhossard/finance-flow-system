package com.financeflow.api.repository;

import com.financeflow.api.domain.Transaction;
import com.financeflow.api.domain.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Busca transações de um mês específico (para a tabela principal)
    List<Transaction> findByDateBetween(LocalDate startDate, LocalDate endDate);

    // Soma total de Receitas ou Despesas de um mês (Para os Cards do topo)
    // O COALESCE garante que retorne 0 se não houver nada, evitando null pointer
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.type = :type AND t.date BETWEEN :start AND :end")
    BigDecimal sumByTypeAndDate(@Param("type") TransactionType type, 
                                @Param("start") LocalDate start, 
                                @Param("end") LocalDate end);
}
