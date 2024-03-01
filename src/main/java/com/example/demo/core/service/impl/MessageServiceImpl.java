package com.example.demo.core.service.impl;

import com.example.demo.core.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageSource messageSource;

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }
}
