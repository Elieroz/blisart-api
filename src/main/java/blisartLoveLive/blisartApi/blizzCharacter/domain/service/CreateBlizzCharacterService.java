package blisartLoveLive.blisartApi.blizzCharacter.domain.service;

import blisartLoveLive.blisartApi.blizzCharacter.domain.entity.BlizzCharacter;
import blisartLoveLive.blisartApi.blizzCharacter.domain.service.valueObject.CreateBlizzCharacterDTO;
import blisartLoveLive.blisartApi.blizzCharacter.infrastructure.repository.BlizzCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateBlizzCharacterService {
    @Autowired
    private BlizzCharacterRepository blizzCharacterRepository;

    public BlizzCharacter createBlizzCharacter(CreateBlizzCharacterDTO request) {
        BlizzCharacter blizzCharacter = new BlizzCharacter();
        blizzCharacter.setFactionId(request.getFactionId());
        blizzCharacter.setName(request.getName());
        blizzCharacter.setClassId(request.getClassId());
        blizzCharacter.setTitle(request.getTitle());
        blizzCharacter.setIsDead(request.isDead());
        blizzCharacter.setRaceId(request.getRaceId());

        return this.blizzCharacterRepository.save(blizzCharacter);
    }
}
