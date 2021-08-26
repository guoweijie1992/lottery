package com.hzsmk.talentcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jiangjiaheng
 */
@SpringBootApplication(scanBasePackages = "com.hzsmk.**")
public class TalentcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalentcodeApplication.class, args);
    }

}
