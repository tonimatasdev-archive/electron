package dev.tonimatas.electron.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerMK {
    @GetMapping()
    public String home() {
        return "DiscordMK API works fine!";
    }
}
