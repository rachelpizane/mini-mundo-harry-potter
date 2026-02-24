package edu.rachel.controller;

import edu.rachel.dto.AppResponse;
import edu.rachel.dto.BruxoRequestDTO;

public interface BruxoController {
    AppResponse criarBruxo(BruxoRequestDTO bruxoRequest);

    AppResponse buscarDetalhesBruxo(Long id);
}
