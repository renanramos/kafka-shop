package br.com.renanrramossi.shop.interfaceadapter.mapper;

import br.com.renanrramossi.shop.domain.dto.ShopDTO;
import br.com.renanrramossi.shop.domain.entities.Shop;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = ShopItemDTOMapper.class)
public interface ShopDTOMapper {

	ShopDTOMapper INSTANCE = Mappers.getMapper(ShopDTOMapper.class);

	@Named("mapShopDTOFrom")
	@Mapping(target = "items", source = "items", qualifiedByName = "mapShopItemDtoFrom")
	ShopDTO mapShopDTOFrom(final Shop shop);

}