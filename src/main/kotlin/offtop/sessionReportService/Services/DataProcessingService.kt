package offtop.sessionReportService.Services

import com.google.gson.Gson
import offtop.sessionReportService.Models.EndSession
import offtop.sessionReportService.Models.EndSessionReport
import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.kstream.KStream
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime

@Service
class DataProcessingService {
    private fun calculateAverageFocusScore(pointer: EndSession): Double {
        var i: Double = 0.0
        var score: Double = 0.0
        pointer.session_data.indices.forEach { value: Int ->
            score += pointer.session_data[value].focus_value
            i++
        }
        return score / i
    }

    private fun calculateIfOnTopic(pointer: EndSession): Boolean {
        var i: Int = 5
        pointer.session_data.indices.forEach { score: Int ->
            if (pointer.session_data[score].focus_score) i++
            else i--
        }
        return i > 5
    }

    fun processEndSessionReport(endSessionStream: KStream<String, EndSession>): KStream<String, String> {
        return endSessionStream.map { _, pointer ->
            var userId: Int = pointer.user_id
            var endSessionReport: EndSessionReport = EndSessionReport(
                    userId = pointer.user_id,
                    sessionData = pointer.session_data,
                    averageFocusScore = calculateAverageFocusScore(pointer),
                    topic = pointer.session_data[0].topic,
                    mostUsedWord = listOf("default1", "default2", "default3"),
                    //mostUsedWord = NEED MACHINE LEARNING ALGORITHM FOR THIS
                    startTime = LocalDateTime.parse(pointer.session_data[0].time_exported).toString(),
                    endTime = LocalDateTime.parse(pointer.session_data.last().time_exported).toString(),
                    sessionDuration = Duration.between(LocalDateTime.parse(pointer.session_data[0].time_exported),
                            LocalDateTime.parse(pointer.session_data.last().time_exported)).toString()
            )
            var endSessionReportJson: String = Gson().toJson(endSessionReport)
            return@map KeyValue("$userId", endSessionReportJson)
        }
    }
}