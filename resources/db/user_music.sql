/*
 Navicat Premium Data Transfer

 Source Server         : music
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : music

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 29/11/2019 16:42:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_music
-- ----------------------------
DROP TABLE IF EXISTS `user_music`;
CREATE TABLE `user_music`  (
  `uid` bigint(20) NOT NULL,
  `music_id` bigint(20) NOT NULL,
  INDEX `FKb53nc2rlyaotl1socy90u0v8`(`music_id`) USING BTREE,
  INDEX `FKp4e4yxp4fi4p5g979lcrt1u0y`(`uid`) USING BTREE,
  CONSTRAINT `FKb53nc2rlyaotl1socy90u0v8` FOREIGN KEY (`music_id`) REFERENCES `music` (`music_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKp4e4yxp4fi4p5g979lcrt1u0y` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
