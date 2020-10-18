package fr.kissy.mockrestapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ExternalController {

    @GetMapping("/external")
    public String external() {
        return String.format("todo — %tQ", new Date());
    }
}
