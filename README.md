# Weather MVP - Docker 教学项目

## 技术栈
- 前端：Vue 3 + Vite
- 后端：Spring Boot 3 + Java 21
- 数据库：MySQL 8（空密码，自动初始化）

## 快速启动

```bash
# 1. 进入项目目录
cd weather-mvp

# 2. 启动所有服务
docker-compose up --build

# 3. 等待启动完成（看到 "Started WeatherApplication" 和 "VITE ready"）
```

## 访问地址
- 前端页面：http://localhost:5173
- 后端 API：http://localhost:8080/api/weather/health
- MySQL：localhost:3306（root/空密码）

## 使用说明
1. 在输入框输入城市名（如 Beijing, London, Tokyo）
2. 点击"查询"，显示实时天气（调用 wttr.in 免费 API）
3. 查询记录自动保存到 MySQL，下方表格显示历史记录

## 教学要点
- `docker-compose up` 一键启动多容器
- MySQL 通过 `init-scripts/init.sql` 自动建表
- 前端热重载：修改 Vue 代码自动刷新
- 数据持久化：MySQL 数据保存在 volume 中
