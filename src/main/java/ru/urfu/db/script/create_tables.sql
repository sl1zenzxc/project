drop table if exists Forbes;

drop table if exists Age;

drop table if exists Company;

drop table if exists Country;

drop table if exists Human;

drop table if exists Industry;

drop table if exists Networth;

drop table if exists Rank;

CREATE TABLE IF NOT EXISTS `Rank` (
                                      `id` INTEGER PRIMARY KEY,
                                      `rankId` INTEGER NOT NULL
);


CREATE TABLE IF NOT EXISTS `Age` (
                                     `id` INTEGER PRIMARY KEY,
                                     `age` INTEGER NOT NULL
);


CREATE TABLE IF NOT EXISTS `Company` (
                                         `id` INTEGER PRIMARY KEY,
                                         `name` VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS `Country` (
                                         `id` INTEGER PRIMARY KEY,
                                         `name` VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS `Human` (
                                       `id` INTEGER PRIMARY KEY,
                                       `name` VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS `Industry` (
                                          `id` INTEGER PRIMARY KEY,
                                          `name` VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS `Networth` (
                                          `id` INTEGER PRIMARY KEY,
                                          `name` REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS `Forbes` (
                                        `id` INTEGER PRIMARY KEY,
                                        'rank_id' INTEGER NOT NULL,
                                        'age_id' INTEGER NOT NULL,
                                        'company_id' INTEGER NOT NULL,
                                        'country_id' INTEGER NOT NULL,
                                        'human_id' INTEGER NOT NULL,
                                        'industry_id' INTEGER NOT NULL,
                                        'networth_id' INTEGER NOT NULL,
                                        FOREIGN KEY (rank_id) REFERENCES `Rank` (`id`),
    FOREIGN KEY (age_id) REFERENCES `Age` (`id`),
    FOREIGN KEY (company_id) REFERENCES `Company` (`id`),
    FOREIGN KEY (country_id) REFERENCES `Country` (`id`),
    FOREIGN KEY (human_id) REFERENCES `Human` (`id`),
    FOREIGN KEY (industry_id) REFERENCES `Industry` (`id`),
    FOREIGN KEY (networth_id) REFERENCES `Networth` (`id`)
    );