CREATE TABLE `floor` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `organization_id` INT(11) NOT NULL,
  `status` BIT(1) NOT NULL,
  `created_dttm` TIMESTAMP NULL DEFAULT NULL,
  `last_updated_dttm` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
