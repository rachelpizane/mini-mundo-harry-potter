package edu.rachel.controller;

import edu.rachel.dto.AppResponse;
import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.dto.BruxoResponseDTO;
import edu.rachel.dto.BruxoResumeDTO;
import edu.rachel.enums.AppStatusEnum;
import edu.rachel.enums.CasaBruxoEnum;
import edu.rachel.enums.TipoMagiaEnum;
import edu.rachel.exception.NotFoundException;
import edu.rachel.service.BruxoService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BruxoControllerImplTest {
    @Mock
    BruxoService service;

    @InjectMocks
    BruxoControllerImpl controller;

    @Nested
    class CadastroBruxoTests {
        @Test
        void deveCadastrarBruxoComSucessoQuandoRequestValido() {
            BruxoRequestDTO request = new BruxoRequestDTO("Bruxo", CasaBruxoEnum.GRIFINORIA);
            BruxoResponseDTO response = new BruxoResponseDTO(
                    1L, request.nome(), request.casa().getNome(), TipoMagiaEnum.COMUM.getNome());

            when(service.criarBruxo(request)).thenReturn(response);

            AppResponse appResponse = controller.criarBruxo(request);

            verificarSucesso(appResponse);
            assertTrue(appResponse.resposta() instanceof BruxoResponseDTO);
        }

        @Test
        void deveFalharCadastroQuandoRequestInvalido() {
            BruxoRequestDTO request = new BruxoRequestDTO("Bruxo", CasaBruxoEnum.GRIFINORIA);
            String mensagemErro = "Request inválido";

            when(service.criarBruxo(request)).thenThrow(new IllegalArgumentException(mensagemErro));

            AppResponse<BruxoResponseDTO> appResponse = controller.criarBruxo(request);

            verificarFalha(appResponse, mensagemErro);
        }
    }

    @Nested
    class DetalhesBruxoTests {
        @Test
        void deveBuscarDetalhesBruxoValido() {
            Long id = 1L;
            String detalhes = "Detalhes do Bruxo";

            when(service.buscarDetalhesBruxo(id)).thenReturn(detalhes);

            AppResponse<String> appResponse = controller.buscarDetalhesBruxo(id);

            verificarSucesso(appResponse);
            assertEquals(detalhes, appResponse.resposta());
        }

        @Test
        void deveFalharBuscaQuandoIdInvalido() {
            Long id = 9L;
            String mensagemErro = "Bruxo nao encontrado";

            when(service.buscarDetalhesBruxo(id)).thenThrow(new NotFoundException(mensagemErro));

            AppResponse<String> appResponse = controller.buscarDetalhesBruxo(id);

            verificarFalha(appResponse, mensagemErro);
        }
    }

    @Nested
    class RealizarMagiaTests {
        @Test
        void deveRealizarMagiaQuandoBruxoValido() {
            Long id = 1L;
            String magia = "Bruxo lancou um feitico";

            when(service.realizarMagia(id)).thenReturn(magia);

            AppResponse<String> appResponse = controller.realizarMagia(id);

            verificarSucesso(appResponse);
            assertEquals(magia, appResponse.resposta());
        }

        @Test
        void deveFalharMagiaQuandoIdInvalido() {
            Long id = 9L;
            String mensagemErro = "Bruxo nao encontrado";

            when(service.realizarMagia(id)).thenThrow(new NotFoundException(mensagemErro));

            AppResponse<String> appResponse = controller.realizarMagia(id);

            verificarFalha(appResponse, mensagemErro);
        }
    }

    @Nested
    class BuscarBruxosTests {
        @Test
        void deveBuscarBruxosComSucesso(){
            BruxoResumeDTO bruxo = new BruxoResumeDTO(1L, "Bruxo");
            when(service.buscarBruxos()).thenReturn(List.of(bruxo));

            AppResponse<List<BruxoResumeDTO>> appResponse = controller.buscarBruxos();

            verificarSucesso(appResponse);
        }

        @Test
        void deveBuscarBruxosComSucessoQuandoListaForVazia(){
            when(service.buscarBruxos()).thenReturn(List.of());

            AppResponse<List<BruxoResumeDTO>> appResponse = controller.buscarBruxos();

            verificarSucesso(appResponse);
        }
    }

    private void verificarFalha(AppResponse<?> appResponse, String mensagemErro) {
        assertEquals(AppStatusEnum.ERRO, appResponse.status());
        assertEquals(mensagemErro, appResponse.messageErro());
        assertNull(appResponse.resposta());
    }

    private void verificarSucesso(AppResponse<?> appResponse) {
        assertEquals(AppStatusEnum.SUCESSO, appResponse.status());
        assertNull(appResponse.messageErro());
        assertNotNull(appResponse.resposta());
    }
}