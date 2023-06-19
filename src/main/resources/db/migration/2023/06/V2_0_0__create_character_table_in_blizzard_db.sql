CREATE TABLE IF NOT EXISTS blizz_character(
    character_id int AUTO_INCREMENT,
    faction_id int NOT NULL,
    name VARCHAR(100) NOT NULL,
    class_id int NOT NULL,
    title VARCHAR (50) NOT NULL,
    is_dead BOOLEAN,
    race_id int unsigned,
    PRIMARY KEY (character_id),
    FOREIGN KEY (faction_id) REFERENCES race(race_id)
);