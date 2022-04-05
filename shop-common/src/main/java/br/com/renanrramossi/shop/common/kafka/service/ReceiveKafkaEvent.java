package br.com.renanrramossi.shop.common.kafka.service;

public interface ReceiveKafkaEvent<T> {
	void listenToEvents(T message, final String key, final String partitionId, String timestamp);
}