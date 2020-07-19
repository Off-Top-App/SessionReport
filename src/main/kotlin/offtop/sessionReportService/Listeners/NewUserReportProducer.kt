package offtop.sessionReportService.Listeners

import offtop.sessionReportService.Models.EndSessionReport
import offtop.sessionReportService.Services.MessageParserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class NewUserReportProducer {
    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, String>

    @Autowired
    private lateinit var messageParserService: MessageParserService

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    fun sendNewUserReport(endUserReport: EndSessionReport) {
        val serializedData: String = messageParserService.toJson(endUserReport)
        kafkaTemplate.send("NewUserReport", serializedData)
        logger.info("Producing NewUserReport Event: -> $serializedData")
    }
}
