package br.com.renanrramossi.shop.interfaceadapter.controller;

import br.com.renanrramossi.shop.domain.entities.StatusEnum;
import br.com.renanrramossi.shop.interfaceadapter.repository.ShopRepository;
import br.com.renanrramossi.shop.domain.dto.ShopDTO;
import br.com.renanrramossi.shop.domain.entities.Shop;
import br.com.renanrramossi.shop.domain.entities.ShopItem;
import br.com.renanrramossi.shop.external.config.kafka.service.KafkaClient;
import br.com.renanrramossi.shop.interfaceadapter.mapper.ShopDTOMapper;
import br.com.renanrramossi.shop.interfaceadapter.mapper.ShopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

	private final ShopRepository shopRepository;
	private final KafkaClient kafkaClient;

	@GetMapping
	public List<ShopDTO> getShop() {
		return shopRepository
						.findAll()
						.stream()
						.map(ShopDTOMapper.INSTANCE::mapShopDTOFrom)
						.collect(Collectors.toList());
	}

	@PostMapping
	public ShopDTO saveShop(@RequestBody final ShopDTO shopDTO) {

		shopDTO.setIdentifier(UUID.randomUUID().toString());
		shopDTO.setDateShop(LocalDate.now());
		shopDTO.setStatus(StatusEnum.PENDING);

		final Shop shop = ShopMapper.INSTANCE.mapShopFrom(shopDTO);

		for (final ShopItem shopItem : shop.getItems()) {
			shopItem.setShop(shop);
		}

		final Shop newShop = shopRepository.save(shop);

		kafkaClient.sendMessage(shopDTO);

		return ShopDTOMapper.INSTANCE.mapShopDTOFrom(newShop);
	}

}