package edu.rachel.model;

import edu.rachel.enums.CasaBruxoEnum;
import edu.rachel.enums.TipoMagiaEnum;

public class BruxoSonserina extends Bruxo {
    public BruxoSonserina(String nome){
        super(nome, CasaBruxoEnum.SONSERINA, TipoMagiaEnum.COMUM);
    }

    @Override
    public String lancarFeitico(){
        return "Serpensortia! - O bruxo da Sonserina lancou seu feitico!";
    }
}
