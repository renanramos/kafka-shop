package br.com.renanrramossi.shop.interfaceadapter.mapper;

import br.com.renanrramossi.shop.domain.dto.ShopReportDTO;
import br.com.renanrramossi.shop.domain.entities.ShopReport;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopReportMapper {

	ShopReportMapper INSTANCE = Mappers.getMapper(ShopReportMapper.class);

	@Named("mapShopReportDTOFrom")
	ShopReportDTO mapShopReportDTOFrom(final ShopReport shopReport);
}