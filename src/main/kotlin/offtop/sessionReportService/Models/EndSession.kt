package offtop.sessionReportService.Models

class EndSession constructor(userId: Int, sessionData:List<SessionData>) {
    var userId: Int = userId
    var sessionData: List<SessionData> = sessionData

    @Override
    override fun toString(): String {
        return "EndSession: [user_id = $userId, session_data = ${sessionData.toString()}]"
    }
}