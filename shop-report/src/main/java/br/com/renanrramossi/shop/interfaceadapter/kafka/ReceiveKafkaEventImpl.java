package br.com.renanrramossi.shop.interfaceadapter.kafka;

import br.com.renanrramossi.shop.common.kafka.service.ReceiveKafkaEvent;
import br.com.renanrramossi.shop.domain.dto.ShopDTO;
import br.com.renanrramossi.shop.interfaceadapter.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaEventImpl implements ReceiveKafkaEvent<ShopDTO> {

	private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

	private final ReportRepository reportRepository;

	@Override
	@Transactional
	@KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group_report")
	public void listenToEvents(final ShopDTO shopDTO,
														 @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) final String key,
														 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) final String partitionId,
														 @Header(KafkaHeaders.RECEIVED_TIMESTAMP) final String timestamp) throws Exception{

		try {
			log.info("Compra recebida no tópico: {}", shopDTO.getIdentifier());

			reportRepository.incrementShopStatus(shopDTO.getStatus().name());

		}catch (Exception ex) {
			log.error("Erro no processamento da mensagem", ex);
		}
	}
}