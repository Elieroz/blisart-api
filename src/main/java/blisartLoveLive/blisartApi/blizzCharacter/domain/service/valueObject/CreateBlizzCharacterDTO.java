package blisartLoveLive.blisartApi.blizzCharacter.domain.service.valueObject;

public class CreateBlizzCharacterDTO {

    private int factionId;
    private String name;
    private int classId;
    private String title;
    private boolean isDead;
    private int raceId;

    public CreateBlizzCharacterDTO(
            int factionId,
            String name,
            int classId,
            String title,
            boolean isDead,
            int raceId
    ) {
        this.factionId = factionId;
        this.name = name;
        this.classId = classId;
        this.title = title;
        this.isDead = isDead;
        this.raceId = raceId;
    }

    public int getFactionId() {
        return factionId;
    }

    public void setFactionId(int factionId) {
        this.factionId = factionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
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

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }
}
