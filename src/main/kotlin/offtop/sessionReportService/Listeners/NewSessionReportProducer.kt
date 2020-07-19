package offtop.sessionReportService.Listeners

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class NewSessionReportProducer {
    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, String>

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    fun sendNewSessionReport(endSessionReport: String) {
        kafkaTemplate.send("NewSessionReport", endSessionReport)
        logger.info(String.format("Producing NewSessionReport Event: -> %s", endSessionReport));
    }
}
