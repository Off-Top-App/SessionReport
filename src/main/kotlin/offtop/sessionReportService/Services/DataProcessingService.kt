package offtop.sessionReportService.Services

import offtop.sessionReportService.Models.EndSession
import offtop.sessionReportService.Models.EndSessionReport
import offtop.sessionReportService.Models.SessionData
import org.springframework.stereotype.Service


@Service
class DataProcessingService {
    fun kafkaStreams(endSession: EndSession):EndSessionReport{
        var userId:Int = endSession.userId
        var sessionData:List<SessionData> = endSession.sessionData
        var averageFocusScore:Int = 1
        var topic: String = "test"
        var mostUsedWord: List<String> = listOf("test1", "test2", "test3")
        var startTime: String = "09:30"
        var endTime: String = "09:45"
        var sessionDuration: String = "15"

        return EndSessionReport(userId = userId, sessionData = sessionData,
                averageFocusScore = averageFocusScore, topic = topic,
                mostUsedWord = mostUsedWord, startTime = startTime,
                endTime = endTime, sessionDuration = sessionDuration)
    }
}