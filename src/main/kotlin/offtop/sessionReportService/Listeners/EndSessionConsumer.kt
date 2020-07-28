package offtop.sessionReportService.Listeners

import offtop.sessionReportService.Config.KafkaStreamsFactory
import offtop.sessionReportService.Services.EndSessionConsumerService
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.KStream
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

@Service
class EndSessionConsumer {
    @Autowired
    private lateinit var consumerService: EndSessionConsumerService

    @Autowired
    private lateinit var newSessionReportProducer: NewSessionReportProducer

    @Autowired
    private lateinit var newUserReportProducer: NewUserReportProducer

    @Autowired
    private lateinit var kafkaStreamsRunner: KafkaStreamsFactory

    var topic = "EndSession"

    @Bean
    fun processMessage() {
        val streamsBuilder = StreamsBuilder()

        val endSessionReport: KStream<String, String> = consumerService.consumeIncomingEndSession(streamsBuilder, topic)

        newSessionReportProducer.sendNewSessionReport(endSessionReport)
        newUserReportProducer.sendNewUserReport(endSessionReport)

        kafkaStreamsRunner.streamsRunner(streamsBuilder)
    }
}
