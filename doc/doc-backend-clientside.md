# 后端文档-客户端方功能

## 功能需求

+ 满足客户端的功能需求
  + 注册登录登出
  + 用户信息查看
  + 消耗资源抽卡
  + 用持有卡牌编队
  + 选择关卡战斗
  + 获得战斗成果
+ 接受客户端的崩溃日志
+ 对玩家的行为收集数据进行统计（登录、登出、进入关卡等）

## 非功能需求

+ 反作弊（特指更改资源，更改他人资源等）
+ 客户端启动耗时小（<2sec）（除去网络延迟）

## 项目架构

![Structure](/doc_client_img1.png)

## 技术栈

+ Spring MVC + Java -> 框架
+ IDEA -> IDE
+ MySQL + MongoDB -> 数据库

## 测试

请参考另一个后端文档

## 访问权限

用户权限下，支持以下功能访问

+ Activity：公告查询
+ Mail：看自己的邮箱
+ User：注册登录
+ Card：卡牌查询
+ OwnCard：持有卡牌查询，养成（技能点数分配）
+ Chapter：进入关卡，关卡游玩结果提交
+ Mechanism：抽卡
+ Item：道具查询
+ OwnItem：持有道具查询