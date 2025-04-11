package com.example.demo.domain.list.service;

import com.example.demo.common.redis.RedisCommon;
import com.example.demo.domain.list.model.ListModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisList {
    final private RedisCommon redis;

    public void AddToListLeft(String key, String name) {
        ListModel model = new ListModel(name);
        redis.addToListLeft(key, model);
    }

    public void AddToListRight(String key, String name) {
        ListModel model = new ListModel(name);
        redis.addToListRight(key, model);
    }

    public List<ListModel> GetAllData(String key) {
        return redis.getAllList(key, ListModel.class);
    }
}
