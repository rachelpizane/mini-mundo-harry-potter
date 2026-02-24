package edu.rachel.controller;

import edu.rachel.dto.AppResponse;
import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.dto.BruxoResponseDTO;
import edu.rachel.enums.AppStatusEnum;
import edu.rachel.enums.CasaBruxoEnum;
import edu.rachel.enums.TipoMagiaEnum;
import edu.rachel.service.BruxoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BruxoControllerImplTest {
    @Mock
    BruxoService service;

    @InjectMocks
    BruxoControllerImpl controller;

    @Test
    void quandoRequestValidoDeveCadastrarBruxoComSucesso(){
        BruxoRequestDTO request = new BruxoRequestDTO("Bruxo", CasaBruxoEnum.GRIFINORIA);
        BruxoResponseDTO response = new BruxoResponseDTO(
                1L, request.nome(), request.casa().getNome(), TipoMagiaEnum.COMUM.getNome());

        when(service.criarBruxo(request)).thenReturn(response);

        AppResponse appResponse = controller.criarBruxo(request);

        assertEquals(AppStatusEnum.SUCESSO, appResponse.status());
        assertTrue(appResponse.resposta() instanceof BruxoResponseDTO);
    }

    @Test
    void quandoRequestInvalidoDeveFalharCadastro(){
        BruxoRequestDTO request = new BruxoRequestDTO("Bruxo", CasaBruxoEnum.GRIFINORIA);
        String mensagemErro = "Request inválido";

        when(service.criarBruxo(request)).thenThrow(new IllegalArgumentException(mensagemErro));

        AppResponse appResponse = controller.criarBruxo(request);

        assertEquals(AppStatusEnum.ERRO, appResponse.status());
        assertEquals(mensagemErro, appResponse.resposta());
    }
}