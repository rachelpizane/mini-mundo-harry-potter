package edu.rachel.model;

import edu.rachel.enums.CasaBruxoEnum;
import edu.rachel.enums.TipoMagiaEnum;

public abstract class Bruxo {
    private Long id;
    private String nome;
    private CasaBruxoEnum casa;
    private TipoMagiaEnum tipoMagia;

    protected Bruxo(String nome, CasaBruxoEnum casa, TipoMagiaEnum tipoMagia) {
        this.nome = nome;
        this.casa = casa;
        this.tipoMagia = tipoMagia;
    }

    public String mostrarInformacoes() {
        return String.format(
                """
                ID: %d
                NOME: %s
                CASA: %s
                TIPO MAGIA: %s
                """, this.id, this.nome, this.casa.getNome(), this.tipoMagia.getNome()
        );
    }

    public Long getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public CasaBruxoEnum getCasa() {
        return this.casa;
    }

    public TipoMagiaEnum getTipoMagia() {
        return this.tipoMagia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCasa(CasaBruxoEnum casa) {
        this.casa = casa;
    }

    public void setTipoMagia(TipoMagiaEnum tipoMagia) {
        this.tipoMagia = tipoMagia;
    }
}
