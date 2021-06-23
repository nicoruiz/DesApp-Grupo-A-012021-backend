CREATE DATABASE IF NOT EXISTS resena;
USE resena;

CREATE TABLE IF NOT EXISTS title (
    `id` VARCHAR(9) PRIMARY KEY NOT NULL,
    `title_type` VARCHAR(50) NOT NULL,
    `primary_title` VARCHAR(100) NOT NULL,
    `start_year` INT NOT NULL,
    `end_year` INT DEFAULT NULL,
    `is_adult` INT NOT NULL,
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

CREATE TABLE IF NOT EXISTS episode_detail (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `episode_id` VARCHAR(9) NOT NULL,
    `serie_id` VARCHAR(9) NOT NULL,
    `season_number` INT NOT NULL,
    `episode_number` INT NOT NULL,
    FOREIGN KEY (`episode_id`) REFERENCES title(`id`),
    FOREIGN KEY (`serie_id`) REFERENCES title(`id`),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS platform (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `api_key` VARCHAR(255) NOT NULL,
    `credits` DECIMAL(10,2) NOT NULL,
    `processed_requests` INT NOT NULL,
    `price_per_request` DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS review (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `resume` VARCHAR(255) DEFAULT NULL,
    `body` VARCHAR(255) DEFAULT NULL,
    `review_type` VARCHAR(20) NOT NULL,
    `rating` DECIMAL(10,1) NOT NULL,
    `created_on` date DEFAULT NULL,
    `platform_id` BIGINT NOT NULL,
    `platform_user_id` INT NOT NULL,
    `language` VARCHAR(255) DEFAULT NULL,
    `title_id` VARCHAR(9) NOT NULL,
    `has_spoiler` bit(1) DEFAULT NULL,
    `username` VARCHAR(255) DEFAULT NULL,
    `localization` VARCHAR(255) DEFAULT NULL,
    `likes` INT DEFAULT 0,
    `dislikes` INT DEFAULT 0,
    FOREIGN KEY (`platform_id`) REFERENCES platform(`id`),
    FOREIGN KEY (`title_id`) REFERENCES title(`id`),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS report (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `comment` VARCHAR(255) NOT NULL,
    `review_id` BIGINT NOT NULL,
    FOREIGN KEY (`review_id`) REFERENCES review(`id`),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS subscription (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `title_id` VARCHAR(9) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    FOREIGN KEY (`title_id`) REFERENCES title(`id`),
    PRIMARY KEY (`id`)
);