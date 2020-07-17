package offtop.sessionReportService.Models

class EndSessionReport constructor(userId: Int, sessionData:List<SessionData>,
                                   averageFocusScore: Int, topic: String,
                                   mostUsedWord:List<String>, startTime:String,
                                   endTime:String, sessionDuration: String) {
    var userId: Int = userId
    var sessionData: List<SessionData> = sessionData
    var averageFocusScore: Int = averageFocusScore
    var topic: String = topic
    var mostUsedWord: List<String> = mostUsedWord
    var startTime: String = startTime
    var endTime: String = endTime
    var sessionDuration: String = sessionDuration

    @Override
    override fun toString(): String {
        return "NewSessionReport: [user_id = $userId, session_data = ${sessionData.toString()}" +
                "average_focus_score = $averageFocusScore, topic = $topic" +
                "most_used_word = $mostUsedWord, start_time = $startTime" +
                "end_time = $endTime, session_duration = $sessionDuration]"
    }
}