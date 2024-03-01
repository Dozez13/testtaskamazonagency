package com.example.demo.web.security.model;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class SkipPathRequestMatcher implements RequestMatcher {
    private final OrRequestMatcher matchers;
    private final RequestMatcher processingMatcher;

    public SkipPathRequestMatcher(List<String> pathToSkip, String processingPath) {
        List<RequestMatcher> matchersList = pathToSkip.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList());
        matchers = new OrRequestMatcher(matchersList);
        processingMatcher = new AntPathRequestMatcher(processingPath);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if (matchers.matches(request)) {
            return false;
        }
        return processingMatcher.matches(request);
    }
}
