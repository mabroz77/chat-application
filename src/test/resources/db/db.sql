-- MySQL Script generated by MySQL Workbench
-- Sun May  6 14:14:44 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema chatdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema chatdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `chatdb` DEFAULT CHARACTER SET utf8 ;
USE `chatdb` ;

-- -----------------------------------------------------
-- Table `chatdb`.`messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatdb`.`messages` (
  `id_message` INT(11) NOT NULL AUTO_INCREMENT,
  `message` LONGTEXT NOT NULL,
  `date_of_message` DATETIME NOT NULL,
  `identifier` VARCHAR(200) NULL DEFAULT NULL,
  `sender` VARCHAR(50) NULL DEFAULT NULL,
  `recipient` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id_message`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `chatdb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatdb`.`users` (
  `id_user` INT(11) NOT NULL AUTO_INCREMENT,
  `nick` VARCHAR(45) NOT NULL,
  `statusText` VARCHAR(45) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `login_UNIQUE` (`nick` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;