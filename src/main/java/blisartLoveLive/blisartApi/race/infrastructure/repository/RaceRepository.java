package blisartLoveLive.blisartApi.race.infrastructure.repository;

import blisartLoveLive.blisartApi.race.domain.entity.Race;
import org.springframework.data.repository.CrudRepository;

public interface RaceRepository extends CrudRepository<Race, Integer> {
}
