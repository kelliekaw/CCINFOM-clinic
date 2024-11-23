-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema clinic
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `clinic` DEFAULT CHARACTER SET utf8mb3 ;
SHOW WARNINGS;
USE `clinic` ;

-- -----------------------------------------------------
-- Table `clinic`.`doctors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clinic`.`doctors` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `clinic`.`doctors` (
  `doctor_id` INT NOT NULL AUTO_INCREMENT,
  `last_name` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `middle_initial` VARCHAR(5) NULL DEFAULT NULL,
  `gender` ENUM('M', 'F') NOT NULL,
  `birthdate` DATE NOT NULL,
  `consultation_rate` FLOAT NOT NULL,
  `mobile_number` BIGINT NOT NULL,
  `email_address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`doctor_id`),
  UNIQUE INDEX `email_address_UNIQUE` (`email_address` ASC) VISIBLE,
  UNIQUE INDEX `mobile_number_UNIQUE` (`mobile_number` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `clinic`.`ref_specializations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clinic`.`ref_specializations` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `clinic`.`ref_specializations` (
  `speci_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`speci_id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `clinic`.`doctor_speci`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clinic`.`doctor_speci` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `clinic`.`doctor_speci` (
  `doctor_id` INT NOT NULL,
  `speci_id` INT NOT NULL,
  PRIMARY KEY (`doctor_id`, `speci_id`),
  INDEX `fk_doctor_speci_doctors1_idx` (`doctor_id` ASC) VISIBLE,
  INDEX `fk_doctor_speci_REF_specializations1_idx` (`speci_id` ASC) VISIBLE,
  CONSTRAINT `fk_doctor_speci_doctors1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `clinic`.`doctors` (`doctor_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_doctor_speci_REF_specializations1`
    FOREIGN KEY (`speci_id`)
    REFERENCES `clinic`.`ref_specializations` (`speci_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `clinic`.`drugs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clinic`.`drugs` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `clinic`.`drugs` (
  `drug_id` INT NOT NULL AUTO_INCREMENT,
  `generic_name` VARCHAR(100) NOT NULL,
  `brand_name` VARCHAR(45) NULL DEFAULT NULL,
  `price` FLOAT NOT NULL,
  `type` ENUM('Capsule', 'Tablet', 'Ointment') NOT NULL,
  PRIMARY KEY (`drug_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `clinic`.`ref_ailments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clinic`.`ref_ailments` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `clinic`.`ref_ailments` (
  `ailment_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `speci_id` INT NOT NULL,
  PRIMARY KEY (`ailment_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_ailments_REF_specializations1_idx` (`speci_id` ASC) VISIBLE,
  CONSTRAINT `fk_ailments_REF_specializations1`
    FOREIGN KEY (`speci_id`)
    REFERENCES `clinic`.`ref_specializations` (`speci_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `clinic`.`patients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clinic`.`patients` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `clinic`.`patients` (
  `patient_id` INT NOT NULL AUTO_INCREMENT,
  `last_name` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `middle_initial` VARCHAR(5) NULL DEFAULT NULL,
  `gender` ENUM('M', 'F') NOT NULL,
  `birthdate` DATE NOT NULL,
  `mobile_number` BIGINT NOT NULL,
  `email_address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`patient_id`),
  UNIQUE INDEX `mobile_number_UNIQUE` (`mobile_number` ASC) VISIBLE,
  UNIQUE INDEX `email_address_UNIQUE` (`email_address` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `clinic`.`visits`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clinic`.`visits` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `clinic`.`visits` (
  `visit_id` INT NOT NULL AUTO_INCREMENT,
  `patient_id` INT NOT NULL,
  `log_in` DATETIME NOT NULL,
  `ailment_id` INT NOT NULL,
  `doctor_id` INT NOT NULL,
  PRIMARY KEY (`visit_id`),
  INDEX `fk_visits_ailments1_idx` (`ailment_id` ASC) VISIBLE,
  INDEX `fk_visits_patients1_idx` (`patient_id` ASC) VISIBLE,
  INDEX `fk_visits_doctors1_idx` (`doctor_id` ASC) VISIBLE,
  CONSTRAINT `fk_visits_ailments1`
    FOREIGN KEY (`ailment_id`)
    REFERENCES `clinic`.`ref_ailments` (`ailment_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_visits_doctors1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `clinic`.`doctors` (`doctor_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_visits_patients1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `clinic`.`patients` (`patient_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `clinic`.`sales`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clinic`.`sales` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `clinic`.`sales` (
  `sale_id` INT NOT NULL AUTO_INCREMENT,
  `visit_id` INT NOT NULL,
  `amt_paid` FLOAT NOT NULL,
  PRIMARY KEY (`sale_id`),
  INDEX `fk_sales_visits1_idx` (`visit_id` ASC) VISIBLE,
  CONSTRAINT `fk_sales_visits1`
    FOREIGN KEY (`visit_id`)
    REFERENCES `clinic`.`visits` (`visit_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `clinic`.`drugs_sold`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clinic`.`drugs_sold` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `clinic`.`drugs_sold` (
  `sale_id` INT NOT NULL,
  `drug_id` INT NOT NULL,
  `qty` INT NOT NULL,
  PRIMARY KEY (`sale_id`, `drug_id`),
  INDEX `fk_drugs_bought_sales1_idx` (`sale_id` ASC) VISIBLE,
  INDEX `fk_drugs_sold_drugs1_idx` (`drug_id` ASC) VISIBLE,
  CONSTRAINT `fk_drugs_bought_sales1`
    FOREIGN KEY (`sale_id`)
    REFERENCES `clinic`.`sales` (`sale_id`),
  CONSTRAINT `fk_drugs_sold_drugs1`
    FOREIGN KEY (`drug_id`)
    REFERENCES `clinic`.`drugs` (`drug_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `clinic`.`inventory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clinic`.`inventory` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `clinic`.`inventory` (
  `drug_id` INT NOT NULL,
  `qty` INT NOT NULL,
  INDEX `fk_inventory_drugs1_idx` (`drug_id` ASC) VISIBLE,
  PRIMARY KEY (`drug_id`),
  CONSTRAINT `fk_inventory_drugs1`
    FOREIGN KEY (`drug_id`)
    REFERENCES `clinic`.`drugs` (`drug_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `clinic`.`prescribed_drugs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clinic`.`prescribed_drugs` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `clinic`.`prescribed_drugs` (
  `visit_id` INT NOT NULL,
  `drug_id` INT NOT NULL,
  `qty_drugs` INT NOT NULL,
  PRIMARY KEY (`visit_id`, `drug_id`),
  INDEX `fk_prescribed_drugs_drug1_idx` (`drug_id` ASC) VISIBLE,
  INDEX `fk_prescribed_drugs_visits1_idx` (`visit_id` ASC) VISIBLE,
  CONSTRAINT `fk_prescribed_drugs_drug1`
    FOREIGN KEY (`drug_id`)
    REFERENCES `clinic`.`drugs` (`drug_id`),
  CONSTRAINT `fk_prescribed_drugs_visits1`
    FOREIGN KEY (`visit_id`)
    REFERENCES `clinic`.`visits` (`visit_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `clinic`.`shipments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clinic`.`shipments` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `clinic`.`shipments` (
  `shipment_id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `shipment_cost` FLOAT NOT NULL,
  PRIMARY KEY (`shipment_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `clinic`.`shipment_drug`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clinic`.`shipment_drug` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `clinic`.`shipment_drug` (
  `shipment_id` INT NOT NULL,
  `drug_id` INT NOT NULL,
  `qty` INT NOT NULL,
  PRIMARY KEY (`shipment_id`, `drug_id`),
  INDEX `fk_shipment_drug_shipments1_idx` (`shipment_id` ASC) VISIBLE,
  INDEX `fk_shipment_drug_drugs1_idx` (`drug_id` ASC) VISIBLE,
  CONSTRAINT `fk_shipment_drug_drugs1`
    FOREIGN KEY (`drug_id`)
    REFERENCES `clinic`.`drugs` (`drug_id`),
  CONSTRAINT `fk_shipment_drug_shipments1`
    FOREIGN KEY (`shipment_id`)
    REFERENCES `clinic`.`shipments` (`shipment_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;

INSERT INTO clinic.ref_specializations (speci_id, title) VALUES
(1, 'Immunologist'),
(2, 'Dermatologist'),
(3, 'Gastroenterologist'),
(4, 'Rheumatologist'),
(5, 'General Physician');

INSERT INTO clinic.drugs (drug_id, generic_name, brand_name, price, type) VALUES
(1, 'Hydrocortisone', 'Eczacort', 332.5, 'Ointment'),
(2, 'Prednisolone', 'Histacort', 202.5, 'Ointment'),
(3, 'Loratadine', 'Allerta', 26.08, 'Tablet'),
(4, 'Loratadine', 'Claritin', 37.00, 'Tablet'),
(5, 'Cetirizine', 'Alnix', 33.47, 'Tablet'),
(6, 'Lactoferrin', 'Lactezin', 33.00, 'Capsule'),
(7, 'Loperamide', 'Diatabs', 8.5, 'Capsule'),
(8, 'Aluminum Hydroxide, Magnesium Hydroxide, Simeticone', 'Kremil S', 9.75, 'Capsule'),
(9, 'Naproxen Sodium', 'Skelan', 9.50, 'Tablet'),
(10, 'Phenylephrine HCl, Chlorphenamine Maleate, Paracetamol, Zinc', 'Neozep Forte', 7.00, 'Tablet'),
(11, 'Paracetamol', 'Biogesic', 4.75, 'Tablet'),
(12, 'Phenylephrine HCl, Chlorphenamine Maleate, Paracetamol', 'Bioflu', 9.00, 'Tablet'),
(13, 'Meclizine HCl', 'Dizitab', 11.00, 'Tablet'),
(14, 'Ibuprofen', 'Advil', 9.00, 'Capsule');

INSERT INTO clinic.ref_ailments (ailment_id, name, speci_id) VALUES
(1, 'Eczema', 1),
(2, 'Insect Sting', 1),
(3, 'Food Allergy', 1),
(4, 'Allergy Reactions', 1),
(5, 'Acne', 2),
(6, 'Hyperacidity', 3),
(7, 'Diarrhea', 3),
(8, 'Arthritis', 4),
(9, 'Joint Pain', 4),
(10, 'Gout', 4),
(11, 'Common Cold', 5),
(12, 'Fever', 5),
(13, 'Flu', 5),
(14, 'Nausea', 5),
(15, 'Muscle Pain', 5);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
