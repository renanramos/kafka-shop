package br.com.renanrramossi.shop.domain.dto;

import br.com.renanrramossi.shop.domain.entities.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO {

	private String identifier;
	private LocalDate dateShop;
	private StatusEnum status;
	private List<ShopItemDTO> items = new ArrayList<>();
}