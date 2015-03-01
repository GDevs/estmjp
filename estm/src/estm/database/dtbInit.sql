CREATE  DATABASE IF NOT EXISTS estm CHARACTER SET 'utf8'  COLLATE 'utf8_german2_ci'
CREATE TABLE IF NOT EXISTS estm.personen (`ID` INT(32), `name` VARCHAR(64), vorname VARCHAR(64), rechte INT(5), `status` VARCHAR(64), kennwort VARCHAR(128),PRIMARY KEY (ID))
CREATE TABLE IF NOT EXISTS estm.termine (person1 INT(32), person2 INT(32), zeitpunkt INT(16), `version` INT(16))
CREATE TABLE IF NOT EXISTS estm.terminwunsch (person1 INT(32), person2 INT(32), zeitpunkt INT(16))
