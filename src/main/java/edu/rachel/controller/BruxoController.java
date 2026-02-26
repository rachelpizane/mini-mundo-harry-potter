package edu.rachel.controller;

import edu.rachel.dto.AppResponse;
import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.dto.BruxoResponseDTO;
import edu.rachel.dto.BruxoResumeDTO;

import java.util.List;

public interface BruxoController {
    AppResponse<BruxoResponseDTO> criarBruxo(BruxoRequestDTO bruxoRequest);

    AppResponse<String> buscarDetalhesBruxo(Long id);

    AppResponse<String> realizarMagia(Long id);

    AppResponse<List<BruxoResumeDTO>> buscarBruxos();
}
