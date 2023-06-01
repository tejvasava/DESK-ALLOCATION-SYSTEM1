CREATE TABLE `user_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(155) NOT NULL,
  `created_dttm` TIMESTAMP NULL DEFAULT NULL,
  `last_updated_dttm` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE `user` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_role_id` INT(11) NOT NULL,
	`username` VARCHAR(45) NOT NULL,
	`password` VARCHAR(500) NULL DEFAULT NULL,
	`name` VARCHAR(1024) NULL DEFAULT NULL,
	`email` VARCHAR(255) NULL DEFAULT NULL,
	`phone_no` VARCHAR(45) NULL DEFAULT NULL,
	`status` BIT(1) NULL DEFAULT NULL,
	`floor_id` INT(11) NULL DEFAULT NULL,
	`created_dttm` TIMESTAMP NULL DEFAULT NULL,
	`last_updated_dttm` TIMESTAMP NULL DEFAULT NULL,
	 PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=16
;

CREATE TABLE `audit_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(255) DEFAULT NULL,  
  `entity_name` varchar(255) DEFAULT NULL,
  `ref_table_id` int(11) DEFAULT NULL,
  `content` longtext DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `user` (`user_role_id`, `username`, `password`, `name`, `email`, `created_dttm`, `last_updated_dttm`) VALUES ('1', 'admin', '$2a$10$/n8LR3rrO/bwKhQjNdNfqu5UJKjRIXN7akXW2t.00.81kFVywEc8K', 'Admin', 'admin@test.com', '2023-01-07 10:47:34', '2023-01-07 10:47:34');

INSERT INTO `user_role` (`name`, `created_dttm`, `last_updated_dttm`) VALUES ('Admin', '2023-01-07 10:47:34', '2023-01-07 10:47:34');
INSERT INTO `user_role` (`name`, `created_dttm`, `last_updated_dttm`) VALUES ('HR', '2023-01-07 10:47:34', '2023-01-07 10:47:34');
INSERT INTO `user_role` (`name`, `created_dttm`, `last_updated_dttm`) VALUES ('PMO', '2023-01-07 10:47:34', '2023-01-07 10:47:34');
INSERT INTO `user_role` (`name`, `created_dttm`, `last_updated_dttm`) VALUES ('Manager', '2023-01-07 10:47:34', '2023-01-07 10:47:34');

