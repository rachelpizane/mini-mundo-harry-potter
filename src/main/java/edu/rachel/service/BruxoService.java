package edu.rachel.service;

import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.dto.BruxoResponseDTO;
import edu.rachel.dto.BruxoResumeDTO;

import java.util.List;

public interface BruxoService {
    BruxoResponseDTO criarBruxo(BruxoRequestDTO bruxoRequest);

    String buscarDetalhesBruxo(Long id);

    String realizarMagia(Long id);

    List<BruxoResumeDTO> buscarBruxos();
}
