package offtop.sessionReportService.Models

class SessionData constructor(
        session_mode: String,
        focus_score: Boolean,
        focus_value: Int,
        analyzed_at: String,
        time_exported: String,
        transcribed_speech: String,
        topic: String
) {
    var session_mode: String = session_mode
    var focus_score: Boolean = focus_score
    var focus_value: Int = focus_value
    var analyzed_at: String = analyzed_at
    var time_exported: String = time_exported
    var transcribed_speech: String = transcribed_speech
    var topic: String = topic

    @Override
    override fun toString(): String {
        return "session_data: session_mode = $session_mode, focus_score = $focus_score, " +
                "focus_value = $focus_value, analyzed_at = $analyzed_at, " +
                "time_exported = ${time_exported}, transcribed_speech = $transcribed_speech, " +
                "topic = $topic"
    }
}