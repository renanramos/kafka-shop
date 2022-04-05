package br.com.renanrramossi.shop.common.kafka.service;

public interface KafkaClient<T> {
	void sendMessage(String topicName, String key, T message);
	void sendMessage(String topicName, T message);
}