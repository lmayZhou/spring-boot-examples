/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 127.0.0.1:3306
 Source Schema         : lms_drools

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 27/10/2021 14:19:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for rule_drl
-- ----------------------------
DROP TABLE IF EXISTS `rule_drl`;
CREATE TABLE `rule_drl` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `rule_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则名称',
  `rule_drl` text COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则DRL',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of rule_drl
-- ----------------------------
BEGIN;
INSERT INTO `rule_drl` VALUES (1, '测试规则', 'package rules.order;\ndialect  \"mvel\"\nimport com.lmaye.cloud.example.drools.entity.Order\n\n// 规则1: 总价小于100没有优惠\nrule \"order_discount_1\"\n    when $order:Order(originalPrice < 100)\n    then\n        $order.setRealPrice($order.getOriginalPrice());\n        System.out.println(\"规则1: 总价小于100没有优惠!\");\nend\n\n// 规则2: 总价在100~200元, 优惠20元\nrule \"order_discount_2\"\n    when $order:Order(100 <= originalPrice && originalPrice < 200)\n    then\n        $order.setRealPrice($order.getOriginalPrice() - 20);\n        System.out.println(\"规则2: 总价在100~200元, 优惠20元!\");\nend', '2021-10-27 13:38:35', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
