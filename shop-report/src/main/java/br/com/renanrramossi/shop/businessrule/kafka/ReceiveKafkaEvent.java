package br.com.renanrramossi.shop.businessrule.kafka;

import br.com.renanrramossi.shop.domain.dto.ShopDTO;

public interface ReceiveKafkaEvent {

	void listenToShopTopic(final ShopDTO shopDTO);
}