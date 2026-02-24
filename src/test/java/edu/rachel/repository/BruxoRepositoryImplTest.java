package edu.rachel.repository;

import edu.rachel.exception.NotFoundException;
import edu.rachel.model.Bruxo;
import edu.rachel.model.BruxoGrifinoria;
import edu.rachel.model.BruxoSonserina;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

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
            Bruxo bruxo = new BruxoGrifinoria("Bruxo");
            Bruxo bruxoSalvo = repository.save(bruxo);

            assertEquals(1L, bruxoSalvo.getId());
        }

        @Test
        void deveIncrementarIdAoSalvarProximoBruxo() {
            Bruxo bruxoGrifinoria = new BruxoGrifinoria("Bruxo");
            Bruxo bruxoSonserina = new BruxoSonserina("Bruxo2");

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
            Bruxo bruxoGrifinoria = new BruxoGrifinoria("Bruxo");
            Bruxo bruxoGrifinoriaSalvo = repository.save(bruxoGrifinoria);

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
}