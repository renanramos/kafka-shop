package br.com.renanrramossi.shop.common.kafka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaClientImpl<T> extends KafkaClient<T>{

	private final KafkaTemplate<String, T> kafkaTemplate;

	@Override
	public void sendMessage(String topicName, T message) {
		kafkaTemplate.send(topicName, message);
	}
}