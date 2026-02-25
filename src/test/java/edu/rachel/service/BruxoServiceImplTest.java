package edu.rachel.service;

import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.dto.BruxoResponseDTO;
import edu.rachel.dto.BruxoResumeDTO;
import edu.rachel.enums.CasaBruxoEnum;
import edu.rachel.enums.TipoMagiaEnum;
import edu.rachel.exception.NotFoundException;
import edu.rachel.factory.BruxoFactory;
import edu.rachel.mapper.BruxoMapperImpl;
import edu.rachel.model.Bruxo;
import edu.rachel.repository.BruxoRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BruxoServiceImplTest {
    @Mock
    BruxoRepository repository;

    @Spy
    BruxoMapperImpl mapper;

    @InjectMocks
    BruxoServiceImpl service;

    @Nested
    class CadastroBruxoTests {
        @Test
        void deveSalvarBruxoCorretamenteQuandoRequestValido() {
            BruxoRequestDTO request = construirBruxoRequestDTO("Bruxo", CasaBruxoEnum.GRIFINORIA);
            Bruxo bruxo = construirBruxo(request.nome(), request.casa());

            when(repository.save(any(Bruxo.class))).thenReturn(bruxo);

            BruxoResponseDTO response = service.criarBruxo(request);

            assertEquals(bruxo.getId(), response.id());
            assertEquals(request.nome(), response.nome());
            assertEquals(request.casa().getNome(), response.casa());
            assertEquals(TipoMagiaEnum.COMUM.getNome(), response.tipo());

            verify(repository, times(1)).save(any(Bruxo.class));
        }

        @ParameterizedTest
        @NullSource
        void deveFalharAoCadastrarBruxoQuandoRequestNulo(BruxoRequestDTO request) {
            assertThrows(IllegalArgumentException.class, () -> {
                service.criarBruxo(request);
            });

            verify(repository, never()).save(any(Bruxo.class));
        }

        @ParameterizedTest
        @EmptySource
        void deveFalharAoCadastrarBruxoQuandoNomeInvalido(String nome) {
            BruxoRequestDTO request = construirBruxoRequestDTO(nome, CasaBruxoEnum.GRIFINORIA);

            assertThrows(IllegalArgumentException.class, () -> {
                service.criarBruxo(request);
            });

            verify(repository, never()).save(any(Bruxo.class));
        }

        @Test
        void deveFalharAoCadastrarBruxoQuandoCasaBruxoForNula() {
            BruxoRequestDTO request = construirBruxoRequestDTO("Bruxo", null);

            assertThrows(IllegalArgumentException.class, () -> {
                service.criarBruxo(request);
            });

            verify(repository, never()).save(any(Bruxo.class));
        }
    }

    @Nested
    class DetalhesBruxoTests {
        @Test
        void deveBuscarDetalhesCorretamenteQuandoIdValido(){
            Bruxo bruxo = construirBruxoPadrao();

            when(repository.buscarPorId(bruxo.getId())).thenReturn(bruxo);

            String detalhes = service.buscarDetalhesBruxo(bruxo.getId());

            assertNotNull(detalhes);
        }

        @Test
        void deveFalharAoBuscarDetalhesQuandoIdInvalido(){
            Long id = 1L;

            when(repository.buscarPorId(id)).thenThrow(new NotFoundException("Bruxo nao encontrado"));

            assertThrows(NotFoundException.class, () -> {
                service.buscarDetalhesBruxo(id);
            });
        }
    }

    @Nested
    class RealizarMagiaTests {
        @Test
        void deveRealizarMagiaCorretamenteQuandoIdValido(){
            Bruxo bruxo = construirBruxoPadrao();

            when(repository.buscarPorId(bruxo.getId())).thenReturn(bruxo);

            String magia = service.realizarMagia(bruxo.getId());

            assertNotNull(magia);
        }

        @Test
        void deveFalharAoRealizarMagiaQuandoIdInvalido(){
            Long id = 1L;

            when(repository.buscarPorId(id)).thenThrow(new NotFoundException("Bruxo nao encontrado"));

            assertThrows(NotFoundException.class, () -> {
                service.realizarMagia(id);
            });
        }
    }

    @Nested
    class BuscarBruxosTests {
        @Test
        void deveBuscarBruxosCorretamente(){
            when(repository.buscarTodos()).thenReturn(List.of(construirBruxoPadrao()));

            List<BruxoResumeDTO> bruxos = service.buscarBruxos();

            assertEquals(1, bruxos.size());
        }

        @Test
        void deveBuscarBruxosCorretamenteQuandoNaoExistirBruxos(){
            when(repository.buscarTodos()).thenReturn(List.of());

            List<BruxoResumeDTO> bruxos = service.buscarBruxos();

            assertEquals(0, bruxos.size());
        }
    }

    public BruxoRequestDTO construirBruxoRequestDTO(String nome, CasaBruxoEnum casa){
        return new BruxoRequestDTO(nome, casa);
    }

    public Bruxo construirBruxoPadrao(){
        return construirBruxo("Bruxo", CasaBruxoEnum.GRIFINORIA);
    }

    public Bruxo construirBruxo(String nome, CasaBruxoEnum casa){
        Bruxo bruxo = BruxoFactory.create(construirBruxoRequestDTO(nome, casa));
        bruxo.setId(1L);

        return bruxo;
    }
}