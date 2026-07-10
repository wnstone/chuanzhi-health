# 传智健康(chuanzhi-health)

黑马程序员「传智健康」教学项目(体检预约管理系统),Spring + SpringMVC + MyBatis + Dubbo + ZooKeeper + Redis + MySQL 架构。只需要跑 **chapter10**(最后一章,包含前面全部功能)。

## 先看这个

**代码本身没问题**——已用 JDK 8 + Maven 在本地把 chapter10 六个模块全部编译通过(`BUILD SUCCESS`)。之前报的一堆错,根源是环境没配对(JDK 版本 / 中间件没起 / 中文路径),不是代码坏了。

## 怎么跑起来

完整手把手步骤看 **[跑通指南.md](./跑通指南.md)**——从建数据库、起 ZooKeeper/Redis,到启动项目、访问页面,一步步都写清楚了,照着做就行。

## 目录说明

| 位置 | 内容 |
| --- | --- |
| `源代码/health/chapter10/health_parent/` | 项目本体(6 个 Maven 模块) |
| `数据库代码/` | 5 个 SQL 文件(建表 + 初始数据) |
| `_环境_/` | Redis / ZooKeeper / HeidiSQL(免安装,已解压好) |
| `跑通指南.md` | 手把手运行步骤 + 排错检查单 |
