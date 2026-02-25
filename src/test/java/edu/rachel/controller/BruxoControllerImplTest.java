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

            assertEquals(AppStatusEnum.SUCESSO, appResponse.status());
            assertTrue(appResponse.resposta() instanceof BruxoResponseDTO);
            assertNull(appResponse.messageErro());
        }

        @Test
        void deveFalharCadastroQuandoRequestInvalido() {
            BruxoRequestDTO request = new BruxoRequestDTO("Bruxo", CasaBruxoEnum.GRIFINORIA);
            String mensagemErro = "Request inválido";

            when(service.criarBruxo(request)).thenThrow(new IllegalArgumentException(mensagemErro));

            AppResponse appResponse = controller.criarBruxo(request);

            assertEquals(AppStatusEnum.ERRO, appResponse.status());
            assertEquals(mensagemErro, appResponse.messageErro());
            assertNull(appResponse.resposta());
        }
    }

    @Nested
    class DetalhesBruxoTests {
        @Test
        void deveBuscarDetalhesBruxoValido() {
            Long id = 1L;
            String detalhes = "Detalhes do Bruxo";

            when(service.buscarDetalhesBruxo(id)).thenReturn(detalhes);

            AppResponse appResponse = controller.buscarDetalhesBruxo(id);

            assertEquals(AppStatusEnum.SUCESSO, appResponse.status());
            assertEquals(detalhes, appResponse.resposta());
            assertNull(appResponse.messageErro());
        }

        @Test
        void deveFalharBuscaQuandoIdInvalido() {
            Long id = 9L;
            String mensagemErro = "Bruxo nao encontrado";

            when(service.buscarDetalhesBruxo(id)).thenThrow(new NotFoundException(mensagemErro));

            AppResponse appResponse = controller.buscarDetalhesBruxo(id);
            assertEquals(mensagemErro, appResponse.messageErro());
            assertNull(appResponse.resposta());
        }
    }

    @Nested
    class RealizarMagiaTests {
        @Test
        void deveRealizarMagiaQuandoBruxoValido() {
            Long id = 1L;
            String magia = "Bruxo lancou um feitico";

            when(service.realizarMagia(id)).thenReturn(magia);

            AppResponse appResponse = controller.realizarMagia(id);

            assertEquals(AppStatusEnum.SUCESSO, appResponse.status());
            assertEquals(magia, appResponse.resposta());
            assertNull(appResponse.messageErro());
        }

        @Test
        void deveFalharMagiaQuandoIdInvalido() {
            Long id = 9L;
            String mensagemErro = "Bruxo nao encontrado";

            when(service.realizarMagia(id)).thenThrow(new NotFoundException(mensagemErro));

            AppResponse appResponse = controller.realizarMagia(id);
            assertEquals(mensagemErro, appResponse.messageErro());
            assertNull(appResponse.resposta());
        }
    }

    @Nested
    class BuscarBruxosTests {
        @Test
        void deveBuscarBruxosComSucesso(){
            BruxoResumeDTO bruxo = new BruxoResumeDTO(1L, "Bruxo");
            when(service.buscarBruxos()).thenReturn(List.of(bruxo));

            AppResponse appResponse = controller.buscarBruxos();

            assertEquals(AppStatusEnum.SUCESSO, appResponse.status());
            assertEquals(List.of(bruxo), appResponse.resposta());
            assertNull(appResponse.messageErro());
        }

        @Test
        void deveBuscarBruxosComSucessoQuandoListaForVazia(){
            when(service.buscarBruxos()).thenReturn(List.of());

            AppResponse appResponse = controller.buscarBruxos();

            assertEquals(AppStatusEnum.SUCESSO, appResponse.status());
            assertEquals(List.of(), appResponse.resposta());
            assertNull(appResponse.messageErro());
        }
    }
}