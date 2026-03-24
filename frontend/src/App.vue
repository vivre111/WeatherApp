<template>
  <div class="container">
    <h1>🌤️ 天气查询 MVP</h1>

    <div class="search-box">
      <input 
        v-model="city" 
        @keyup.enter="queryWeather"
        placeholder="输入城市名（如：Beijing, Shanghai, London）"
      />
      <button @click="queryWeather" :disabled="loading">
        {{ loading ? '查询中...' : '查询' }}
      </button>
    </div>

    <div v-if="currentWeather" class="result-box">
      <h2>{{ currentWeather.city }}</h2>
      <p class="temp">{{ currentWeather.temperature }}</p>
      <p class="desc">{{ currentWeather.weatherDesc }}</p>
      <p class="time">查询时间：{{ formatTime(currentWeather.queryTime) }}</p>
    </div>

    <div class="history">
      <h3>📜 最近查询记录</h3>
      <button @click="loadHistory" class="refresh-btn">刷新记录</button>
      <table v-if="history.length > 0">
        <thead>
          <tr>
            <th>城市</th>
            <th>温度</th>
            <th>天气</th>
            <th>查询时间</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="record in history" :key="record.id">
            <td>{{ record.city }}</td>
            <td>{{ record.temperature }}</td>
            <td>{{ record.weatherDesc }}</td>
            <td>{{ formatTime(record.queryTime) }}</td>
          </tr>
        </tbody>
      </table>
      <p v-else>暂无记录</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const city = ref('')
const currentWeather = ref(null)
const history = ref([])
const loading = ref(false)

const API_BASE = import.meta.env.DEV ? '/api' : 'http://localhost:8080/api'

const queryWeather = async () => {
  if (!city.value.trim()) return

  loading.value = true
  try {
    const response = await axios.post(`${API_BASE}/weather/query`, {
      city: city.value.trim()
    })
    currentWeather.value = response.data
    loadHistory()
  } catch (error) {
    alert('查询失败：' + error.message)
  } finally {
    loading.value = false
  }
}

const loadHistory = async () => {
  try {
    const response = await axios.get(`${API_BASE}/weather/history`)
    history.value = response.data
  } catch (error) {
    console.error('加载历史记录失败:', error)
  }
}

const formatTime = (timeStr) => {
  if (!timeStr) return '-'
  return new Date(timeStr).toLocaleString('zh-CN')
}

onMounted(() => {
  loadHistory()
})
</script>

<style>
.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
}

h1 {
  text-align: center;
  color: #333;
}

.search-box {
  display: flex;
  gap: 10px;
  margin: 30px 0;
}

input {
  flex: 1;
  padding: 12px;
  font-size: 16px;
  border: 2px solid #ddd;
  border-radius: 8px;
}

button {
  padding: 12px 24px;
  background: #4CAF50;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-size: 16px;
}

button:hover:not(:disabled) {
  background: #45a049;
}

button:disabled {
  background: #ccc;
  cursor: not-allowed;
}

.result-box {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 30px;
  border-radius: 16px;
  text-align: center;
  margin-bottom: 30px;
}

.result-box h2 {
  margin: 0 0 10px 0;
  font-size: 24px;
}

.temp {
  font-size: 48px;
  font-weight: bold;
  margin: 10px 0;
}

.desc {
  font-size: 20px;
  opacity: 0.9;
}

.time {
  font-size: 14px;
  opacity: 0.8;
  margin-top: 15px;
}

.history {
  background: #f5f5f5;
  padding: 20px;
  border-radius: 12px;
}

.history h3 {
  margin-top: 0;
  display: inline-block;
  margin-right: 15px;
}

.refresh-btn {
  background: #2196F3;
  padding: 6px 12px;
  font-size: 14px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 15px;
  background: white;
  border-radius: 8px;
  overflow: hidden;
}

th, td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  background: #4CAF50;
  color: white;
}

tr:hover {
  background: #f9f9f9;
}
</style>
