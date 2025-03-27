package com.example.demo.common.redis;

import com.google.gson.Gson;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisCommon {
    private final RedisTemplate<String, String> template;
    private final Gson gson;

    @Value("${spring.data.redis.default-time}")
    private Duration defaultExpireTime;

    public <T> T getData(String key, Class<T> clazz) {
        String jsonValue = template.opsForValue().get(key);
        if (jsonValue == null) {
            return null;
        }

        return gson.fromJson(jsonValue, clazz);
    }

    public <T> void setData(String key, T value) {
        String jsonValue = gson.toJson(value);
        template.opsForValue().set(key, jsonValue);
        template.expire(key, defaultExpireTime);
    }

    public <T> void multiSetdata(Map<String, T> datas) {
        Map<String, String> jsonMap = new HashMap<>();

        for (Map.Entry<String, T> entry : datas.entrySet()) {
            jsonMap.put(entry.getKey(), gson.toJson(entry.getValue()));
        }

        template.opsForValue().multiSet(jsonMap);
    }

    public <T> void addToSortedSet(String key, T value, Float score) {
        String jsonValue = gson.toJson(value);
        template.opsForZSet().add(key, jsonValue, score);
    }

    public <T> Set<T> rangeByScore(String key, Float minScore, Float maxScore, Class<T> clazz) {
        Set<String> jsonValues = template.opsForZSet().rangeByScore(key, minScore, maxScore);
        Set<T> resultSet = new HashSet<T>();

        if (jsonValues != null) {
            for (String jsonValue : jsonValues) {
                T v = gson.fromJson(jsonValue, clazz);
                resultSet.add(v);
            }
        }

        return resultSet;
    }

    /**
     * reverseRange: 상위 N명 랭킹
     * range: 범위
     */
    public <T> Set<T> getTopNFromSortedSet(String key, int n, Class<T> clazz) {
        Set<String> jsonValues = template.opsForZSet().reverseRange(key, 0, n-1);
        Set<T> resultSet = new HashSet<T>();

        if (jsonValues != null) {
            for (String jsonValue : jsonValues) {
                T v = gson.fromJson(jsonValue, clazz);
                resultSet.add(v);
            }
        }

        return resultSet;
    }
}