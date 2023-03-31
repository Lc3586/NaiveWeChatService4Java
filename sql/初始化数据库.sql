/*
 Navicat Premium Data Transfer

 Source Server         : LCTR TCS MariaDB 10.6
 Source Server Type    : MariaDB
 Source Server Version : 100605
 Source Host           : database.lctr.top:3307
 Source Schema         : naive_wechat_service

 Target Server Type    : MariaDB
 Target Server Version : 100605
 File Encoding         : 65001

 Date: 29/03/2023 16:29:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wechat_config
-- ----------------------------
DROP TABLE IF EXISTS `wechat_config`;
CREATE TABLE `wechat_config`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `app_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信公众号标识',
  `secret` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密钥（加密存储）',
  `token` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '令牌（加密存储）',
  `template_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板标识',
  `access_token` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问令牌（加密存储）',
  `aes_key` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'AES加密密钥（加密存储）',
  `expires_time` bigint(20) NULL DEFAULT NULL COMMENT '过期时间',
  `oauth2redirect_uri` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份验证跳转地址',
  `http_proxy_host` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'http代理主机',
  `http_proxy_port` int(11) NULL DEFAULT NULL COMMENT 'http代理端口',
  `http_proxy_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'http代理用户名',
  `http_proxy_password` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'http代理密码（加密存储）',
  `retry_sleep_millis` int(11) NULL DEFAULT NULL COMMENT 'http请求重试间隔毫秒数',
  `max_retry_times` int(11) NULL DEFAULT NULL COMMENT 'http请求最大重试次数',
  `jsapi_ticket` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'H5支付接口票据（加密存储）',
  `jsapi_ticket_expires_time` bigint(20) NULL DEFAULT NULL COMMENT 'H5支付接口票据过期时间',
  `sdk_ticket` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SDK票据（加密存储）',
  `sdk_ticket_expires_time` bigint(20) NULL DEFAULT NULL COMMENT 'SDK票据过期时间',
  `card_api_ticket` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '刷卡支付接口票据（加密存储）',
  `card_api_ticket_expires_time` bigint(20) NULL DEFAULT NULL COMMENT '刷卡支付接口票据过期时间',
  `tmp_dir_file` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '临时文件存储目录',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wechat_user_info
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user_info`;
CREATE TABLE `wechat_user_info`  (
  `id` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '主键',
  `app_id` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '微信公众号标识',
  `open_id` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '用户公众号唯一标识',
  `union_id` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '用户公众平台唯一标识',
  `group_id` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '分组标识',
  `nickname` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `headimg_url` varchar(2048) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '性别',
  `country` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '国家',
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '城市',
  `language` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '语言',
  `enable` bit(1) NOT NULL COMMENT '启用',
  `remark` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `C_WCUI_idx_`(`enable`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '微信用户信息' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
