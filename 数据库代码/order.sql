-- 临时关闭外键检查
SET FOREIGN_KEY_CHECKS = 0;

-- 下面粘贴你全部的t_order插入语句
INSERT INTO `t_order` VALUES ('1', '1', '2021-10-08', '微信预约', '未到诊', '1');
INSERT INTO `t_order` VALUES ('2', '2', '2021-10-11', '客户端预约', '未到诊', '1');
INSERT INTO `t_order` VALUES ('3', '3', '2021-10-11', '微信预约', '未到诊', '1');
INSERT INTO `t_order` VALUES ('4', '4', '2021-10-11', '客户端预约', '未到诊', '1');
INSERT INTO `t_order` VALUES ('5', '5', '2021-10-13', '微信预约', '未到诊', '1');
INSERT INTO `t_order` VALUES ('6', '6', '2021-10-15', '微信预约', '未到诊', '2');
INSERT INTO `t_order` VALUES ('7', '7', '2021-10-09', '微信预约', '未到诊', '2');
INSERT INTO `t_order` VALUES ('8', '8', '2021-10-20', '微信预约', '未到诊', '2');
INSERT INTO `t_order` VALUES ('9', '9', '2021-10-18', '微信预约', '未到诊', '2');
INSERT INTO `t_order` VALUES ('10', '10', '2021-10-08', '微信预约', '未到诊', '2');
INSERT INTO `t_order` VALUES ('11', '1', '2021-11-08', '微信预约', '未到诊', '3');
INSERT INTO `t_order` VALUES ('12', '2', '2021-10-17', '客户端预约', '已到诊', '3');
INSERT INTO `t_order` VALUES ('13', '3', '2021-10-16', '微信预约', '未到诊', '3');
INSERT INTO `t_order` VALUES ('14', '4', '2021-10-18', '客户端预约', '已到诊', '4');
INSERT INTO `t_order` VALUES ('15', '5', '2021-10-19', '微信预约', '未到诊', '4');
INSERT INTO `t_order` VALUES ('16', '6', '2021-10-25', '微信预约', '未到诊', '4');
INSERT INTO `t_order` VALUES ('17', '7', '2021-10-19', '微信预约', '未到诊', '6');
INSERT INTO `t_order` VALUES ('18', '8', '2021-10-22', '微信预约', '未到诊', '6');
INSERT INTO `t_order` VALUES ('19', '9', '2021-10-28', '微信预约', '未到诊', '6');
INSERT INTO `t_order` VALUES ('20', '10', '2021-10-28', '微信预约', '未到诊', '6');
INSERT INTO `t_order` VALUES ('21', '2', '2021-10-28', '微信预约', '未到诊', '6');

-- 执行完成恢复外键约束
SET FOREIGN_KEY_CHECKS = 1;