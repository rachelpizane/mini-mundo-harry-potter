package edu.rachel.mapper;

import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.dto.BruxoResponseDTO;
import edu.rachel.dto.BruxoResumeDTO;
import edu.rachel.model.Bruxo;

public interface BruxoMapper {
    Bruxo toEntity(BruxoRequestDTO dto);

    BruxoResponseDTO toDto(Bruxo entity);

    BruxoResumeDTO toBruxoResumeDto(Bruxo entity);
}
