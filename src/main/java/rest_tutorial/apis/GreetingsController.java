package rest_tutorial.apis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Beksultan
 */
@Controller
public class GreetingsController {

    @GetMapping("/")
    public String greetings() {
        return "greetings";
    }

}
