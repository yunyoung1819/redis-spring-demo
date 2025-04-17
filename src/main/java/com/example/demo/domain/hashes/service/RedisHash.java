package com.example.demo.domain.hashes.service;

import com.example.demo.common.redis.RedisCommon;
import com.example.demo.domain.hashes.model.request.HashModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisHash {
    final private RedisCommon redis;

    public void putInHash(String key, String field, String name) {
        HashModel model = new HashModel(name);
        redis.putInHash(key, field, model);
    }

    public HashModel GetFromHash(String key, String field) {
        return redis.getFromHash(key, field, HashModel.class);
    }
}
