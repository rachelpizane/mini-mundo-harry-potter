package edu.rachel.service;

import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.dto.BruxoResponseDTO;

public interface BruxoService {
    BruxoResponseDTO criarBruxo(BruxoRequestDTO bruxoRequest);

    String buscarDetalhesBruxo(Long id);
}
