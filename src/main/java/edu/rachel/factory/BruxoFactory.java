package edu.rachel.factory;

import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.enums.CasaBruxoEnum;
import edu.rachel.model.Bruxo;
import edu.rachel.model.BruxoGrifinoria;
import edu.rachel.model.BruxoSonserina;

public class BruxoFactory {

    public static Bruxo create(BruxoRequestDTO dto) {
        if(dto.casa().equals(CasaBruxoEnum.GRIFINORIA)){
            return new BruxoGrifinoria(dto.nome());
        }

        if(dto.casa().equals(CasaBruxoEnum.SONSERINA)){
            return new BruxoSonserina(dto.nome());
        }

        throw new IllegalArgumentException("A casa informada nao existe");
    }

    private BruxoFactory() {}
}
