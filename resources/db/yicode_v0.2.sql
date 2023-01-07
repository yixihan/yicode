/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.238.128&mysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50740
 Source Host           : 192.168.238.128:1617
 Source Schema         : yicode

 Target Server Type    : MySQL
 Target Server Version : 50740
 File Encoding         : 65001

 Date: 07/01/2023 19:42:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `comment_reply`;
CREATE TABLE `comment_reply`
(
    `id`          bigint(20) UNSIGNED                      NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `reply_id`    bigint(20) UNSIGNED                      NOT NULL DEFAULT 0 COMMENT '子评论 id',
    `root_id`     bigint(20) UNSIGNED                      NOT NULL DEFAULT 0 COMMENT '父评论 id',
    `question_id` bigint(20) UNSIGNED                      NOT NULL DEFAULT 0 COMMENT '问题 id',
    `user_id`     bigint(20) UNSIGNED                      NOT NULL DEFAULT 0 COMMENT '评论者 id',
    `content`     text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '评论内容',
    `like_count`  int(10) UNSIGNED                         NOT NULL DEFAULT 0 COMMENT '点赞数',
    `create_time` datetime                                 NOT NULL COMMENT '创建时间',
    `update_time` datetime                                 NOT NULL COMMENT '修改时间',
    `version`     int(10) UNSIGNED                         NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`    int(11)                                  NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `reply_id` (`reply_id`) USING BTREE,
    INDEX `root_id` (`root_id`) USING BTREE,
    INDEX `common_idx` (`question_id`, `user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '子评论表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment_reply
-- ----------------------------

-- ----------------------------
-- Table structure for comment_root
-- ----------------------------
DROP TABLE IF EXISTS `comment_root`;
CREATE TABLE `comment_root`
(
    `id`          bigint(20) UNSIGNED                         NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `root_id`     bigint(20) UNSIGNED                         NOT NULL DEFAULT 0 COMMENT '父评论 id',
    `answer_id`   bigint(20) UNSIGNED                         NOT NULL DEFAULT 0 COMMENT '评论内容 id',
    `answer_type` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '评论内容类型(QUESTION:问题, NOTE:题解)',
    `user_id`     bigint(20) UNSIGNED                         NOT NULL DEFAULT 0 COMMENT '评论者 id',
    `content`     text CHARACTER SET utf8 COLLATE utf8_bin    NOT NULL COMMENT '评论内容',
    `like_count`  int(10) UNSIGNED                            NOT NULL DEFAULT 0 COMMENT '点赞数',
    `reply_count` int(10) UNSIGNED                            NOT NULL DEFAULT 0 COMMENT '回复数',
    `create_time` datetime                                    NOT NULL COMMENT '创建时间',
    `update_time` datetime                                    NOT NULL COMMENT '修改时间',
    `version`     int(10) UNSIGNED                            NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`    int(11)                                     NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `root_id` (`root_id`) USING BTREE,
    INDEX `answer_id` (`answer_id`) USING BTREE,
    INDEX `user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '父评论表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment_root
-- ----------------------------

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`
(
    `id`          bigint(20) UNSIGNED                             NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `label_id`    bigint(20) UNSIGNED                             NOT NULL DEFAULT 0 COMMENT '标签 id',
    `label_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '标签名',
    `create_time` datetime                                        NOT NULL COMMENT '创建时间',
    `update_time` datetime                                        NOT NULL COMMENT '修改时间',
    `version`     int(10) UNSIGNED                                NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`    int(11)                                         NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `label_id` (`label_id`) USING BTREE,
    INDEX `label_name` (`label_name`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '标签表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of label
-- ----------------------------

-- ----------------------------
-- Table structure for label_note
-- ----------------------------
DROP TABLE IF EXISTS `label_note`;
CREATE TABLE `label_note`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `note_id`     bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '题解 id',
    `label_id`    bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '标签 id',
    `create_time` datetime            NOT NULL COMMENT '创建时间',
    `update_time` datetime            NOT NULL COMMENT '修改时间',
    `version`     int(10) UNSIGNED    NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`    int(11)             NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `common_idx` (`note_id`, `label_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '题解标签表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of label_note
-- ----------------------------

-- ----------------------------
-- Table structure for label_question
-- ----------------------------
DROP TABLE IF EXISTS `label_question`;
CREATE TABLE `label_question`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `question_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '问题 id',
    `label_id`    bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '标签 id',
    `create_time` datetime            NOT NULL COMMENT '创建时间',
    `update_time` datetime            NOT NULL COMMENT '修改时间',
    `version`     int(10) UNSIGNED    NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`    int(11)             NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `common_idx` (`question_id`, `label_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '问题标签表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of label_question
-- ----------------------------

-- ----------------------------
-- Table structure for label_user
-- ----------------------------
DROP TABLE IF EXISTS `label_user`;
CREATE TABLE `label_user`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `label_id`    bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '标签 id',
    `user_id`     bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户 id',
    `create_time` datetime            NOT NULL COMMENT '创建时间',
    `update_time` datetime            NOT NULL COMMENT '修改时间',
    `version`     int(10) UNSIGNED    NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`    int(11)             NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `common_idx` (`label_id`, `user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '用户标签表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of label_user
-- ----------------------------

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note`
(
    `id`               bigint(20) UNSIGNED                             NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `question_id`      bigint(20) UNSIGNED                             NOT NULL DEFAULT 0 COMMENT '问题 id',
    `note_id`          bigint(20) UNSIGNED                             NOT NULL DEFAULT 0 COMMENT '题解 id',
    `note_name`        varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '题解标题',
    `note_content`     text CHARACTER SET utf8 COLLATE utf8_bin        NULL COMMENT '题解内容',
    `user_id`          bigint(20) UNSIGNED                             NOT NULL DEFAULT 0 COMMENT '用户 id',
    `like_count`       int(10) UNSIGNED                                NOT NULL DEFAULT 0 COMMENT '点赞数量',
    `collection_count` int(10) UNSIGNED                                NOT NULL DEFAULT 0 COMMENT '收藏数量',
    `read_count`       int(10) UNSIGNED                                NOT NULL DEFAULT 0 COMMENT '阅读数量',
    `create_time`      datetime                                        NOT NULL COMMENT '创建时间',
    `update_time`      datetime                                        NOT NULL COMMENT '修改时间',
    `version`          int(10) UNSIGNED                                NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`         int(11)                                         NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `note_id` (`note_id`) USING BTREE,
    INDEX `common_idx` (`question_id`, `user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '问题题解表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of note
-- ----------------------------

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`
(
    `id`                  bigint(20) UNSIGNED                             NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `question_id`         bigint(20) UNSIGNED                             NOT NULL DEFAULT 0 COMMENT '问题 id',
    `question_name`       varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '问题题目',
    `question_desc`       text CHARACTER SET utf8 COLLATE utf8_bin        NULL COMMENT '问题描述',
    `question_difficulty` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '问题难度',
    `like_count`          int(10) UNSIGNED                                NOT NULL DEFAULT 0 COMMENT '问题点赞数',
    `commit_count`        int(10) UNSIGNED                                NOT NULL DEFAULT 0 COMMENT '问题提交数',
    `success_count`       int(10) UNSIGNED                                NOT NULL DEFAULT 0 COMMENT '问题通过数',
    `create_time`         datetime                                        NOT NULL COMMENT '创建时间',
    `update_time`         datetime                                        NOT NULL COMMENT '修改时间',
    `version`             int(10) UNSIGNED                                NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`            int(11)                                         NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `question_id` (`question_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '问题表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question
-- ----------------------------

-- ----------------------------
-- Table structure for question_answer
-- ----------------------------
DROP TABLE IF EXISTS `question_answer`;
CREATE TABLE `question_answer`
(
    `id`                    bigint(20) UNSIGNED                              NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `question_id`           bigint(20) UNSIGNED                              NOT NULL DEFAULT 0 COMMENT '问题 id',
    `user_id`               bigint(20) UNSIGNED                              NOT NULL DEFAULT 0 COMMENT '用户 id',
    `answer_type`           varchar(10) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '代码语言类型',
    `answer_code`           text CHARACTER SET utf8 COLLATE utf8_bin         NULL COMMENT '代码',
    `answer_flag`           varchar(10) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '代码运行结果(acm模式运行结果)',
    `answer_time_consume`   int(10) UNSIGNED                                 NOT NULL DEFAULT 0 COMMENT '时间消耗',
    `answer_memory_consume` int(10) UNSIGNED                                 NOT NULL DEFAULT 0 COMMENT '内存消耗',
    `answer_note`           varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    `create_time`           datetime                                         NOT NULL COMMENT '创建时间',
    `update_time`           datetime                                         NOT NULL COMMENT '修改时间',
    `version`               int(10) UNSIGNED                                 NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`              int(11)                                          NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `common_idx` (`question_id`, `user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '问题答案表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question_answer
-- ----------------------------

-- ----------------------------
-- Table structure for question_case
-- ----------------------------
DROP TABLE IF EXISTS `question_case`;
CREATE TABLE `question_case`
(
    `id`          bigint(20) UNSIGNED                      NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `question_id` bigint(20) UNSIGNED                      NOT NULL DEFAULT 0 COMMENT '问题 id',
    `case_params` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '测试用例参数',
    `case_answer` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '测试用例答案',
    `create_time` datetime                                 NOT NULL COMMENT '创建时间',
    `update_time` datetime                                 NOT NULL COMMENT '修改时间',
    `version`     int(10) UNSIGNED                         NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`    int(11)                                  NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `question_id` (`question_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '问题测试用例表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question_case
-- ----------------------------

-- ----------------------------
-- Table structure for question_daily
-- ----------------------------
DROP TABLE IF EXISTS `question_daily`;
CREATE TABLE `question_daily`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `question_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '问题 id',
    `create_time` datetime            NOT NULL COMMENT '创建时间',
    `update_time` datetime            NOT NULL COMMENT '修改时间',
    `version`     int(10) UNSIGNED    NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`    int(11)             NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `question_id` (`question_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '每日一题表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question_daily
-- ----------------------------

-- ----------------------------
-- Table structure for question_daily_user
-- ----------------------------
DROP TABLE IF EXISTS `question_daily_user`;
CREATE TABLE `question_daily_user`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `question_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '问题 id',
    `user_id`     bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户 id',
    `create_time` datetime            NOT NULL COMMENT '创建时间',
    `update_time` datetime            NOT NULL COMMENT '修改时间',
    `version`     int(10) UNSIGNED    NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`    int(11)             NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `common_idx` (`question_id`, `user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '用户每日一题表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question_daily_user
-- ----------------------------

-- ----------------------------
-- Table structure for question_user_record
-- ----------------------------
DROP TABLE IF EXISTS `question_user_record`;
CREATE TABLE `question_user_record`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `user_id`     bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户 id',
    `question_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '题目 id',
    `create_time` datetime            NOT NULL COMMENT '创建时间',
    `update_time` datetime            NOT NULL COMMENT '修改时间',
    `version`     int(10) UNSIGNED    NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`    int(11)             NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `user_id` (`user_id`) USING BTREE,
    INDEX `question_id` (`question_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '用户题目通过记录表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question_user_record
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`          bigint(20) UNSIGNED                             NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `role_id`     bigint(20) UNSIGNED                             NOT NULL DEFAULT 0 COMMENT '角色 id',
    `role_name`   varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '角色名',
    `create_time` datetime                                        NOT NULL COMMENT '创建时间',
    `update_time` datetime                                        NOT NULL COMMENT '修改时间',
    `version`     int(10) UNSIGNED                                NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`    int(11)                                         NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `role_id` (`role_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000003
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '角色表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`
VALUES (10000000, 1, 'SUPER_ADMIN', '2022-10-21 13:46:35', '2022-10-21 13:46:35', 1, 0);
INSERT INTO `role`
VALUES (10000001, 2, 'ADMIN', '2022-10-21 13:46:35', '2022-10-21 13:46:35', 1, 0);
INSERT INTO `role`
VALUES (10000002, 3, 'USER', '2022-10-21 13:46:35', '2022-10-21 13:46:35', 1, 0);

-- ----------------------------
-- Table structure for template_email
-- ----------------------------
DROP TABLE IF EXISTS `template_email`;
CREATE TABLE `template_email`
(
    `id`               bigint(20) UNSIGNED                             NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `template_name`    varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '模板名字',
    `template_content` text CHARACTER SET utf8 COLLATE utf8_bin        NULL COMMENT '模板内容',
    `create_time`      datetime                                        NOT NULL COMMENT '创建时间',
    `update_time`      datetime                                        NOT NULL COMMENT '修改时间',
    `version`          int(10) UNSIGNED                                NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`         int(11)                                         NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000004
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '邮件模板表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of template_email
-- ----------------------------
INSERT INTO `template_email`
VALUES (10000000, '登录模板',
        '您正在<span style=\"font-weight:bold\"> 登录</span><br><span style=\"font-size: 14px;\">验证码为：<span style=\"font-size: 16px; font-weight:bold\"> %s </span>，请于<span style=\"font-size: 16px;font-weight:bold\"> %s </span>分钟内填写。若非本人操作，请忽略本邮件！</span>',
        '2022-10-28 13:47:09', '2022-10-28 13:47:09', 1, 0);
INSERT INTO `template_email`
VALUES (10000001, '注册模板',
        '您正在<span style=\"font-weight:bold\"> 注册</span><br><span style=\"font-size: 14px;\">验证码为：<span style=\"font-size: 16px; font-weight:bold\"> %s </span>，请于<span style=\"font-size: 16px;font-weight:bold\"> %s </span>分钟内填写。若非本人操作，请忽略本邮件！</span>',
        '2022-10-28 13:47:09', '2022-10-28 13:47:09', 1, 0);
INSERT INTO `template_email`
VALUES (10000002, '重置密码模板',
        '您正在进行<span style=\"font-weight:bold\"> 密码重置操作</span><br><span style=\"font-size: 14px;\">验证码为：<span style=\"font-size: 16px; font-weight:bold\"> %s </span>，请于<span style=\"font-size: 16px;font-weight:bold\"> %s </span>分钟内填写。若非本人操作，请忽略本邮件！</span>',
        '2022-10-28 13:47:09', '2022-10-28 13:47:09', 1, 0);
INSERT INTO `template_email`
VALUES (10000003, '通用模板',
        '您正在进行<span style=\"font-weight:bold\"> 敏感操作</span><br><span style=\"font-size: 14px;\">验证码为：<span style=\"font-size: 16px; font-weight:bold\"> %s </span>，请于<span style=\"font-size: 16px;font-weight:bold\"> %s </span>分钟内填写。若非本人操作，请忽略本邮件！</span>',
        '2022-10-28 13:47:09', '2022-10-28 13:47:09', 1, 0);

-- ----------------------------
-- Table structure for template_sms
-- ----------------------------
DROP TABLE IF EXISTS `template_sms`;
CREATE TABLE `template_sms`
(
    `id`            bigint(20) UNSIGNED                             NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `template_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '模板名字',
    `template_id`   varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '模板 id',
    `create_time`   datetime                                        NOT NULL COMMENT '创建时间',
    `update_time`   datetime                                        NOT NULL COMMENT '修改时间',
    `version`       int(10) UNSIGNED                                NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`      int(11)                                         NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000004
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '短信模板表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of template_sms
-- ----------------------------
INSERT INTO `template_sms`
VALUES (10000000, '登录模板', '1588422', '2022-10-28 11:17:22', '2022-10-28 11:17:22', 1, 0);
INSERT INTO `template_sms`
VALUES (10000001, '注册模板', '1588425', '2022-10-28 11:17:22', '2022-10-28 11:17:22', 1, 0);
INSERT INTO `template_sms`
VALUES (10000002, '重置密码模板', '1588427', '2022-10-28 11:17:22', '2022-10-28 11:17:22', 1, 0);
INSERT INTO `template_sms`
VALUES (10000003, '通用模板', '1588482', '2022-10-28 11:17:22', '2022-10-28 11:17:22', 1, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`            bigint(20) UNSIGNED                              NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `user_id`       bigint(20) UNSIGNED                              NOT NULL DEFAULT 0 COMMENT '用户 id',
    `user_name`     varchar(20) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '用户名',
    `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户密码',
    `user_mobile`   char(11) CHARACTER SET utf8 COLLATE utf8_bin     NULL     DEFAULT NULL COMMENT '用户手机号',
    `user_email`    varchar(50) CHARACTER SET utf8 COLLATE utf8_bin  NULL     DEFAULT NULL COMMENT '用户邮箱',
    `register_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '注册方式',
    `create_time`   datetime                                         NOT NULL COMMENT '创建时间',
    `update_time`   datetime                                         NOT NULL COMMENT '修改时间',
    `version`       int(10) UNSIGNED                                 NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`      int(11)                                          NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `user_id` (`user_id`) USING BTREE,
    UNIQUE INDEX `user_mobile` (`user_mobile`) USING BTREE,
    UNIQUE INDEX `user_email` (`user_email`) USING BTREE,
    INDEX `idx_user_name` (`user_name`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000005
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '用户表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (10000004, 1, 'yixihan', '$2a$10$2MThiN5AWiGbq43aHLCF/.3eFd4utijouCZlgxMTDVIGykIQEU38a', '17623850426',
        '3113788997@qq.com', 'USERNAME_PASSWORD', '2022-11-10 14:49:38', '2022-11-10 14:49:38', 1, 0);

-- ----------------------------
-- Table structure for user_collection
-- ----------------------------
DROP TABLE IF EXISTS `user_collection`;
CREATE TABLE `user_collection`
(
    `id`            bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `favorite_id`   bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '收藏夹 id',
    `collection_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户收藏内容 id',
    `create_time`   datetime            NOT NULL COMMENT '创建时间',
    `update_time`   datetime            NOT NULL COMMENT '修改时间',
    `version`       int(10) UNSIGNED    NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`      int(11)             NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `common_idx` (`favorite_id`, `collection_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '用户收藏表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_collection
-- ----------------------------

-- ----------------------------
-- Table structure for user_favorite
-- ----------------------------
DROP TABLE IF EXISTS `user_favorite`;
CREATE TABLE `user_favorite`
(
    `id`             bigint(20) UNSIGNED                             NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `favorite_id`    bigint(20) UNSIGNED                             NOT NULL DEFAULT 0 COMMENT '收藏夹 id',
    `user_id`        bigint(20) UNSIGNED                             NOT NULL DEFAULT 0 COMMENT '用户 id',
    `favorite_type`  varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '收藏类型(QUESTION : 问题, NOTE : 题解)',
    `favorite_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '收藏夹名',
    `favorite_count` int(10) UNSIGNED                                NOT NULL DEFAULT 0 COMMENT '收藏数量',
    `create_time`    datetime                                        NOT NULL COMMENT '创建时间',
    `update_time`    datetime                                        NOT NULL COMMENT '修改时间',
    `version`        int(10) UNSIGNED                                NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`       int(11)                                         NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `favorite_id` (`favorite_id`) USING BTREE,
    INDEX `user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '用户收藏夹表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_favorite
-- ----------------------------
INSERT INTO `user_favorite`
VALUES (1, 1, 1, 'QUESTION', '默认收藏夹', 0, '2022-11-10 14:49:38', '2022-11-10 14:49:38', 1, 0);

-- ----------------------------
-- Table structure for user_follow
-- ----------------------------
DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow`
(
    `id`             bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `user_id`        bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户 id',
    `follow_user_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '关注人用户 id',
    `create_time`    datetime            NOT NULL COMMENT '创建时间',
    `update_time`    datetime            NOT NULL COMMENT '修改时间',
    `version`        int(10) UNSIGNED    NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`       int(11)             NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `user_id` (`user_id`) USING BTREE,
    INDEX `follow_user_id` (`follow_user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '用户关注表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_follow
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`
(
    `id`             bigint(20) UNSIGNED                              NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `user_id`        bigint(20) UNSIGNED                              NOT NULL DEFAULT 0 COMMENT '用户 id',
    `user_avatar`    varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户头像',
    `user_province`  char(6) CHARACTER SET utf8 COLLATE utf8_bin      NOT NULL DEFAULT '' COMMENT '用户省份',
    `user_city`      char(6) CHARACTER SET utf8 COLLATE utf8_bin      NOT NULL DEFAULT '' COMMENT '用户城市',
    `user_sex`       varchar(10) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT 'SECRECY' COMMENT '用户性别(SECRECY:保密, MAN:男, WOMAN:女)',
    `user_sign`      varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户签名',
    `user_school`    varchar(50) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '用户学校',
    `user_birth`     datetime                                         NULL     DEFAULT NULL COMMENT '用户生日',
    `user_real_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '用户真实姓名',
    `create_time`    datetime                                         NOT NULL COMMENT '创建时间',
    `update_time`    datetime                                         NOT NULL COMMENT '修改时间',
    `version`        int(10) UNSIGNED                                 NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`       int(11)                                          NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '用户信息表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info`
VALUES (1, 1, '', '', '', 'SECRECY', '', '', NULL, '', '2022-11-10 14:49:38', '2022-11-10 14:49:38', 1, 0);

-- ----------------------------
-- Table structure for user_language
-- ----------------------------
DROP TABLE IF EXISTS `user_language`;
CREATE TABLE `user_language`
(
    `id`          bigint(20) UNSIGNED                             NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `user_id`     bigint(20) UNSIGNED                             NOT NULL DEFAULT 0 COMMENT '用户 id',
    `language`    varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '语言名',
    `deal_count`  int(10) UNSIGNED                                NOT NULL DEFAULT 0 COMMENT '解决问题数量',
    `create_time` datetime                                        NOT NULL COMMENT '创建时间',
    `update_time` datetime                                        NOT NULL COMMENT '修改时间',
    `version`     int(10) UNSIGNED                                NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`    int(11)                                         NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '用户语言表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_language
-- ----------------------------

-- ----------------------------
-- Table structure for user_msg
-- ----------------------------
DROP TABLE IF EXISTS `user_msg`;
CREATE TABLE `user_msg`
(
    `id`             bigint(20) UNSIGNED                              NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `send_user_id`   bigint(20) UNSIGNED                              NOT NULL DEFAULT 0 COMMENT '发送者 id',
    `send_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL DEFAULT '' COMMENT '发送者用户名',
    `receive_use_id` bigint(20) UNSIGNED                              NOT NULL DEFAULT 0 COMMENT '接收者 id',
    `msg`            varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '消息内容',
    `create_time`    datetime                                         NOT NULL COMMENT '创建时间',
    `update_time`    datetime                                         NOT NULL COMMENT '修改时间',
    `version`        int(10) UNSIGNED                                 NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`       int(11)                                          NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '用户消息表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_msg
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `user_id`     bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户 id',
    `role_id`     bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色 id',
    `create_time` datetime            NOT NULL COMMENT '创建时间',
    `update_time` datetime            NOT NULL COMMENT '修改时间',
    `version`     int(10) UNSIGNED    NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`    int(11)             NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000010
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '用户角色对应表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role`
VALUES (10000007, 1, 1, '2022-11-10 15:17:19', '2022-11-10 15:17:19', 1, 0);
INSERT INTO `user_role`
VALUES (10000008, 1, 2, '2022-11-10 15:17:19', '2022-11-10 15:17:19', 1, 0);
INSERT INTO `user_role`
VALUES (10000009, 1, 3, '2022-11-10 15:17:19', '2022-11-10 15:17:19', 1, 0);

-- ----------------------------
-- Table structure for user_website
-- ----------------------------
DROP TABLE IF EXISTS `user_website`;
CREATE TABLE `user_website`
(
    `id`           bigint(20) UNSIGNED                              NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `user_id`      bigint(20) UNSIGNED                              NOT NULL DEFAULT 0 COMMENT '用户 id',
    `user_website` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户个人网站',
    `create_time`  datetime                                         NOT NULL COMMENT '创建时间',
    `update_time`  datetime                                         NOT NULL COMMENT '修改时间',
    `version`      int(10) UNSIGNED                                 NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `del_flag`     int(11)                                          NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000000
  CHARACTER SET = utf8
  COLLATE = utf8_bin COMMENT = '用户个人网站表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_website
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
