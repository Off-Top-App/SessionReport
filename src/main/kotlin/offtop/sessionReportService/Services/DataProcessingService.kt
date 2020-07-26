package offtop.sessionReportService.Services

import com.google.gson.Gson
import offtop.sessionReportService.Models.EndSession
import offtop.sessionReportService.Models.EndSessionReport
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.Produced
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DataProcessingService {
    fun processEndSessionReport(endSessionStream: KStream<String, EndSession>) {
        var processDataAndProduce: KStream<String, String> = endSessionStream.map { _, pointer ->
            var userId:Int = pointer.user_id
            var endSessionReport: EndSessionReport = EndSessionReport(
                    userId = pointer.user_id,
                    sessionData = pointer.session_data,
                    averageFocusScore = 0, //averageFocusScore = pointer.session_data,
                    topic = pointer.session_data[0].topic,
                    mostUsedWord = listOf("default1", "default2", "default3"), //mostUsedWord =
                    startTime = pointer.session_data[0].analyzed_at,
                    endTime = pointer.session_data.last().analyzed_at,
                    sessionDuration = "default" //sessionDuration = (parseInt(endTime) - parseInt(startTime)).toString()),
            )
            var endSessionReportJson:String = Gson().toJson(endSessionReport)
            return@map KeyValue("$userId", endSessionReportJson)
        }

        processDataAndProduce.to("NewSessionReport", Produced.with(Serdes.String(), Serdes.String()))
        processDataAndProduce.to("NewUserReport", Produced.with(Serdes.String(), Serdes.String()))
    }
}