package br.com.renanrramossi.shop.common.kafka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaClientImpl<T> implements KafkaClient<T>{

	private final KafkaTemplate<String, T> kafkaTemplate;

	@Override
	public void sendMessage(final String topicName, final String key, final T message) {
		kafkaTemplate.send(topicName, key, message);
	}

	@Override
	public void sendMessage(final String topicName, final T message) {
		kafkaTemplate.send(topicName, message);
	}
}