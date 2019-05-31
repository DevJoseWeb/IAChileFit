-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema coopfit
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema coopfit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `coopfit` DEFAULT CHARACTER SET latin1 ;
USE `coopfit` ;

-- -----------------------------------------------------
-- Table `coopfit`.`t_dispositivo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coopfit`.`t_dispositivo` (
  `id_dispositivo` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(255) NOT NULL,
  `cd_pessoa` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id_dispositivo`),
  INDEX `FKp61ek2wr9utkcuhb4ei1ovkd8` (`cd_pessoa` ASC))
ENGINE = MyISAM
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `coopfit`.`t_pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coopfit`.`t_pessoa` (
  `cd_pessoa` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `dt_alteracao` DATETIME NOT NULL,
  `altura` DOUBLE NOT NULL,
  `dt_cadastro` DATETIME NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `foto` TINYBLOB NULL DEFAULT NULL,
  `ds_genero` INT(11) NOT NULL,
  `dt_nascimento` DATETIME NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `recebe_notificacao` BIT(1) NOT NULL,
  `observacao` VARCHAR(255) NULL DEFAULT NULL,
  `peso` DOUBLE NOT NULL,
  `senha` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`cd_pessoa`))
ENGINE = MyISAM
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `coopfit`.`t_dispositivo_sensor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coopfit`.`t_dispositivo_sensor` (
  `id_dispositivo_sensor` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `data` DATETIME NOT NULL,
  `tipo` VARCHAR(255) NOT NULL,
  `valor` DOUBLE NOT NULL,
  `cd_pessoa` BIGINT(20) NOT NULL,
  `id_dispositivo` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_dispositivo_sensor`),
  INDEX `fk_t_dispositivo_sensor_t_pessoa1_idx` (`cd_pessoa` ASC),
  INDEX `fk_t_dispositivo_sensor_t_dispositivo1_idx` (`id_dispositivo` ASC))
ENGINE = MyISAM
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `coopfit`.`t_informativo_saude`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coopfit`.`t_informativo_saude` (
  `id_informacao_saude` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `data` DATETIME NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `cd_pessoa` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_informacao_saude`),
  INDEX `fk_t_informativo_saude_t_pessoa1_idx` (`cd_pessoa` ASC))
ENGINE = MyISAM
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `coopfit`.`t_informe_tratativas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coopfit`.`t_informe_tratativas` (
  `id_informativo` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `data` DATETIME NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `cd_pessoa` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_informativo`),
  INDEX `fk_t_informe_tratativas_t_pessoa_idx` (`cd_pessoa` ASC))
ENGINE = MyISAM
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `coopfit`.`t_notificacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coopfit`.`t_notificacao` (
  `id_notificacao` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `data` DATETIME NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `cd_pessoa` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_notificacao`),
  INDEX `fk_t_notificacao_t_pessoa1_idx` (`cd_pessoa` ASC))
ENGINE = MyISAM
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `coopfit`.`t_perfis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coopfit`.`t_perfis` (
  `perfis` INT(11) NULL DEFAULT NULL,
  `pessoa_cd_pessoa` BIGINT(20) NOT NULL)
ENGINE = MyISAM
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `coopfit`.`t_questionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coopfit`.`t_questionario` (
  `id_questionario` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `dt_questionario` DATETIME NULL DEFAULT NULL,
  `qt_estresse` INT(11) NULL DEFAULT NULL,
  `qt_copo_agua` INT(11) NULL DEFAULT NULL,
  `tp_sentimento` VARCHAR(255) NULL DEFAULT NULL,
  `cd_pessoa` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_questionario`),
  INDEX `fk_t_questionario_t_pessoa1_idx` (`cd_pessoa` ASC))
ENGINE = MyISAM
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `coopfit`.`t_rotina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coopfit`.`t_rotina` (
  `id_rotina` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `data` DATETIME NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `tipo` INT(11) NOT NULL,
  `cd_pessoa` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_rotina`),
  INDEX `fk_t_rotina_t_pessoa1_idx` (`cd_pessoa` ASC))
ENGINE = MyISAM
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
