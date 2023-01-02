package how_are_you_project.how_are_you;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home(){
        log.info("home controller");
        return "home";
    }

//    @RequestMapping("/login")
//    public String login(){
//        log.info("login controller");
//        return "fragments/login";
//    }
}
