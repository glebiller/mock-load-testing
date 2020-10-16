package fr.kissy.mockrestapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BaselineController {

    @GetMapping("/baseline")
    public String greeting() {
        return String.format("baseline — %tc", new Date());
    }
}
