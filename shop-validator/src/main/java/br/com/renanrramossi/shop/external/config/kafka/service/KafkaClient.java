package br.com.renanrramossi.shop.external.config.kafka.service;

import br.com.renanrramossi.shop.domain.dto.ShopDTO;

public interface KafkaClient {
	void sendMessage(final ShopDTO message);
}