package offtop.sessionReportService.Listeners

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.Produced
import org.springframework.stereotype.Service

@Service
class NewUserReportProducer {
    fun sendNewUserReport(endUserReport: KStream<String, String>) {
        endUserReport.to("NewUserReport", Produced.with(Serdes.String(), Serdes.String()))
    }
}
