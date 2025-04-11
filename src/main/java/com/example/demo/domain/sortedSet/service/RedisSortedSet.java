package com.example.demo.domain.sortedSet.service;

import com.example.demo.common.redis.RedisCommon;
import com.example.demo.domain.sortedSet.model.SortedSet;
import com.example.demo.domain.sortedSet.model.request.SortedSetRequest;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSortedSet {
    private final RedisCommon redis;

    public void SetSortedSet(SortedSetRequest req) {
        SortedSet model = new SortedSet(req.Name(), req.Score());
        redis.addToSortedSet(req.baseRequest().Key(), model, req.Score());
    }

    public Set<SortedSet> GetSetDataByRange(String key, Float min, Float max) {
        return redis.rangeByScore(key, min, max, SortedSet.class);
    }

    public List<SortedSet> GetTopN(String key, Integer n) {
        return redis.getTopNFromSortedSet(key, n, SortedSet.class);
    }
}
