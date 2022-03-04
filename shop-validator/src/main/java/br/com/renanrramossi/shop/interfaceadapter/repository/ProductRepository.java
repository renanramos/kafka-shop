package br.com.renanrramossi.shop.interfaceadapter.repository;

import br.com.renanrramossi.shop.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findByIdentifier(final String identifier);
}