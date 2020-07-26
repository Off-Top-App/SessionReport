package offtop.sessionReportService.Models

class EndSessionReport constructor(
        userId: Int,
        sessionData: List<SessionData>,
        averageFocusScore: Int,
        topic: String,
        mostUsedWord: List<String>,
        startTime: String,
        endTime: String,
        sessionDuration: String
) {
    var user_id: Int = userId
    var session_data: List<SessionData> = sessionData
    var average_focus_score: Int = averageFocusScore
    var topic: String = topic
    var most_used_word: List<String> = mostUsedWord
    var start_time: String = startTime
    var end_time: String = endTime
    var session_duration: String = sessionDuration

    @Override
    override fun toString(): String {
        return "NewSessionReport: user_id = $user_id, session_data = $session_data, " +
                "average_focus_score = $average_focus_score, topic = $topic, " +
                "most_used_word = $most_used_word, start_time = $start_time, " +
                "end_time = $end_time, session_duration = $session_duration]"
    }
}