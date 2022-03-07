package br.com.renanrramossi.shop.interfaceadapter.controller;

import br.com.renanrramossi.shop.domain.dto.ShopReportDTO;
import br.com.renanrramossi.shop.interfaceadapter.mapper.ShopReportMapper;
import br.com.renanrramossi.shop.interfaceadapter.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shop_report")
@RequiredArgsConstructor
public class ShopController {

	private final ReportRepository reportRepository;

	@GetMapping
	public List<ShopReportDTO> getShopReport() {
		return reportRepository.findAll()
						.stream()
						.map(ShopReportMapper.INSTANCE::mapShopReportDTOFrom)
						.collect(Collectors.toList());
	}

}