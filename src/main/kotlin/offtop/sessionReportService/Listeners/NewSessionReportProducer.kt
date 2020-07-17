package offtop.sessionReportService.Listeners

import offtop.sessionReportService.Models.EndSessionReport
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class NewSessionReportProducer {
    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, Any>

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    fun sendNewSessionReport(endSessionReport: EndSessionReport) {
        kafkaTemplate.send("NewSessionReport", endSessionReport)
        logger.info(String.format("Producing NewSessionReport Event: -> %s", endSessionReport));
    }
}
