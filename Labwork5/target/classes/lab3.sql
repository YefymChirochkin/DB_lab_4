-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Driver_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Driver_rating` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Driver_rating` (
  `Average_driver_rating_id` INT NOT NULL,
  `Average_driver_rating` INT NOT NULL,
  PRIMARY KEY (`Average_driver_rating_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Driver`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Driver` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Driver` (
  `Driver_id` INT NOT NULL AUTO_INCREMENT,
  `Age` INT NOT NULL,
  `Driving_experience` INT NOT NULL,
  `License_number` INT NOT NULL,
  `Category` VARCHAR(5) NOT NULL,
  `Total_number_of_trips` INT NOT NULL,
  `Driver_rating_Average_driver_rating` INT NOT NULL,
  PRIMARY KEY (`Driver_id`),
  INDEX `fk_Driver_Driver_rating1_idx` (`Driver_rating_Average_driver_rating` ASC) VISIBLE,
  CONSTRAINT `fk_Driver_Driver_rating1`
    FOREIGN KEY (`Driver_rating_Average_driver_rating`)
    REFERENCES `mydb`.`Driver_rating` (`Average_driver_rating_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Car`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Car` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Car` (
  `Car_id` INT NOT NULL AUTO_INCREMENT,
  `VIN_code` INT NOT NULL,
  `Brand` VARCHAR(45) NOT NULL,
  `Number_of_seats` INT NOT NULL,
  `Conditioning` TINYINT NULL,
  `Car_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Car_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Client` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Client` (
  `Client_id` INT NOT NULL AUTO_INCREMENT,
  `Age` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Client_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Driver_has_Car`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Driver_has_Car` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Driver_has_Car` (
  `Driver_Driver_id` INT NOT NULL,
  `Car_Car_id` INT NOT NULL,
  PRIMARY KEY (`Driver_Driver_id`, `Car_Car_id`),
  INDEX `fk_Driver_has_Car_Car1_idx` (`Car_Car_id` ASC) VISIBLE,
  INDEX `fk_Driver_has_Car_Driver_idx` (`Driver_Driver_id` ASC) VISIBLE,
  CONSTRAINT `fk_Driver_has_Car_Driver`
    FOREIGN KEY (`Driver_Driver_id`)
    REFERENCES `mydb`.`Driver` (`Driver_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Driver_has_Car_Car1`
    FOREIGN KEY (`Car_Car_id`)
    REFERENCES `mydb`.`Car` (`Car_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Driver_has_Client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Driver_has_Client` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Driver_has_Client` (
  `Driver_Driver_id` INT NOT NULL,
  `Client_Client_id` INT NOT NULL,
  PRIMARY KEY (`Driver_Driver_id`, `Client_Client_id`),
  INDEX `fk_Driver_has_Client_Client1_idx` (`Client_Client_id` ASC) VISIBLE,
  INDEX `fk_Driver_has_Client_Driver1_idx` (`Driver_Driver_id` ASC) VISIBLE,
  CONSTRAINT `fk_Driver_has_Client_Driver1`
    FOREIGN KEY (`Driver_Driver_id`)
    REFERENCES `mydb`.`Driver` (`Driver_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Driver_has_Client_Client1`
    FOREIGN KEY (`Client_Client_id`)
    REFERENCES `mydb`.`Client` (`Client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Car_Booking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Car_Booking` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Car_Booking` (
  `Car_Booking_id` INT NOT NULL AUTO_INCREMENT,
  `Date` DATE NOT NULL,
  `Time` TIME NOT NULL,
  PRIMARY KEY (`Car_Booking_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Client_has_Car_Booking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Client_has_Car_Booking` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Client_has_Car_Booking` (
  `Client_Client_id` INT NOT NULL,
  `Car_Booking_Car_Booking_id` INT NOT NULL,
  PRIMARY KEY (`Client_Client_id`, `Car_Booking_Car_Booking_id`),
  INDEX `fk_Client_has_Car_Booking_Car_Booking1_idx` (`Car_Booking_Car_Booking_id` ASC) VISIBLE,
  INDEX `fk_Client_has_Car_Booking_Client1_idx` (`Client_Client_id` ASC) VISIBLE,
  CONSTRAINT `fk_Client_has_Car_Booking_Client1`
    FOREIGN KEY (`Client_Client_id`)
    REFERENCES `mydb`.`Client` (`Client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Client_has_Car_Booking_Car_Booking1`
    FOREIGN KEY (`Car_Booking_Car_Booking_id`)
    REFERENCES `mydb`.`Car_Booking` (`Car_Booking_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Car_has_Car_Booking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Car_has_Car_Booking` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Car_has_Car_Booking` (
  `Car_Car_id` INT NOT NULL,
  `Car_Booking_Car_Booking_id` INT NOT NULL,
  PRIMARY KEY (`Car_Car_id`, `Car_Booking_Car_Booking_id`),
  INDEX `fk_Car_has_Car_Booking_Car_Booking1_idx` (`Car_Booking_Car_Booking_id` ASC) VISIBLE,
  INDEX `fk_Car_has_Car_Booking_Car1_idx` (`Car_Car_id` ASC) VISIBLE,
  CONSTRAINT `fk_Car_has_Car_Booking_Car1`
    FOREIGN KEY (`Car_Car_id`)
    REFERENCES `mydb`.`Car` (`Car_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Car_has_Car_Booking_Car_Booking1`
    FOREIGN KEY (`Car_Booking_Car_Booking_id`)
    REFERENCES `mydb`.`Car_Booking` (`Car_Booking_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`Driver_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Driver_rating` (`Average_driver_rating_id`, `Average_driver_rating`) VALUES (1, 3);
INSERT INTO `mydb`.`Driver_rating` (`Average_driver_rating_id`, `Average_driver_rating`) VALUES (2, 4);
INSERT INTO `mydb`.`Driver_rating` (`Average_driver_rating_id`, `Average_driver_rating`) VALUES (3, 4);
INSERT INTO `mydb`.`Driver_rating` (`Average_driver_rating_id`, `Average_driver_rating`) VALUES (4, 5);
INSERT INTO `mydb`.`Driver_rating` (`Average_driver_rating_id`, `Average_driver_rating`) VALUES (5, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Driver`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Driver` (`Driver_id`, `Age`, `Driving_experience`, `License_number`, `Category`, `Total_number_of_trips`, `Driver_rating_Average_driver_rating`) VALUES (1, 23, 3, 33363, 'B', 214, 4);
INSERT INTO `mydb`.`Driver` (`Driver_id`, `Age`, `Driving_experience`, `License_number`, `Category`, `Total_number_of_trips`, `Driver_rating_Average_driver_rating`) VALUES (2, 32, 8, 32487, 'B', 653, 3);
INSERT INTO `mydb`.`Driver` (`Driver_id`, `Age`, `Driving_experience`, `License_number`, `Category`, `Total_number_of_trips`, `Driver_rating_Average_driver_rating`) VALUES (3, 26, 7, 13845, 'D', 812, 4);
INSERT INTO `mydb`.`Driver` (`Driver_id`, `Age`, `Driving_experience`, `License_number`, `Category`, `Total_number_of_trips`, `Driver_rating_Average_driver_rating`) VALUES (4, 41, 9, 86476, 'C', 983, 5);
INSERT INTO `mydb`.`Driver` (`Driver_id`, `Age`, `Driving_experience`, `License_number`, `Category`, `Total_number_of_trips`, `Driver_rating_Average_driver_rating`) VALUES (5, 30, 6, 33674, 'C', 368, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Car`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Car` (`Car_id`, `VIN_code`, `Brand`, `Number_of_seats`, `Conditioning`, `Car_number`) VALUES (1, 2356, 'Mercedes', 4, 1, 'ВС3678ОО');
INSERT INTO `mydb`.`Car` (`Car_id`, `VIN_code`, `Brand`, `Number_of_seats`, `Conditioning`, `Car_number`) VALUES (2, 6548, 'Mercedes', 4, 0, 'АК1367АВ');
INSERT INTO `mydb`.`Car` (`Car_id`, `VIN_code`, `Brand`, `Number_of_seats`, `Conditioning`, `Car_number`) VALUES (3, 1234, 'BMW', 4, 1, 'АС2468АК');
INSERT INTO `mydb`.`Car` (`Car_id`, `VIN_code`, `Brand`, `Number_of_seats`, `Conditioning`, `Car_number`) VALUES (4, 3678, 'BMW', 4, 0, 'ВО2467ОП');
INSERT INTO `mydb`.`Car` (`Car_id`, `VIN_code`, `Brand`, `Number_of_seats`, `Conditioning`, `Car_number`) VALUES (5, 2468, 'Mercedes', 4, 0, 'АА0055ВР');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Client`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Client` (`Client_id`, `Age`, `Name`, `Surname`) VALUES (1, 18, 'Yefym', 'Chirochkin');
INSERT INTO `mydb`.`Client` (`Client_id`, `Age`, `Name`, `Surname`) VALUES (2, 20, 'Nazar', 'Gurlo');
INSERT INTO `mydb`.`Client` (`Client_id`, `Age`, `Name`, `Surname`) VALUES (3, 21, 'Ivan', 'Rachivskiy');
INSERT INTO `mydb`.`Client` (`Client_id`, `Age`, `Name`, `Surname`) VALUES (4, 35, 'Stepan', 'Nechuk');
INSERT INTO `mydb`.`Client` (`Client_id`, `Age`, `Name`, `Surname`) VALUES (5, 42, 'Oleg', 'Vasutnuk');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Driver_has_Car`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Driver_has_Car` (`Driver_Driver_id`, `Car_Car_id`) VALUES (1, 1);
INSERT INTO `mydb`.`Driver_has_Car` (`Driver_Driver_id`, `Car_Car_id`) VALUES (2, 2);
INSERT INTO `mydb`.`Driver_has_Car` (`Driver_Driver_id`, `Car_Car_id`) VALUES (3, 3);
INSERT INTO `mydb`.`Driver_has_Car` (`Driver_Driver_id`, `Car_Car_id`) VALUES (4, 4);
INSERT INTO `mydb`.`Driver_has_Car` (`Driver_Driver_id`, `Car_Car_id`) VALUES (5, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Driver_has_Client`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Driver_has_Client` (`Driver_Driver_id`, `Client_Client_id`) VALUES (1, 1);
INSERT INTO `mydb`.`Driver_has_Client` (`Driver_Driver_id`, `Client_Client_id`) VALUES (2, 2);
INSERT INTO `mydb`.`Driver_has_Client` (`Driver_Driver_id`, `Client_Client_id`) VALUES (3, 3);
INSERT INTO `mydb`.`Driver_has_Client` (`Driver_Driver_id`, `Client_Client_id`) VALUES (4, 4);
INSERT INTO `mydb`.`Driver_has_Client` (`Driver_Driver_id`, `Client_Client_id`) VALUES (5, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Car_Booking`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Car_Booking` (`Car_Booking_id`, `Date`, `Time`) VALUES (1, '2020-12-26', '13:25:00');
INSERT INTO `mydb`.`Car_Booking` (`Car_Booking_id`, `Date`, `Time`) VALUES (2, '2020-12-27', '10:00:00');
INSERT INTO `mydb`.`Car_Booking` (`Car_Booking_id`, `Date`, `Time`) VALUES (3, '2020-12-27', '17:50:00');
INSERT INTO `mydb`.`Car_Booking` (`Car_Booking_id`, `Date`, `Time`) VALUES (4, '2020-12-28', '15:30:00');
INSERT INTO `mydb`.`Car_Booking` (`Car_Booking_id`, `Date`, `Time`) VALUES (5, '2020-12-29', '20:00:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Client_has_Car_Booking`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Client_has_Car_Booking` (`Client_Client_id`, `Car_Booking_Car_Booking_id`) VALUES (1, 1);
INSERT INTO `mydb`.`Client_has_Car_Booking` (`Client_Client_id`, `Car_Booking_Car_Booking_id`) VALUES (2, 2);
INSERT INTO `mydb`.`Client_has_Car_Booking` (`Client_Client_id`, `Car_Booking_Car_Booking_id`) VALUES (3, 3);
INSERT INTO `mydb`.`Client_has_Car_Booking` (`Client_Client_id`, `Car_Booking_Car_Booking_id`) VALUES (4, 4);
INSERT INTO `mydb`.`Client_has_Car_Booking` (`Client_Client_id`, `Car_Booking_Car_Booking_id`) VALUES (5, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Car_has_Car_Booking`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Car_has_Car_Booking` (`Car_Car_id`, `Car_Booking_Car_Booking_id`) VALUES (1, 1);
INSERT INTO `mydb`.`Car_has_Car_Booking` (`Car_Car_id`, `Car_Booking_Car_Booking_id`) VALUES (2, 2);
INSERT INTO `mydb`.`Car_has_Car_Booking` (`Car_Car_id`, `Car_Booking_Car_Booking_id`) VALUES (3, 3);
INSERT INTO `mydb`.`Car_has_Car_Booking` (`Car_Car_id`, `Car_Booking_Car_Booking_id`) VALUES (4, 4);
INSERT INTO `mydb`.`Car_has_Car_Booking` (`Car_Car_id`, `Car_Booking_Car_Booking_id`) VALUES (5, 5);

COMMIT;

