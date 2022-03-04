package br.com.renanrramossi.shop.businessrule.kafka;

public interface ReceiveKafkaEvent<T> {
	void listenToEvents(T message);
}