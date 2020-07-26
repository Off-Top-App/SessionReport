package offtop.sessionReportService.Listeners

import offtop.sessionReportService.Services.EndSessionConsumerService
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.KStream
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class EndSessionConsumer {
    @Autowired
    private lateinit var consumerService: EndSessionConsumerService

    var topic = "EndSession"

    @Autowired
    fun processMessage() {
        val streamsBuilder = StreamsBuilder()
        val endSessionJsonStream: KStream<String, String> = streamsBuilder
                .stream(topic, Consumed.with(Serdes.String(), Serdes.String()))
        consumerService.consumeIncomingEndSession(endSessionJsonStream)
        val topology: Topology = streamsBuilder.build()
        val props = Properties()
        props["bootstrap.servers"] = "localhost:9092"
        props["application.id"] = "report"
        val streams = KafkaStreams(topology, props)
        streams.start()
    }
}
