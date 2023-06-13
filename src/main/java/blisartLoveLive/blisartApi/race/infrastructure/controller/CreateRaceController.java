package blisartLoveLive.blisartApi.race.infrastructure.controller;

import blisartLoveLive.blisartApi.race.application.useCase.CreateRaceUseCase;
import blisartLoveLive.blisartApi.race.application.useCase.request.CreateRaceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/race")
public class CreateRaceController {
    @Autowired
    CreateRaceUseCase createRaceUseCase;

    @PostMapping
    public void createRace(@RequestBody CreateRaceRequest request) {
        this.createRaceUseCase.createRace(request.getName());
    }
}
