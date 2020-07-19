package offtop.sessionReportService.Listeners

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class NewUserReportProducer {
    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, String>

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    fun sendNewUserReport(endUserReport: String) {
        kafkaTemplate.send("NewUserReport", endUserReport)
        logger.info(String.format("Producing NewUserReport Event: -> %s", endUserReport));
    }
}
