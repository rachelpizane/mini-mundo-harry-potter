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
            return new AppResponse<BruxoResponseDTO>(AppStatusEnum.SUCESSO, response);

        } catch (Exception e) {
            return new AppResponse<String>(AppStatusEnum.ERRO, e.getMessage());
        }
    }

    @Override
    public AppResponse buscarDetalhesBruxo(Long id) {
        try {
            String detalhes = service.buscarDetalhesBruxo(id);
            return new AppResponse<String>(AppStatusEnum.SUCESSO, detalhes);

        } catch (Exception e) {
            return new AppResponse<String>(AppStatusEnum.ERRO, e.getMessage());
        }
    }
}
