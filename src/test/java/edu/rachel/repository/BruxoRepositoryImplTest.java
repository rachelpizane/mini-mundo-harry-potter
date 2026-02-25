package edu.rachel.repository;

import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.enums.CasaBruxoEnum;
import edu.rachel.exception.NotFoundException;
import edu.rachel.factory.BruxoFactory;
import edu.rachel.model.Bruxo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BruxoRepositoryImplTest {

    @InjectMocks
    BruxoRepositoryImpl repository;

    @AfterEach
    void setDown(){
        repository.clear();
    }

    @Nested
    class SalvarBruxoTests {

        @Test
        void deveSalvarBruxoCorretamente() {
            Bruxo bruxoSalvo = repository.save(construirBruxoPadrao());

            assertEquals(1L, bruxoSalvo.getId());
        }

        @Test
        void deveIncrementarIdAoSalvarProximoBruxo() {
            Bruxo bruxoGrifinoria = construirBruxoPadrao();
            Bruxo bruxoSonserina = construirBruxo("Bruxo", CasaBruxoEnum.SONSERINA);

            Bruxo bruxoGrifinoriaSalvo = repository.save(bruxoGrifinoria);
            Bruxo bruxoSonserinaSalvo = repository.save(bruxoSonserina);

            assertEquals(1L, bruxoGrifinoriaSalvo.getId());
            assertEquals(2L, bruxoSonserinaSalvo.getId());
        }
    }

    @Nested
    class BuscarPorIdTests {
        @Test
        void deveBuscarPorIdCorretamentoQuandoIdValido(){
            Bruxo bruxoGrifinoriaSalvo = repository.save(construirBruxoPadrao());
            Bruxo bruxoBuscado = repository.buscarPorId(bruxoGrifinoriaSalvo.getId());

            assertNotNull(bruxoBuscado);
        }

        @Test
        void deveFalharAoBuscarPorIdQuandoIdInvalido(){
            assertThrows(NotFoundException.class, () -> {
                repository.buscarPorId(1L);
            });
        }
    }

    @Nested
    class BuscarTodosTests {
        @Test
        void deveBuscarTodosCorretamente(){
            repository.save(construirBruxoPadrao());

            List<Bruxo> bruxos = repository.buscarTodos();
            assertEquals(1, bruxos.size());
        }

        @Test
        void deveRetornarListaVaziaQuandoNaoHaBruxos(){
            List<Bruxo> bruxos = repository.buscarTodos();
            assertEquals(0, bruxos.size());
        }
    }

    public Bruxo construirBruxoPadrao(){
        return construirBruxo("Bruxo", CasaBruxoEnum.GRIFINORIA);
    }

    public Bruxo construirBruxo(String nome, CasaBruxoEnum casa ){
        return BruxoFactory.create(new BruxoRequestDTO(nome, casa));
    }
}