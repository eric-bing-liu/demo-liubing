

# demo-redis
springboot2 + spring-boot-starter-data-redis jedis方式集成redis

通过SETNX方式实现redis锁



## 1 redis锁
### 文件位置
src/main/java/com/sinosoft/demoredis/config/RedisLock.java

### 测试redis锁：

http://localhost:9000/index1

http://localhost:9000/index2



## 2 常规redis操作

RedisConfig + RedisService