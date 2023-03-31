CREATE TABLE `email_template` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `type` tinyint NOT NULL DEFAULT '1' COMMENT '邮件类型（1：注册成功邮件）',
                                  `subject` varchar(1000) DEFAULT NULL COMMENT '主题',
                                  `content` varchar(2000) DEFAULT NULL COMMENT '邮件模版内容,属性替换可使用{propertyName}',
                                  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `email_template_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='邮件模版'

CREATE TABLE `email_details` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `template_id` bigint DEFAULT NULL COMMENT '邮件模版id',
                                 `user_id` bigint DEFAULT NULL,
                                 `content` varchar(2000) DEFAULT NULL COMMENT '已发邮件内容',
                                 `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `email_details_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='已发邮件记录'


CREATE TABLE `user_info` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `user_name` varchar(100) DEFAULT NULL,
                             `age` int DEFAULT NULL,
                             `email` varchar(300) DEFAULT NULL,
                             `send_flag` tinyint DEFAULT NULL COMMENT '发送邮件标记（1：已发送，0：未发送）',
                             `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `valid_flag` tinyint NOT NULL DEFAULT '1',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `user_info_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表'

-- 初始化邮件模版
insert into email_template(id,type,subject,content)values (1000,1,'注册成功','尊敬的用户：{userName}\n 您已注册成功，感谢使用我们的系统.');