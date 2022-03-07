package br.com.renanrramossi.shop.interfaceadapter.repository;

import br.com.renanrramossi.shop.domain.entities.ShopReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<ShopReport, Long> {

	@Modifying
	@Query(value = "update shop_report set amount = amount + 1 where identifier = :shopStatus", nativeQuery = true)
	void incrementShopStatus(final String shopStatus);
}