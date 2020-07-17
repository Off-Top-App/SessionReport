package offtop.sessionReportService.Services

import offtop.sessionReportService.Models.EndSession
import offtop.sessionReportService.Models.SessionData
import org.springframework.stereotype.Service

@Service
class EndSessionConsumerService{
    private fun getIncomingEndSession(incomingEndSession: Map<*, *>): EndSession {
        return EndSession(
                incomingEndSession["user_id"].toString().toDouble().toInt(),
                incomingEndSession["session_data"] as List<SessionData>
        )
    }

    fun consumeIncomingEndSession(consumeIncomingEndSession: Map<*,*>) {
        var incomingEndSession: EndSession  = getIncomingEndSession(consumeIncomingEndSession)
        println(incomingEndSession.toString());
    }
}