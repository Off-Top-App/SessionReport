package offtop.sessionReportService.Listeners

import offtop.sessionReportService.Services.EndSessionConsumerService
import offtop.sessionReportService.Services.MessageParserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class EndSessionConsumer {

    private val logger = LoggerFactory.getLogger(javaClass)
    @Autowired
    private lateinit var messageParserService: MessageParserService
    @Autowired
    private lateinit var consumerService: EndSessionConsumerService

    @KafkaListener(topics = ["EndSession"], groupId = "report")
    fun processMessage(message: String) {
        val consumedData: Map<*, *> = messageParserService.parseMessage(message)
        consumerService.consumeIncomingEndSession(consumedData)
        logger.info("Got EndSession Request from user: $consumedData")
    }
}
