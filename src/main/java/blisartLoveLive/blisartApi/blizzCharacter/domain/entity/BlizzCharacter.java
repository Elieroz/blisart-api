package blisartLoveLive.blisartApi.blizzCharacter.domain.entity;

import jakarta.persistence.*;

@Entity
public class BlizzCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer characterId;
    private Integer factionId;
    private String name;
    private Integer classId;
    private String title;
    private boolean isDead;
    private Integer raceId;

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getFactionId() {
        return factionId;
    }

    public void setFactionId(Integer factionId) {
        this.factionId = factionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public Integer getRaceId() {
        return raceId;
    }

    public void setRaceId(Integer raceId) {
        this.raceId = raceId;
    }
}
