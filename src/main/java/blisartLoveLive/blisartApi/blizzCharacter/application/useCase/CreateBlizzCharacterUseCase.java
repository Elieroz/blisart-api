package blisartLoveLive.blisartApi.blizzCharacter.application.useCase;

import blisartLoveLive.blisartApi.blizzCharacter.domain.entity.BlizzCharacter;
import blisartLoveLive.blisartApi.blizzCharacter.domain.service.CreateBlizzCharacterService;
import blisartLoveLive.blisartApi.blizzCharacter.domain.service.valueObject.CreateBlizzCharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateBlizzCharacterUseCase {
    @Autowired
    CreateBlizzCharacterService createBlizzCharacterService;

    public BlizzCharacter createBlizzCharacter(CreateBlizzCharacterDTO request) {
        return this.createBlizzCharacterService.createBlizzCharacter(request);
    }
}
