package com.example.demo.core.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CacheRemover {

    @CacheEvict(value = {"saleAndTrafficDateStatistic", "saleAndTrafficAsinStatistic"}, allEntries = true)
    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.SECONDS)
    public void evictMoviesCache() {
    }
}
