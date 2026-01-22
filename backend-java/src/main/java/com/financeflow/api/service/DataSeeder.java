package com.financeflow.api.service;

import com.financeflow.api.domain.Category;
import com.financeflow.api.domain.Transaction;
import com.financeflow.api.domain.TransactionType;
import com.financeflow.api.repository.CategoryRepository;
import com.financeflow.api.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    public DataSeeder(CategoryRepository categoryRepository, TransactionRepository transactionRepository) {
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            // Criar Categorias
            Category marketing = categoryRepository.save(Category.builder().name("Marketing").colorHex("#FF5733").build());
            Category vendas = categoryRepository.save(Category.builder().name("Vendas").colorHex("#33FF57").build());
            
            // Criar Transações Fakes
            transactionRepository.saveAll(Arrays.asList(
                Transaction.builder()
                    .description("Venda Software A")
                    .amount(new BigDecimal("1500.00"))
                    .date(LocalDate.now())
                    .type(TransactionType.INCOME)
                    .category(vendas)
                    .pending(false)
                    .build(),
                Transaction.builder()
                    .description("Google Ads")
                    .amount(new BigDecimal("500.00"))
                    .date(LocalDate.now().minusDays(2))
                    .type(TransactionType.EXPENSE)
                    .category(marketing)
                    .pending(false)
                    .build()
            ));
            
            System.out.println("✅ Banco populado com dados de teste!");
        }
    }
}
