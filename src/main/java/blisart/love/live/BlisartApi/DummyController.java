package blisart.love.live.BlisartApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {
    @GetMapping("/test")
    public String dummy() {
        return "HELLO BITCHES";
    }
}
