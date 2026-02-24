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
    public AppResponse criarBruxo(BruxoRequestDTO bruxoRequest) {
        try {
            BruxoResponseDTO response = service.criarBruxo(bruxoRequest);
            return new AppResponse(AppStatusEnum.SUCESSO, response);

        } catch (Exception e) {
            return new AppResponse(AppStatusEnum.ERRO, e.getMessage());
        }
    }
}
