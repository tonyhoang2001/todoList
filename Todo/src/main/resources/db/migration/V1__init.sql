
CREATE SCHEMA IF NOT EXISTS `todo_list` DEFAULT CHARACTER SET utf8mb4;
USE `todo_list` ;

CREATE TABLE IF NOT EXISTS `todo_list`.`user`
(
    `id`      varchar(255) NOT NULL ,
    `username` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `role`  ENUM('admin','user') NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS `todo_list`.`todo`
(
    `id`          varchar(255) NOT NULL ,
    `name`       varchar(255) NOT NULL,
    `description` varchar(255) DEFAULT NULL,
    `user_id`     varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT
    FOREIGN KEY (`user_id`) REFERENCES `todo_list`.`user`(`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

