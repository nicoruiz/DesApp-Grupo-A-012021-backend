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
        `birth_year` INT,
        `death_year` INT, 
        `primary_name` VARCHAR(100), 
        `professions` VARCHAR(3000)
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS cast (
	`id` INT NOT NULL AUTO_INCREMENT,
    `title_id` VARCHAR(9) NOT NULL,
    `actor_id` INT NOT NULL,
    `category` VARCHAR(100) NOT NULL,
    `characters` VARCHAR(3000) NOT NULL,
    `job` VARCHAR(100),
    `ordering` INT NOT NULLca,
    FOREIGN KEY (`title_id`) REFERENCES title(`id`),
    FOREIGN KEY (`actor_id`) REFERENCES actor(`id`),
    PRIMARY KEY (`id`)
);