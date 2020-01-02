package com.github.liuyx11241.springflow.showcase;

import com.github.liuyx11241.springflow.stereotype.EnableSpecificFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.github.liuyx11241.springflow")
@RestController
@EnableSpecificFlow
public class ShowcaseApplication {

    @Autowired
    TestInterface testInterface;

    @GetMapping("test")
    public String getSpecific() {
        return testInterface.output();
    }

    public static void main(String[] args) {
        SpringApplication.run(ShowcaseApplication.class, args);
    }
}
