package edu.rachel.mapper;

import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.dto.BruxoResponseDTO;
import edu.rachel.dto.BruxoResumeDTO;
import edu.rachel.factory.BruxoFactory;
import edu.rachel.model.Bruxo;

public class BruxoMapperImpl implements BruxoMapper {

    @Override
    public Bruxo toEntity(BruxoRequestDTO dto) {
        return BruxoFactory.create(dto);
    }

    @Override
    public BruxoResponseDTO toDto(Bruxo entity){
        return new BruxoResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getCasa().getNome(),
                entity.getTipoMagia().getNome()
        );
    }

    @Override
    public BruxoResumeDTO toBruxoResumeDto(Bruxo entity){
        return new BruxoResumeDTO(
                entity.getId(),
                entity.getNome()
        );
    }
}
