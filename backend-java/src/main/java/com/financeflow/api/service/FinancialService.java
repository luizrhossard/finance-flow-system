package com.financeflow.api.service;

import com.financeflow.api.domain.Transaction;
import com.financeflow.api.domain.TransactionType;
import com.financeflow.api.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Service
// @RequiredArgsConstructor // Removido para evitar erro de compilação se o Lombok falhar
public class FinancialService {

    private final TransactionRepository repository;

    public FinancialService(TransactionRepository repository) {
        this.repository = repository;
    }

    // Método para os 4 Cards do Topo
    public Map<String, BigDecimal> getDashboardSummary() {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        LocalDate endOfMonth = now.withDayOfMonth(now.lengthOfMonth());

        // 1. Receitas do Mês
        BigDecimal income = repository.sumByTypeAndDate(TransactionType.INCOME, startOfMonth, endOfMonth);
        
        // 2. Despesas do Mês
        BigDecimal expenses = repository.sumByTypeAndDate(TransactionType.EXPENSE, startOfMonth, endOfMonth);
        
        // 3. Lucro Líquido
        BigDecimal netProfit = income.subtract(expenses);
        
        // 4. Contas Pendentes (Lógica simples: Despesas futuras ainda não pagas)
        // Aqui precisaríamos de um ajuste no Repository para filtrar por 'pending=true'
        // Por enquanto, vamos deixar fixo ou usar uma lógica simples 
        
        return Map.of(
            "income", income,
            "expenses", expenses,
            "netProfit", netProfit
        );
    }

    public List<Transaction> getAllTransactions() {
        return repository.findAll(); // Pega tudo do banco para mandar pro Python treinar
    }
}
