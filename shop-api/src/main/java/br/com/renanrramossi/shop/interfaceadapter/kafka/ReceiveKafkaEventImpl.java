package br.com.renanrramossi.shop.interfaceadapter.kafka;

import br.com.renanrramossi.shop.common.kafka.service.ReceiveKafkaEvent;
import br.com.renanrramossi.shop.domain.dto.ShopDTO;
import br.com.renanrramossi.shop.domain.entities.Shop;
import br.com.renanrramossi.shop.interfaceadapter.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceiveKafkaEventImpl implements ReceiveKafkaEvent<ShopDTO> {

	private final ShopRepository shopRepository;

	private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

	@KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group")
	public void listenToEvents(final ShopDTO shopDTO,
														 @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY)	final String key,
														 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) final String partitionId,
														 @Header(KafkaHeaders.RECEIVED_TIMESTAMP)	final String timestamp) {
		try{
			log.info("Status da compra recebida no tópico: {}.", shopDTO.getIdentifier());

			final Shop shop = shopRepository.findByIdentifier(shopDTO.getIdentifier());
			shop.setStatus(shopDTO.getStatus());
			shopRepository.save(shop);
		}catch (Exception ex) {
			log.error("Error no processamento da compra {}", shopDTO.getIdentifier());
			log.error(ex.getLocalizedMessage());
		}
	}
}