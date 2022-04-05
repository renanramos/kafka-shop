package br.com.renanrramossi.shop.interfaceadapter.kafka;

import br.com.renanrramossi.shop.common.kafka.service.ReceiveKafkaEvent;
import br.com.renanrramossi.shop.domain.dto.ShopDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceiveKafkaEventImpl implements ReceiveKafkaEvent<ShopDTO> {

	private static final String SHOP_TOPIC = "SHOP_TOPIC";
	private static final String SHOP_TOPIC_RETRY = "SHOP_TOPIC_RETRY";

	private final KafkaTemplate<String, ShopDTO> kafkaTemplate;

	@Override
	@KafkaListener(topics = SHOP_TOPIC, groupId = "group_report")
	public void listenToEvents(final ShopDTO shopDTO,
														 @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) final String key,
														 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) final String partitionId,
														 @Header(KafkaHeaders.RECEIVED_TIMESTAMP) final String timestamp) throws Exception{
		log.info("Compra recebida no tópico: {}.", shopDTO.getIdentifier());

		try {
			if (shopDTO.getItems() == null || shopDTO.getItems().isEmpty()) {
				log.error("Compra sem itens.");
				throw new Exception();
			}
		} catch (Exception e) {
			log.info("Erro na aplicação");
			kafkaTemplate.send(SHOP_TOPIC_RETRY, shopDTO);
		}
	}

	@KafkaListener(topics = SHOP_TOPIC_RETRY, groupId = "group_retry")
	public void listenShopTopicRetry(final ShopDTO shopDTO) {
		log.info("Retentativa de processamento: {}", shopDTO.getIdentifier());
	}
}