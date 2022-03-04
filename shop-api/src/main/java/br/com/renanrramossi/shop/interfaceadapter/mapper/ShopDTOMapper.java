package br.com.renanrramossi.shop.interfaceadapter.mapper;

import br.com.renanrramossi.shop.domain.dto.ShopDTO;
import br.com.renanrramossi.shop.domain.entities.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopDTOMapper {

	ShopDTOMapper INSTANCE = Mappers.getMapper(ShopDTOMapper.class);

	@Named("mapShopDTOFrom")
	ShopDTO mapShopDTOFrom(final Shop shop);

}