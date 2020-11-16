package offtop.sessionReportService.Config

import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.Topology
import org.springframework.stereotype.Service
import java.util.*

@Service
class KafkaStreamsFactory {
    fun streamsRunner(streamsBuilder: StreamsBuilder) {
        val topology: Topology = streamsBuilder.build()

        val props = Properties()
        //UNCOMMENT THE LINE BELOW IF YOU WANT TO USE DOCKER KAFKA
        props["bootstrap.servers"] = "kafka:9092"
        //UNCOMMENT THE LINE BELOW IF YOU WANT TO USE LOCAL KAFKA
        //props["bootstrap.servers"] = "localhost:9092"  
        props["application.id"] = "report"

        val streams = KafkaStreams(topology, props)
        streams.start()
    }
}