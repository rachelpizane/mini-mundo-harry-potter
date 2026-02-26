package edu.rachel.enums;

public enum CasaBruxoEnum {
    GRIFINORIA("Grifinoria"),
    SONSERINA("Sonserina");

    private final String nome;

    CasaBruxoEnum(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }
}
