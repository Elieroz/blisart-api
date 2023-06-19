package blisartLoveLive.blisartApi.blizzCharacter.infrastructure.repository;

import blisartLoveLive.blisartApi.blizzCharacter.domain.entity.BlizzCharacter;
import org.springframework.data.repository.CrudRepository;

public interface BlizzCharacterRepository extends CrudRepository<BlizzCharacter, Integer> {
}
