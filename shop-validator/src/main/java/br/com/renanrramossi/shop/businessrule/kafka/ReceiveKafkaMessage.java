package br.com.renanrramossi.shop.businessrule.kafka;

public interface ReceiveKafkaMessage<T> {
	void listenShopTopic(T message);
}