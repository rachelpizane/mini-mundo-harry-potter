package edu.rachel.repository;

import edu.rachel.model.Bruxo;

public interface BruxoRepository {
    Bruxo save(Bruxo bruxo);

    Bruxo buscarPorId(Long id);
}
