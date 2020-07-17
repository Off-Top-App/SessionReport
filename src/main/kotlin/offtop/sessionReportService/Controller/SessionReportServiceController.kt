package offtop.sessionReportService.Controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserSessionController{
    @RequestMapping("/Rest")
    fun test() = "Api works!!"
}