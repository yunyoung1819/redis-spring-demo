package com.example.demo.domain.hashes.controller;

import com.example.demo.domain.hashes.model.request.HashModel;
import com.example.demo.domain.hashes.model.request.HashRequest;
import com.example.demo.domain.hashes.service.RedisHash;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "hash", description = "hash api")
@RestController
@RequestMapping("/api/v1/hash")
@RequiredArgsConstructor
public class HashesController {

    private final RedisHash redis;

    @PostMapping("/put-hashes")
    public void PutHashes(@RequestBody @Valid HashRequest req) {
        redis.putInHash(req.baseRequest().Key(), req.field(), req.Name());
    }

    @GetMapping("/get-hash-value")
    public HashModel GetHashes(@RequestParam @Valid String key, @RequestParam @Valid String field) {
        return redis.GetFromHash(key, field);
    }
}
