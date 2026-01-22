package com.financeflow.api.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder // Padrão Builder para criar objetos mais fácil depois
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Ex: "Marketing", "Salários"

    private String colorHex; // Ex: "#00FF00" (Para pintar o gráfico no frontend)
}
