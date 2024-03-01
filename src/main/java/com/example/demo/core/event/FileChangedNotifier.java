package com.example.demo.core.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class FileChangedNotifier {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Scheduled(fixedDelay = 3, initialDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void notifyFileCouldChange() {
        applicationEventPublisher.publishEvent(new FileChangedEvent(this));
    }
}
