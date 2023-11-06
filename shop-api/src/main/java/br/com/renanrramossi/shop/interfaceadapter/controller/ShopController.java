package br.com.renanrramossi.shop.interfaceadapter.controller;

import br.com.renanrramossi.shop.common.kafka.service.KafkaClient;
import br.com.renanrramossi.shop.domain.entities.StatusEnum;
import br.com.renanrramossi.shop.interfaceadapter.repository.ShopRepository;
import br.com.renanrramossi.shop.domain.dto.ShopDTO;
import br.com.renanrramossi.shop.domain.entities.Shop;
import br.com.renanrramossi.shop.interfaceadapter.mapper.ShopDTOMapper;
import br.com.renanrramossi.shop.interfaceadapter.mapper.ShopMapper;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.com.renanrramossi.shop.common.constants.TopicsNames.SHOP_TOPIC;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

	private final ShopRepository shopRepository;
	private final KafkaClient<ShopDTO> kafkaClient;

	@GetMapping
	public List<ShopDTO> getShop() {
		return shopRepository
						.findAll()
						.stream()
						.map(ShopDTOMapper.INSTANCE::mapShopDTOFrom)
						.collect(Collectors.toList());
	}

	@PostMapping
	public ShopDTO saveShop(@RequestBody @Valid final ShopDTO shopDTO) {

		shopDTO.setIdentifier(UUID.randomUUID().toString());
		shopDTO.setDateShop(LocalDate.now());
		shopDTO.setStatus(StatusEnum.PENDING);

		final Shop shop = ShopMapper.INSTANCE.mapShopFrom(shopDTO);

		final Shop newShop = shopRepository.save(shop);

		kafkaClient.sendMessage(SHOP_TOPIC, shopDTO.getBuyerIdentifier(), shopDTO);

		return ShopDTOMapper.INSTANCE.mapShopDTOFrom(newShop);
	}

}
