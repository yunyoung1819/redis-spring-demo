package com.example.demo.domain.sortedSet.controller;

import com.example.demo.domain.sortedSet.model.SortedSet;
import com.example.demo.domain.sortedSet.model.request.SortedSetRequest;
import com.example.demo.domain.sortedSet.service.RedisSortedSet;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "sorted sorted", description = "sorted set api")
@RestController
@RequestMapping("/api/v1/sorted-set")
@RequiredArgsConstructor
public class SortedSetController {

    private final RedisSortedSet redis;

    @PostMapping("/sorted-set-collection")
    public void SetSortedSet(@RequestBody @Valid SortedSetRequest req) {
        redis.SetSortedSet(req);
    }

    @GetMapping("/get-sorted-set-by-range")
    public Set<SortedSet> GetSortedSet(@RequestParam @Valid String key,
        @RequestParam @Valid Float min, @RequestParam @Valid Float max) {
        return redis.GetSetDataByRange(key, min, max);
    }

    @GetMapping("/get-sorted-set-by-top")
    public Set<SortedSet> GetTopN(@RequestParam @Valid String key, @RequestParam @Valid Integer n) {
        return redis.GetTopN(key, n);
    }
}
