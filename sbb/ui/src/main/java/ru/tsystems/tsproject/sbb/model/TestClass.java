package ru.tsystems.tsproject.sbb.model;

import org.springframework.stereotype.Component;

@Component
public class TestClass {

    private String ss = "Hello! This is autowired bean";

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }
}
