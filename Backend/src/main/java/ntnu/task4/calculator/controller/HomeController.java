package ntnu.task4.calculator.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Controller
public class HomeController {
    @GetMapping()
    public String home() {
        return "index";
    }

    @GetMapping("/main.js")
    public ResponseEntity<String> getMainJs() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/static/main.js");
        String fileContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fileContent);
    }


    @GetMapping(value = "/styles.css")
    public ResponseEntity<String> getStylesCss() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/static/styles.css");
        String fileContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(fileContent);
    }

}

