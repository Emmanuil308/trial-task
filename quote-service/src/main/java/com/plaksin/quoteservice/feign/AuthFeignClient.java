package com.plaksin.quoteservice.feign;

import com.plaksin.quoteservice.dto.SavedUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface AuthFeignClient {

    @GetMapping("/api/internal/users/{userId}")
    SavedUserDto getUserById(@PathVariable("userId") Long userId);

}
