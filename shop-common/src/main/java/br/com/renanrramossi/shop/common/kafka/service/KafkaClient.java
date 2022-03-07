package br.com.renanrramossi.shop.common.kafka.service;

public abstract class KafkaClient<T> {
	public abstract void sendMessage(String topicName, T message);
}