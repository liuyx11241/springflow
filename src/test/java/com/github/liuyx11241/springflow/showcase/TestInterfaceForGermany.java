package com.github.liuyx11241.springflow.showcase;

import com.github.liuyx11241.springflow.stereotype.SpecificFlowQualifier;
import org.springframework.stereotype.Service;

@Service
@SpecificFlowQualifier(GermanySpecific.class)
public class TestInterfaceForGermany implements TestInterface {
    @Override
    public String output() {
        return "germany";
    }
}
