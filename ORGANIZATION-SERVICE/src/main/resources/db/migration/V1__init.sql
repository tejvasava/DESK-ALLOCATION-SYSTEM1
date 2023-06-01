CREATE TABLE `organization` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `status` BIT(1) NULL DEFAULT NULL,
  `created_dttm` TIMESTAMP NULL DEFAULT NULL,
  `last_updated_dttm` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `organization` (`name`,`created_dttm`, `last_updated_dttm`) VALUES ('kcs','2023-01-07 10:47:34', '2023-01-07 10:47:34');