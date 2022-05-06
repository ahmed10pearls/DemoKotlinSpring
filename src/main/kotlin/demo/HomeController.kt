package demo

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

//    @RequestMapping(value = arrayOf("/"))
    fun home() : String
    {
        return "Welcome Screen"
    }
}