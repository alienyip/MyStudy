package com.example.mystudy;

/**
 * Created by Nature on 2017/6/28.
 */

public class Demo {
    private String demoname;

    private Class<?> demoactivity;

    private String demoURL;

    public Demo(String demoname, Class<?> demoactivity, String demoURL) {
        this.demoname = demoname;
        this.demoactivity = demoactivity;
        this.demoURL = demoURL;
    }

    public String getDemoname() {
        return demoname;
    }

    public Class<?> getDemoactivity() {
        return demoactivity;
    }

    public String getDemoURL() {
        return demoURL;
    }
}
