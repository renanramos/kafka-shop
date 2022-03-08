package br.com.renanrramossi.shop.common.kafka;

public abstract class ReceiveKafkaEvent<T> {
	protected abstract void listenToEvents(T message);
}