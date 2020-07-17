package offtop.sessionReportService.Listeners

import offtop.sessionReportService.Models.EndSessionReport
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class NewUserReportProducer {
    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, Any>
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    fun sendNewUserReport(endUserReport: EndSessionReport) {
        kafkaTemplate.send("NewUserReport", endUserReport)
        logger.info(String.format("Producing NewUserReport Event: -> %s", endUserReport));
    }
}
