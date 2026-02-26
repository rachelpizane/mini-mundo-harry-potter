package edu.rachel.enums;

public enum TipoMagiaEnum {
    COMUM("Comum");

    private final String nome;

    TipoMagiaEnum(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
