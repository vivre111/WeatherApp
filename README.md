# Weather MVP - Docker 教学项目

## 技术栈
- 前端：Vue 3 + Vite
- 后端：Spring Boot 3 + Java 21
- 数据库：MySQL 8（空密码，自动初始化）

## 快速启动

```bash
# 1. 克隆项目
git clone https://jihulab.com/austinxia-group/weatherapp.git

# 2. 进入项目目录
cd weatherapp

# 3. 启动所有服务
# 第一次启动（会拉镜像/构建）：docker build 一次即可
docker compose up --build

# 之后仅启动（代码没改时就够了）：docker compose up
# docker compose up

# 4. 等待启动完成（看到 "Started WeatherApplication" 和 "VITE ready"）
```

## 访问地址
- 前端页面：http://localhost:5173
- 后端 API：http://localhost:8080/api/weather/health
- MySQL：localhost:3306（root/空密码）

## 使用说明
1. 在输入框输入城市名（如 Beijing, London, Tokyo）
2. 点击"查询"，显示实时天气（调用 wttr.in 免费 API）
3. 查询记录自动保存到 MySQL，下方表格显示历史记录
