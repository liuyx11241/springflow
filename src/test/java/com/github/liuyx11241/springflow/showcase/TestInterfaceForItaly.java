package com.github.liuyx11241.springflow.showcase;

import com.github.liuyx11241.springflow.stereotype.SpecificFlowQualifier;
import org.springframework.stereotype.Service;

@Service
@SpecificFlowQualifier(ItalySpecific.class)
public class TestInterfaceForItaly implements TestInterface {
    @Override
    public String output() {
        return "italy";
    }
}
