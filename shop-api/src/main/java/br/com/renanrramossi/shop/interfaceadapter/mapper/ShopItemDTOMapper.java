package br.com.renanrramossi.shop.interfaceadapter.mapper;

import br.com.renanrramossi.shop.domain.dto.ShopItemDTO;
import br.com.renanrramossi.shop.domain.entities.ShopItem;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopItemDTOMapper {

	ShopItemDTOMapper INSTANCE = Mappers.getMapper(ShopItemDTOMapper.class);

	@Named("mapShopItemDtoFrom")
	List<ShopItemDTO> mapShopItemDtoFrom(final List<ShopItem> shopItem);
}