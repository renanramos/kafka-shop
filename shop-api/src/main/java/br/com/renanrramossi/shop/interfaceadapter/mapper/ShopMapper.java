package br.com.renanrramossi.shop.interfaceadapter.mapper;

import br.com.renanrramossi.shop.domain.dto.ShopDTO;
import br.com.renanrramossi.shop.domain.entities.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopMapper {

	ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

	@Named("mapShopDTOFrom")
	Shop mapShopFrom(final ShopDTO shopDTO);

}