package io.xstefank.guardrails.input;

import jakarta.enterprise.context.RequestScoped;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class FailureAggregator {

    public List<String> failureList = new ArrayList<>();
}
