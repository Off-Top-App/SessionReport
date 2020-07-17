package offtop.sessionReportService.Services

import com.google.gson.Gson
import org.springframework.stereotype.Service

@Service
class MessageParserService {
    fun parseMessage(message: String): Map<*, *> {
        return Gson().fromJson(message, Map::class.java)
    }
}