package fr.kissy.mockloadtesting;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class SimpleErrorController implements ErrorController {

    private static final String ERROR_MAPPING = "/error";

    @RequestMapping(value = ERROR_MAPPING)
    public ResponseEntity<String> error(HttpServletResponse response) {
        return new ResponseEntity<>("request failed", HttpStatus.valueOf(response.getStatus()));
    }

    @Override
    public String getErrorPath() {
        return ERROR_MAPPING;
    }
}