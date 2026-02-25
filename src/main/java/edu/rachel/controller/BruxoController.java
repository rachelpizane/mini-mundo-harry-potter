package edu.rachel.controller;

import edu.rachel.dto.AppResponse;
import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.dto.BruxoResponseDTO;

public interface BruxoController {
    AppResponse<BruxoResponseDTO> criarBruxo(BruxoRequestDTO bruxoRequest);

    AppResponse<String> buscarDetalhesBruxo(Long id);

    AppResponse<String> realizarMagia(Long id);
}
