package com.financeflow.api.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description; // Ex: "Pagamento AWS"

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount; // Use BigDecimal sempre para dinheiro!

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type; // INCOME ou EXPENSE

    @ManyToOne // Muitas transações podem ter uma categoria
    @JoinColumn(name = "category_id")
    private Category category;

    private boolean pending; // true = Pendente (Cinza), false = Concluído (Verde/Vermelho)
}
