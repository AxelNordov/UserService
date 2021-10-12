CREATE TABLE `user`
(
    `uuid`       VARCHAR(255) NOT NULL,
    `email`      VARCHAR(255) NULL,
    `first_name` VARCHAR(255) NULL,
    `last_name`  VARCHAR(255) NULL,
    PRIMARY KEY (`uuid`)
);

CREATE TABLE `usergroup`
(
    `uuid` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NULL,
    PRIMARY KEY (`uuid`)
);

CREATE TABLE `user_user_groups`
(
    `user_uuid`        VARCHAR(255) NOT NULL,
    `user_groups_uuid` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`user_uuid`, `user_groups_uuid`),
    CONSTRAINT `FK_User_user_groups_Users`
        FOREIGN KEY (`user_uuid`)
            REFERENCES `user` (`uuid`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `FK_User_user_groups_User_groups`
        FOREIGN KEY (`user_groups_uuid`)
            REFERENCES `usergroup` (`uuid`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

