package br.com.renanrramossi.shop.interfaceadapter.mapper;

import br.com.renanrramossi.shop.domain.dto.ShopItemDTO;
import br.com.renanrramossi.shop.domain.entities.ShopItem;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopItemMapper {

	ShopItemMapper INSTANCE = Mappers.getMapper(ShopItemMapper.class);

	@Named("mapShopItemDTOFrom")
	ShopItemDTO mapShopItemDTOFrom(final ShopItem shopItem);
}