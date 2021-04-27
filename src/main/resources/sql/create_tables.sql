CREATE DATABASE IF NOT EXISTS resena;
USE resena; 

CREATE TABLE IF NOT EXISTS title (
    `id` VARCHAR(9) PRIMARY KEY NOT NULL,
    `title_type` VARCHAR(50) NOT NULL,
    `primary_title` VARCHAR(100) NOT NULL,
    `is_adult` INT NOT NULL,
    `start_year` INT NOT NULL,
    `end_year` INT DEFAULT NULL,
    `genres` VARCHAR(1000)
);

CREATE TABLE IF NOT EXISTS person (
    `id` INT NOT NULL AUTO_INCREMENT,
    `primary_name` VARCHAR(100),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS title_person (
    `title_id` VARCHAR(9) NOT NULL,
    `person_id` INT NOT NULL,
    FOREIGN KEY (`title_id`) REFERENCES title(`id`),
    FOREIGN KEY (`person_id`) REFERENCES person(`id`)
);

CREATE TABLE IF NOT EXISTS episode (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title_id` VARCHAR(9) NOT NULL,
    `season_number` INT NOT NULL,
    `episode_number` INT NOT NULL,
    FOREIGN KEY (`title_id`) REFERENCES title(`id`),
    PRIMARY KEY (`id`)
);