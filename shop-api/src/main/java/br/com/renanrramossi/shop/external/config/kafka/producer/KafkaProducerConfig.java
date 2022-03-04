package br.com.renanrramossi.shop.external.config.kafka.producer;

import br.com.renanrramossi.shop.domain.dto.ShopDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

	@Value("${kafka.bootstrapAddress:localhost:9092}")
	private String bootstrapAddress;

	public ProducerFactory<String, ShopDTO> producerFactory() {

		final Map<String, Object> props = new HashMap<>();

		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "shop-api");

		return new DefaultKafkaProducerFactory<>(props);
	}


	@Bean
  public KafkaTemplate<String, ShopDTO> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

}