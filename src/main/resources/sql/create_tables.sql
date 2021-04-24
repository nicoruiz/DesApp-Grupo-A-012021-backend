CREATE DATABASE IF NOT EXISTS resena;
USE resena;

CREATE TABLE IF NOT EXISTS title (
    `id` VARCHAR(9) PRIMARY KEY NOT NULL,
    `title_type` VARCHAR(5),
    `primary_title` VARCHAR(100),
    `original_title` VARCHAR(100),
    `is_adult` INT,
    `start_year` INT,
    `end_year` INT NULL,
    `runtime_minutes` VARCHAR(2),
    `genres` VARCHAR(24)
);

CREATE TABLE IF NOT EXISTS actor (
	`id` INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS cast (
	`id` INT NOT NULL AUTO_INCREMENT,
    `title_id` VARCHAR(9) NOT NULL,
    `actor_id` INT NOT NULL,
    FOREIGN KEY (`title_id`) REFERENCES title(`id`),
    FOREIGN KEY (`actor_id`) REFERENCES actor(`id`),
    PRIMARY KEY (`id`)
);