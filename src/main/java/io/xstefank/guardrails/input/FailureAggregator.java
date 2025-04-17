package io.xstefank.guardrails.input;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;

import java.util.concurrent.ConcurrentHashMap;

@RequestScoped
public class FailureAggregator {

    public ConcurrentHashMap<String, String> failureMap = new ConcurrentHashMap<>();
}
