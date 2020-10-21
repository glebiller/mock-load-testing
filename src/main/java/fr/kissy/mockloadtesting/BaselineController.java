package fr.kissy.mockloadtesting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BaselineController {

    @GetMapping("/baseline")
    public String baseline() {
        return String.format("baseline — %tQ", new Date());
    }
}
