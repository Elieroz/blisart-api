CREATE TABLE IF NOT EXISTS race(
    race_id int AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (race_id)
);

INSERT INTO race (name)
VALUES ('elfo de la noche');