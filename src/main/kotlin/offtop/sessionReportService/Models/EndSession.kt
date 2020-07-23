package offtop.sessionReportService.Models

class EndSession constructor(userId: Int, sessionData: List<SessionData>) {
    var user_id: Int = userId
    var session_data: List<SessionData> = sessionData

    @Override
    override fun toString(): String {
        return "EndSession: user_id = $user_id, session_data = $session_data"
    }
}