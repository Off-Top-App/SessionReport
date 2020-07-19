package offtop.sessionReportService.Services

import com.google.gson.Gson
import offtop.sessionReportService.Models.EndSessionReport
import org.springframework.stereotype.Service

@Service
class MessageParserService {
    fun parseMessage(message: String): Map<*, *> {
        return Gson().fromJson(message, Map::class.java)
    }

    fun toJson(obj: EndSessionReport): String {
        return Gson().toJson(obj)
    }
}