package offtop.sessionReportService.Listeners

import offtop.sessionReportService.Models.EndSessionReport
import offtop.sessionReportService.Services.EndSessionConsumerService
import offtop.sessionReportService.Services.MessageParserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class EndSessionConsumer {
    @Autowired
    private lateinit var messageParserService: MessageParserService
    @Autowired
    private lateinit var consumerService: EndSessionConsumerService
    @Autowired
    lateinit var sendNewSessionReport: NewSessionReportProducer
    @Autowired
    lateinit var sendNewUserReport: NewUserReportProducer

    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["EndSession"], groupId = "report")
    fun processMessage(message: String) {
        val consumedData: Map<*, *> = messageParserService.parseMessage(message)
        logger.info("Got EndSession Request from user: $consumedData")

        val processedData:EndSessionReport = consumerService.consumeIncomingEndSession(consumedData)

        sendNewSessionReport.sendNewSessionReport(processedData)
        sendNewUserReport.sendNewUserReport(processedData)
    }
}
