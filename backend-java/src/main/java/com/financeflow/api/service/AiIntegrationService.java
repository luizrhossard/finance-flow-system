package com.financeflow.api.service;

import com.financeflow.api.domain.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;

@Service
public class AiIntegrationService {

    private final RestClient restClient;

    public AiIntegrationService(@Value("${app.python.api.url}") String pythonUrl) {
        this.restClient = RestClient.builder().baseUrl(pythonUrl).build();
    }

    // Agora recebemos a lista de transações para enviar
    public String getPrediction(List<Transaction> history) {
        // Converte para DTO para enviar apenas o necessário
        List<com.financeflow.api.dto.TransactionEntryDTO> entries = history.stream()
            .map(t -> new com.financeflow.api.dto.TransactionEntryDTO(
                t.getDate().toString(), // ISO 8601 (yyyy-MM-dd)
                t.getAmount(),
                t.getType().name()
            ))
            .toList();

        return restClient.post()
                .uri("/predict")
                .body(entries) // Envia a lista de DTOs limpa
                .retrieve()
                .body(String.class);
    }
}
