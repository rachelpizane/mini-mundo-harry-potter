package edu.rachel.repository;

import edu.rachel.model.Bruxo;

import java.util.List;

public interface BruxoRepository {
    Bruxo save(Bruxo bruxo);

    Bruxo buscarPorId(Long id);

    List<Bruxo> buscarTodos();
}
