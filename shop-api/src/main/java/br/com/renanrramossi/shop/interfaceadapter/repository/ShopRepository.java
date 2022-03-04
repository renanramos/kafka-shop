package br.com.renanrramossi.shop.interfaceadapter.repository;

import br.com.renanrramossi.shop.domain.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
	Shop findByIdentifier(final String identifier);
}