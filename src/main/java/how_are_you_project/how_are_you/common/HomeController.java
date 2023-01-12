package how_are_you_project.how_are_you.common;

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

    @RequestMapping("/members/join")
    public String createMemberForm(){
        log.info("join controller");
        return "createMemberForm";
    }

    @RequestMapping("/members/myPage")
    public String myPage(){
        log.info("myPage controller");
        return "myPage";
    }

}
