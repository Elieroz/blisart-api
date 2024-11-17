package blisartLoveLive.blisartApi.blizzCharacter.domain.service.valueObject;

public record CreateBlizzCharacterDTO(
		Integer factionId,
		String name,
		Integer classId,
		String title,
		Boolean isDead,
		Integer raceId
) {
}
