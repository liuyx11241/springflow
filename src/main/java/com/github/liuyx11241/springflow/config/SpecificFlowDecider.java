package com.github.liuyx11241.springflow.config;

import com.github.liuyx11241.springflow.stereotype.SpecificFlow;

@FunctionalInterface
public interface SpecificFlowDecider {
    Class<? extends SpecificFlow> currentSpecific();
}
