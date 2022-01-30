package io.github.gsogabriel.domain.repository;

import io.github.gsogabriel.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {
}
