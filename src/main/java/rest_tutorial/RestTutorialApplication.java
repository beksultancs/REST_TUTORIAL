package rest_tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rest_tutorial.services.StudentService;
import rest_tutorial.services.impl.StudentServiceImpl;

@SpringBootApplication
public class RestTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestTutorialApplication.class, args);
    }

}
