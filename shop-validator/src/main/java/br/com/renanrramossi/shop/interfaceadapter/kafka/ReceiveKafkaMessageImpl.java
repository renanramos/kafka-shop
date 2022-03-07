package br.com.renanrramossi.shop.interfaceadapter.kafka;

import br.com.renanrramossi.shop.businessrule.kafka.ReceiveKafkaMessage;
import br.com.renanrramossi.shop.common.kafka.service.KafkaClient;
import br.com.renanrramossi.shop.domain.dto.ShopDTO;
import br.com.renanrramossi.shop.domain.dto.ShopItemDTO;
import br.com.renanrramossi.shop.domain.entities.Product;
import br.com.renanrramossi.shop.domain.entities.StatusEnum;
import br.com.renanrramossi.shop.interfaceadapter.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessageImpl implements ReceiveKafkaMessage<ShopDTO> {

	private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";
	private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

	private final ProductRepository productRepository;
	private final KafkaClient kafkaClient;

	@KafkaListener(topics =  SHOP_TOPIC_NAME, groupId = "group")
	public void listenShopTopic(final ShopDTO shopDTO) {

		try {
			log.info("Compra recebida no tópico: {}", shopDTO.getIdentifier());

			shopDTO.setStatus(validateShopItems(shopDTO.getItems(), shopDTO.getIdentifier()));
			kafkaClient.sendMessage(SHOP_TOPIC_EVENT_NAME, shopDTO);

		} catch (Exception ex) {
			log.error("Exception: " + ex.getLocalizedMessage());
			log.error("Erro no processamento da compra {}", shopDTO.getIdentifier());
		}
	}

	private StatusEnum validateShopItems(final List<ShopItemDTO> shopItemsDTO, final String identifier) {
		for (final ShopItemDTO item : shopItemsDTO) {
			log.debug("ITEM (product_identifier): " + item.getProductIdentifier());

			final Product product = productRepository.findByIdentifier(item.getProductIdentifier())
							.orElse(null);

			if (!isValidShop(item, product)) {
				log.error("Erro no processamento da compra {}.", identifier);
				return StatusEnum.ERROR;
			}
		}

		log.info("Compra {} efetuada com sucesso.", identifier);

		return StatusEnum.SUCCESS;
	}

	private boolean isValidShop(final ShopItemDTO item, final Product product) {

		if (product == null) {
			return false;
		}

		return product.getAmount() >= item.getAmount();
	}
}