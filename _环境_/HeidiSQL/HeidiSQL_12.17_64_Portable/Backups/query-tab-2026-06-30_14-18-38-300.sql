-- 1. 关闭外键约束
SET FOREIGN_KEY_CHECKS = 0;

-- 2. 清空两张表
TRUNCATE TABLE t_setmeal_checkgroup;
TRUNCATE TABLE t_setmeal;

-- 3. 把你所有插入套餐、关联组的SQL粘贴到这里
INSERT INTO `t_setmeal` VALUES ('2', '粉红珍爱(女)升级TM12项筛查体检套餐', '0002', 'FHZA', '2', '18-60', '1200', '本套餐针对宫颈(TCT检查、HPV乳头瘤病毒筛查）、乳腺（彩超，癌抗125），甲状腺（彩超，甲功验血）以及胸片，血常规肝功等有全面检查，非常适合女性全面疾病筛查使用。', null, '3bd90d2c-4e82-42a1-a401-882c88b06a1a2.jpg');
INSERT INTO `t_setmeal` VALUES ('3', '阳光爸妈升级肿瘤12项筛查（男女单人）体检套餐', '0003', 'YGBM', '0', '55-100', '1400', '本套餐主要针对常见肿瘤筛查，肝肾、颈动脉、脑血栓、颅内血流筛查，以及风湿、颈椎、骨密度检查。', null, 'ee7dcf84-8a3a-4ab9-b981-9c5d272fd58d3.jpg');
INSERT INTO `t_setmeal` VALUES ('4', '珍爱高端升级肿瘤12项筛查（男女单人）', '0004', 'ZAGD', '0', '14-20', '2400', '本套餐是一款针对生化五项检查，心，肝，胆，胃，甲状腺，颈椎，肺功能，脑部检查（经颅多普勒）以及癌症筛查，适合大众人群体检的套餐。', null, 'e373b2eb-0e50-4e95-a09b-03f2c1ee1d351.jpg');
INSERT INTO `t_setmeal` VALUES ('5', '高级入职无忧体检套餐（男女通用）', '0005', 'GJRZTJ', '0', '23-56', '350', '高级入职体检套餐', '无', '03a36073-a140-4942-9b9b-712cecb144901.jpg');
INSERT INTO `t_setmeal` VALUES ('6', '肝肾检查', '0006', 'GSJC', '0', '30-60', '1500', '肝肾检查，本套餐针对肝脏和肾脏的常规检查。', null, 'ac3b5a4d-33a5-4f37-bd49-99e06ce17d202.jpg');

INSERT INTO `t_setmeal_checkgroup` VALUES ('2', '1');
INSERT INTO `t_setmeal_checkgroup` VALUES ('3', '1');
INSERT INTO `t_setmeal_checkgroup` VALUES ('4', '1');
INSERT INTO `t_setmeal_checkgroup` VALUES ('5', '1');
INSERT INTO `t_setmeal_checkgroup` VALUES ('2', '2');
INSERT INTO `t_setmeal_checkgroup` VALUES ('3', '2');
INSERT INTO `t_setmeal_checkgroup` VALUES ('4', '2');
INSERT INTO `t_setmeal_checkgroup` VALUES ('5', '2');
INSERT INTO `t_setmeal_checkgroup` VALUES ('2', '3');
INSERT INTO `t_setmeal_checkgroup` VALUES ('3', '3');
INSERT INTO `t_setmeal_checkgroup` VALUES ('4', '3');
INSERT INTO `t_setmeal_checkgroup` VALUES ('6', '3');
INSERT INTO `t_setmeal_checkgroup` VALUES ('5', '3');
INSERT INTO `t_setmeal_checkgroup` VALUES ('2', '4');
INSERT INTO `t_setmeal_checkgroup` VALUES ('3', '4');
INSERT INTO `t_setmeal_checkgroup` VALUES ('4', '4');
INSERT INTO `t_setmeal_checkgroup` VALUES ('6', '4');
INSERT INTO `t_setmeal_checkgroup` VALUES ('5', '4');
INSERT INTO `t_setmeal_checkgroup` VALUES ('2', '5');
INSERT INTO `t_setmeal_checkgroup` VALUES ('3', '5');
INSERT INTO `t_setmeal_checkgroup` VALUES ('4', '5');
INSERT INTO `t_setmeal_checkgroup` VALUES ('5', '5');
INSERT INTO `t_setmeal_checkgroup` VALUES ('6', '5');
INSERT INTO `t_setmeal_checkgroup` VALUES ('2', '6');
INSERT INTO `t_setmeal_checkgroup` VALUES ('3', '6');
INSERT INTO `t_setmeal_checkgroup` VALUES ('4', '6');
INSERT INTO `t_setmeal_checkgroup` VALUES ('5', '6');
INSERT INTO `t_setmeal_checkgroup` VALUES ('6', '6');
INSERT INTO `t_setmeal_checkgroup` VALUES ('2', '7');
INSERT INTO `t_setmeal_checkgroup` VALUES ('3', '7');
INSERT INTO `t_setmeal_checkgroup` VALUES ('4', '7');
INSERT INTO `t_setmeal_checkgroup` VALUES ('3', '8');
INSERT INTO `t_setmeal_checkgroup` VALUES ('4', '8');
INSERT INTO `t_setmeal_checkgroup` VALUES ('3', '10');
INSERT INTO `t_setmeal_checkgroup` VALUES ('4', '10');
INSERT INTO `t_setmeal_checkgroup` VALUES ('3', '11');
INSERT INTO `t_setmeal_checkgroup` VALUES ('4', '11');

-- 4. 执行完插入后，恢复外键约束（必须加）
SET FOREIGN_KEY_CHECKS = 1;