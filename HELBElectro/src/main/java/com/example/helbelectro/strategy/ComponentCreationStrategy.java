package com.example.helbelectro.strategy;

import com.example.helbelectro.conponent.Component;

public interface ComponentCreationStrategy {
    Component createComponent(String[] values);
}
