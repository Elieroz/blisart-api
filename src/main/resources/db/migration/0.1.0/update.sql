CREATE TABLE IF NOT EXISTS race(
    race_id int AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (race_id)
);

CREATE TABLE IF NOT EXISTS blizz_character(
    character_id int AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    faction_id int NOT NULL,
	race_id int NOT NULL,
    class_id int NOT NULL,
    title VARCHAR (50) NOT NULL,
    is_dead BOOLEAN,

    PRIMARY KEY (character_id),
    FOREIGN KEY (race_id) REFERENCES race(race_id)
);