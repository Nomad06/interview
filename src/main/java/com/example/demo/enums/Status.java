package com.example.demo.enums;

public enum Status {
  C("created"){},
  R("running"){},
  F("finished"){};

  private final String name;

  Status(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
