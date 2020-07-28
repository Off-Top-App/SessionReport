package offtop.sessionReportService.Services

import com.google.gson.Gson
import offtop.sessionReportService.Models.EndSession
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.KStream
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EndSessionConsumerService {
    @Autowired
    lateinit var processingService: DataProcessingService

    fun consumeIncomingEndSession(streamsBuilder: StreamsBuilder, topic: String): KStream<String, String> {
        val endSessionJsonStream: KStream<String, String> = streamsBuilder
                .stream(topic, Consumed.with(Serdes.String(), Serdes.String()))
        val endSessionStream: KStream<String, EndSession> = endSessionJsonStream.mapValues { value ->
            return@mapValues Gson().fromJson(value, EndSession::class.java)
        }
        return processingService.processEndSessionReport(endSessionStream)
    }
}