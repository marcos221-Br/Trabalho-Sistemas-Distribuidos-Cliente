package com.example.trabalhosistemasdistribuidos.enums;

public enum CompetenciaEnum {
    COM1("Python"),
    COM2("C#"),
    COM3("C++"),
    COM4("JS"),
    COM5("PHP"),
    COM6("Swift"),
    COM7("Java"),
    COM8("Go"),
    COM9("SQL"),
    COM10("Ruby"),
    COM11("HTML"),
    COM12("CSS"),
    COM13("NOSQL"),
    COM14("Flutter"),
    COM15("TypeScript"),
    COM16("Perl"),
    COM17("Cobol"),
    COM18("dotNet"),
    COM19("Kotlin"),
    COM20("Dart");

    private String competencia;

    private CompetenciaEnum(String competencia){
        this.competencia = competencia;
    }

    public String getCompetencia(){
        return this.competencia;
    }
}
