package edu.rachel.repository;

import edu.rachel.model.Bruxo;

import java.util.HashMap;
import java.util.Map;
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

    public void clear() {
        bruxos.clear();
        geradorId.set(0L);
    }
}
