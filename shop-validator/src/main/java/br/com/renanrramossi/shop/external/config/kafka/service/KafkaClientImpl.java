package br.com.renanrramossi.shop.external.config.kafka.service;


import br.com.renanrramossi.shop.domain.dto.ShopDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaClientImpl implements KafkaClient {

	private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

	private final KafkaTemplate<String, ShopDTO> kafkaTemplate;

	@Override
	public void sendMessage(ShopDTO message) {
		kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, message);
	}
}