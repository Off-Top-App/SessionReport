package offtop.sessionReportService.Services

import com.google.gson.Gson
import offtop.sessionReportService.Models.EndSession
import offtop.sessionReportService.Models.EndSessionReport
import org.apache.kafka.streams.kstream.KStream
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EndSessionConsumerService {
    @Autowired
    lateinit var processingService: DataProcessingService

    fun consumeIncomingEndSession(endSessionJsonStream: KStream<String, String>) {
        val endSessionStream: KStream<String, EndSession> = endSessionJsonStream.mapValues { value ->
            return@mapValues Gson().fromJson(value, EndSession::class.java)
        }
        processingService.processEndSessionReport(endSessionStream)
    }
}