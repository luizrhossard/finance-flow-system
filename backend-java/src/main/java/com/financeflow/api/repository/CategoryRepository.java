package com.financeflow.api.repository;

import com.financeflow.api.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Aqui podemos adicionar métodos customizados depois, se precisar
    // Ex: Optional<Category> findByName(String name);
}
