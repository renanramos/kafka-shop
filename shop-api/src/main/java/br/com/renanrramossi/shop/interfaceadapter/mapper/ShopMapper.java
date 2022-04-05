package br.com.renanrramossi.shop.interfaceadapter.mapper;

import br.com.renanrramossi.shop.domain.dto.ShopDTO;
import br.com.renanrramossi.shop.domain.dto.ShopItemDTO;
import br.com.renanrramossi.shop.domain.entities.Shop;
import br.com.renanrramossi.shop.domain.entities.ShopItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = ShopItemMapper.class)
public interface ShopMapper {

	ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

	@Named("mapShopFrom")
	@Mapping(target = "items", source = "items", qualifiedByName = "mapShopItemsFrom")
	Shop mapShopFrom(final ShopDTO shopDTO);

}