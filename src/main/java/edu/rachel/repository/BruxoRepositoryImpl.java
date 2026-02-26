package edu.rachel.repository;

import edu.rachel.exception.NotFoundException;
import edu.rachel.model.Bruxo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class BruxoRepositoryImpl implements BruxoRepository {
    Map<Long, Bruxo> bruxos = new HashMap<>();
    AtomicLong geradorId = new AtomicLong(0L);

    @Override
    public Bruxo save(Bruxo bruxo){
        bruxo.setId(geradorId.incrementAndGet());
        bruxos.put(bruxo.getId(), bruxo);

        return bruxos.get(bruxo.getId());
    }

    @Override
    public Bruxo buscarPorId(Long id) {
        Bruxo bruxo = bruxos.get(id);

        if(Objects.isNull(bruxo)) {
            throw new NotFoundException("Bruxo nao encontrado");
        }

        return bruxo;
    }

    @Override
    public List<Bruxo> buscarTodos(){
        if(bruxos.isEmpty()){
            return List.of();
        }

        return bruxos.values().stream().toList();
    }

    public void clear() {
        bruxos.clear();
        geradorId.set(0L);
    }
}
