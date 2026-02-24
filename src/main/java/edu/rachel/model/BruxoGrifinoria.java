package edu.rachel.model;

import edu.rachel.enums.CasaBruxoEnum;
import edu.rachel.enums.TipoMagiaEnum;

public class BruxoGrifinoria extends Bruxo {
    public BruxoGrifinoria(String nome){
        super(nome, CasaBruxoEnum.GRIFINORIA, TipoMagiaEnum.COMUM);
    }
}
