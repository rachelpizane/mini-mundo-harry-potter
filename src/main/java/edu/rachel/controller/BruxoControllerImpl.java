package edu.rachel.controller;

import edu.rachel.dto.AppResponse;
import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.dto.BruxoResponseDTO;
import edu.rachel.dto.BruxoResumeDTO;
import edu.rachel.enums.AppStatusEnum;
import edu.rachel.service.BruxoService;

import java.util.List;
import java.util.function.Supplier;

public class BruxoControllerImpl implements BruxoController {
    private final BruxoService service;

    public BruxoControllerImpl(BruxoService service) {
        this.service = service;
    }

    @Override
    public AppResponse<BruxoResponseDTO> criarBruxo(BruxoRequestDTO bruxoRequest) {
        return executarChamadaComTratamento(() -> service.criarBruxo(bruxoRequest));
    }

    @Override
    public AppResponse<String> buscarDetalhesBruxo(Long id) {
        return executarChamadaComTratamento(() -> service.buscarDetalhesBruxo(id));
    }

    @Override
    public AppResponse<String> realizarMagia(Long id) {
        return executarChamadaComTratamento(() -> service.realizarMagia(id));
    }

    @Override
    public AppResponse<List<BruxoResumeDTO>> buscarBruxos() {
        return executarChamadaComTratamento(service::buscarBruxos);
    }

    private <T> AppResponse<T> executarChamadaComTratamento(Supplier<T> acao) {
        try {
            T resultado = acao.get();
            return new AppResponse<>(AppStatusEnum.SUCESSO, resultado, null);

        } catch (Exception e) {
            return new AppResponse<>(AppStatusEnum.ERRO, null, e.getMessage());
        }
    }
}
