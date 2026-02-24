package edu.rachel.service;

import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.dto.BruxoResponseDTO;
import edu.rachel.enums.CasaBruxoEnum;
import edu.rachel.enums.TipoMagiaEnum;
import edu.rachel.mapper.BruxoMapperImpl;
import edu.rachel.model.Bruxo;
import edu.rachel.model.BruxoGrifinoria;
import edu.rachel.repository.BruxoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    void quandoRequestValidoDeveSalvarBruxoCorretamente(){
        BruxoRequestDTO request = new BruxoRequestDTO("Bruxo", CasaBruxoEnum.GRIFINORIA);
        Bruxo bruxo = new BruxoGrifinoria(request.nome());
        bruxo.setId(1L);

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
    void quandoRequestNuloDeveFalharAoCadastrarBruxo(BruxoRequestDTO request){
        assertThrows(IllegalArgumentException.class, () -> {
            service.criarBruxo(request);
        });

        verify(repository, never()).save(any(Bruxo.class));
    }

    @ParameterizedTest
    @EmptySource
    void quandoNomeInvalidoDeveFalharAoCadastrarBruxo(String nome){
        BruxoRequestDTO request = new BruxoRequestDTO(nome, CasaBruxoEnum.GRIFINORIA);

        assertThrows(IllegalArgumentException.class, () -> {
            service.criarBruxo(request);
        });

        verify(repository, never()).save(any(Bruxo.class));
    }

    @Test
    void quandoCasaBruxoNulaDeveFalharAoCadastrarBruxo(){
        BruxoRequestDTO request = new BruxoRequestDTO("Bruxo", null);

        assertThrows(IllegalArgumentException.class, () -> {
            service.criarBruxo(request);
        });

        verify(repository, never()).save(any(Bruxo.class));
    }
}