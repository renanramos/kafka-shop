package br.com.renanrramossi.shop.interfaceadapter.kafka;

import br.com.renanrramossi.shop.businessrule.kafka.ReceiveKafkaEvent;
import br.com.renanrramossi.shop.domain.dto.ShopDTO;
import br.com.renanrramossi.shop.interfaceadapter.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaEventImpl implements ReceiveKafkaEvent {

	private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";
	private final ReportRepository reportRepository;

	@Override
	@Transactional
	@KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group_report")
	public void listenToShopTopic(final ShopDTO shopDTO) {

		try {
			log.info("Compra recebida no tópico: {}", shopDTO.getIdentifier());

			reportRepository.incrementShopStatus(shopDTO.getStatus().name());

		}catch (Exception ex) {
			log.error("Erro no processamento da mensagem", ex);
		}
	}
}