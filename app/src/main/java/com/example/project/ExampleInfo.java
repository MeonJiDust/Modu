package com.example.project;

public class ExampleInfo {
    String name;
    String info;

    public ExampleInfo(){} // 생성자 메서드
    public ExampleInfo(String name, String info){
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
