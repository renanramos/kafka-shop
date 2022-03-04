package br.com.renanrramossi.shop.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "shop_item")
public class ShopItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_identifier")
	private String productIdentifier;

	private Integer amount;

	private Float price;

	@ManyToOne
	@JoinColumn(name = "shop_id")
	private Shop shop;
}