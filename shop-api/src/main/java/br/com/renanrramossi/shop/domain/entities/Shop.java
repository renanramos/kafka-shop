package br.com.renanrramossi.shop.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String identifier;

	private StatusEnum status;

	@Column(name = "date_shop")
	private LocalDate dateShop;

	@Column(name = "buyer_identifier")
	private String buyerIdentifier;

	@OneToMany(fetch = FetchType.EAGER,
					   cascade = CascadeType.ALL,
						 mappedBy = "shop")
	private List<ShopItem> items;
}