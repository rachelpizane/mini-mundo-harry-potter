package edu.rachel.controller;

import edu.rachel.dto.AppResponse;
import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.dto.BruxoResponseDTO;
import edu.rachel.enums.AppStatusEnum;
import edu.rachel.service.BruxoService;

public class BruxoControllerImpl implements BruxoController {
    private final BruxoService service;

    public BruxoControllerImpl(BruxoService service) {
        this.service = service;
    }

    @Override
    public AppResponse<BruxoResponseDTO> criarBruxo(BruxoRequestDTO bruxoRequest) {
        try {
            BruxoResponseDTO response = service.criarBruxo(bruxoRequest);
            return new AppResponse<>(AppStatusEnum.SUCESSO, response, null);

        } catch (Exception e) {
            return new AppResponse<>(AppStatusEnum.ERRO, null, e.getMessage());
        }
    }

    @Override
    public AppResponse<String> buscarDetalhesBruxo(Long id) {
        try {
            String detalhes = service.buscarDetalhesBruxo(id);
            return new AppResponse<>(AppStatusEnum.SUCESSO, detalhes, null);

        } catch (Exception e) {
            return new AppResponse<>(AppStatusEnum.ERRO, null, e.getMessage());
        }
    }
}
