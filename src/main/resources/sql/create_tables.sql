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
    `id` VARCHAR(9) PRIMARY KEY NOT NULL,
    `primary_name` VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS title_person (
    `title_id` VARCHAR(9) NOT NULL,
    `person_id` VARCHAR(9) NOT NULL,
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

CREATE TABLE IF NOT EXISTS user_review (
    `id` INT NOT NULL AUTO_INCREMENT,
    `body` VARCHAR(255) DEFAULT NULL,
    `date` datetime DEFAULT NULL,
    `language` VARCHAR(255) DEFAULT NULL,
    `platform_type` VARCHAR(255) DEFAULT NULL,
    `platform_user_id` INT NOT NULL,
    `rating` INT NOT NULL,
    `title` VARCHAR(255) DEFAULT NULL,
    `has_spoiler` bit(1) NOT NULL,
    `localization` VARCHAR(255) DEFAULT NULL,
    `username` VARCHAR(255) DEFAULT NULL,
    `title_id` VARCHAR(9) NOT NULL,
    FOREIGN KEY (`title_id`) REFERENCES title(`id`),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS premium_review (
    `id` INT NOT NULL AUTO_INCREMENT,
    `body` VARCHAR(255) DEFAULT NULL,
    `date` datetime DEFAULT NULL,
    `language` VARCHAR(255) DEFAULT NULL,
    `platform_type` VARCHAR(255) DEFAULT NULL,
    `platform_user_id` INT NOT NULL,
    `rating` INT NOT NULL,
    `title` VARCHAR(255) DEFAULT NULL,
    `title_id` varchar(9) NOT NULL,
    FOREIGN KEY (`title_id`) REFERENCES title(`id`),
    PRIMARY KEY (`id`)
);