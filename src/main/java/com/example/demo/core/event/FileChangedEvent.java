package com.example.demo.core.event;

import org.springframework.context.ApplicationEvent;

public class FileChangedEvent extends ApplicationEvent {
    public FileChangedEvent(Object source) {
        super(source);
    }
}
