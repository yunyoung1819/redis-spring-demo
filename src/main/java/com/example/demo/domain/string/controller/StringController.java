package com.example.demo.domain.string.controller;

import com.example.demo.domain.string.model.request.MultiStringRequest;
import com.example.demo.domain.string.model.request.StringRequest;
import com.example.demo.domain.string.model.response.StringResponse;
import com.example.demo.domain.string.service.RedisString;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "string", description = "string api")
@RestController
@RequestMapping("/api/v1/set")
@RequiredArgsConstructor
public class StringController {
    private final RedisString redis;

    @PostMapping("/set-string-collection")
    public void setString(@RequestBody @Valid StringRequest req) {
        redis.Set(req);
    }

    @GetMapping("/get-string-collection")
    public StringResponse getString(@RequestParam @Valid String key) {
        return redis.Get(key);
    }

    @PostMapping("/multi-set-collection")
    public void multiString(@RequestBody @Valid MultiStringRequest req) {
        redis.MultiSet(req);
    }
}
