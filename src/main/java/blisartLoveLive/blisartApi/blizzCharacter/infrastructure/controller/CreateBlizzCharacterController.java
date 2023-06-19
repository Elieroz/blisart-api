package blisartLoveLive.blisartApi.blizzCharacter.infrastructure.controller;

import blisartLoveLive.blisartApi.blizzCharacter.application.useCase.CreateBlizzCharacterUseCase;
import blisartLoveLive.blisartApi.blizzCharacter.domain.service.valueObject.CreateBlizzCharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/character")
public class CreateBlizzCharacterController {
    @Autowired
    CreateBlizzCharacterUseCase createBlizzCharacterUseCase;

    @PostMapping
    public void createBlizzCharacter(@RequestBody CreateBlizzCharacterDTO request) {
        this.createBlizzCharacterUseCase.createBlizzCharacter(request);
    }
}
