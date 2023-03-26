package blisartLoveLive.blisartApi.race.application.useCase;

import blisartLoveLive.blisartApi.race.domain.service.CreateRaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateRaceUseCase {
    @Autowired
    CreateRaceService createRaceService;

    public void createRace(String name) {
        this.createRaceService.createRace(name);
    }
}
