package blisartLoveLive.blisartApi.race.domain.service;

import blisartLoveLive.blisartApi.race.domain.entity.Race;
import blisartLoveLive.blisartApi.race.infrastructure.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateRaceService {
    @Autowired
    private RaceRepository raceRepository;

    public Race createRace(String name) {
        Race race = new Race();
        race.setName(name);

        return this.raceRepository.save(race);
    }
}
