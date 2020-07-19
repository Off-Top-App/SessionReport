package offtop.sessionReportService.Services

import offtop.sessionReportService.Models.EndSession
import offtop.sessionReportService.Models.EndSessionReport
import offtop.sessionReportService.Models.SessionData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EndSessionConsumerService{
    @Autowired
    lateinit var processData: DataProcessingService

    private fun getIncomingEndSession(incomingEndSession: Map<*, *>): EndSession {
        return EndSession(
                userId = incomingEndSession["user_id"].toString().toDouble().toInt(),
                sessionData = incomingEndSession["session_data"] as List<SessionData>
        )
    }

    fun consumeIncomingEndSession(consumeIncomingEndSession: Map<*,*>): EndSessionReport {
        var incomingEndSession: EndSession  = getIncomingEndSession(consumeIncomingEndSession)


        return processData.kafkaStreams(incomingEndSession)
    }
}