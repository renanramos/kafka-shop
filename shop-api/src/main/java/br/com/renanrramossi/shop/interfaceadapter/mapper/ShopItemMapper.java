package br.com.renanrramossi.shop.interfaceadapter.mapper;

import br.com.renanrramossi.shop.domain.dto.ShopItemDTO;
import br.com.renanrramossi.shop.domain.entities.ShopItem;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopItemMapper {

	ShopItemMapper INSTANCE = Mappers.getMapper(ShopItemMapper.class);


	@Named("mapShopItemsFrom")
	default List<ShopItem> mapShopItemsFrom(final List<ShopItemDTO> itemDtoList) {
		if (itemDtoList == null) {
			return Collections.emptyList();
		}

		return itemDtoList.stream().map(ShopItemMapper.INSTANCE::mapShopItemDtoFrom).collect(Collectors.toList());
	}

	@Named("mapShopItemDtoFrom")
	ShopItem mapShopItemDtoFrom(final ShopItemDTO shopItemDTO);
}