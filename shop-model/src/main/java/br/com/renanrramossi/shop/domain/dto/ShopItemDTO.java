package br.com.renanrramossi.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopItemDTO {

	private String productIdentifier;
	private Integer amount;
	private Float price;

}