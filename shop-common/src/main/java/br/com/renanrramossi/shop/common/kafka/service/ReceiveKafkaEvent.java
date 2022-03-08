package br.com.renanrramossi.shop.common.kafka.service;

public abstract class ReceiveKafkaEvent<T> {
	protected abstract void listenToEvents(T message);
}