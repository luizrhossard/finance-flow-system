package com.financeflow.api.controller;

import com.financeflow.api.service.AiIntegrationService;
import com.financeflow.api.service.FinancialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permite acesso de qualquer frontend
@Tag(name = "Dashboard", description = "Endpoints para alimentar o painel visual")
public class DashboardController {

    private final FinancialService financialService;
    private final AiIntegrationService aiService;

    @Operation(summary = "Resumo Financeiro", description = "Retorna o total de Receitas, Despesas e Lucro do mês atual")
    @GetMapping("/summary")
    public ResponseEntity<Map<String, BigDecimal>> getSummary() {
        return ResponseEntity.ok(financialService.getDashboardSummary());
    }

    @Operation(summary = "Previsão via IA", description = "Conecta ao microserviço Python para prever o fluxo de caixa futuro")
    @GetMapping("/prediction")
    public ResponseEntity<String> getPrediction() {
        // 1. Busca histórico real no Postgres
        var history = financialService.getAllTransactions();
        
        // 2. Envia para o Python analisar
        String predictionJson = aiService.getPrediction(history);
        
        return ResponseEntity.ok(predictionJson);
    }
}
