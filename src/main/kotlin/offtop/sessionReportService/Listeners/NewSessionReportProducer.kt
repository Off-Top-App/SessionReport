package offtop.sessionReportService.Listeners

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.Produced
import org.springframework.stereotype.Service

@Service
class NewSessionReportProducer {
    fun sendNewSessionReport(endSessionReport: KStream<String, String>) {
        endSessionReport.to("NewSessionReport", Produced.with(Serdes.String(), Serdes.String()))
    }
}
