package edu.rachel.service;

import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.dto.BruxoResponseDTO;
import edu.rachel.dto.BruxoResumeDTO;
import edu.rachel.mapper.BruxoMapper;
import edu.rachel.model.Bruxo;
import edu.rachel.repository.BruxoRepository;

import java.util.List;
import java.util.Objects;

public class BruxoServiceImpl implements BruxoService {
    private final BruxoMapper mapper;
    private final BruxoRepository repository;

    public BruxoServiceImpl(BruxoMapper mapper, BruxoRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public BruxoResponseDTO criarBruxo(BruxoRequestDTO bruxoRequest) {
        validar(bruxoRequest);

        Bruxo bruxo = mapper.toEntity(bruxoRequest);

        Bruxo bruxoSalvo = repository.save(bruxo);

        return mapper.toDto(bruxoSalvo);
    }

    @Override
    public String buscarDetalhesBruxo(Long id){
        Bruxo bruxo = repository.buscarPorId(id);
        return bruxo.mostrarInformacoes();
    }

    @Override
    public String realizarMagia(Long id){
        Bruxo bruxo = repository.buscarPorId(id);
        return bruxo.lancarFeitico();
    }

    @Override
    public List<BruxoResumeDTO> buscarBruxos() {
        List<Bruxo> bruxos = repository.buscarTodos();
        return bruxos.stream().map(mapper::toBruxoResumeDto).toList();
    }

    private void validar(BruxoRequestDTO bruxoRequest) {
        if(Objects.isNull(bruxoRequest)) {
            throw new IllegalArgumentException("A requisicao nao pode ser nula");
        }

        if(Objects.isNull(bruxoRequest.nome()) || bruxoRequest.nome().isBlank()){
            throw new IllegalArgumentException("O campo nome deve ser preenchido");
        }

        if(Objects.isNull(bruxoRequest.casa())){
            throw new IllegalArgumentException("O campo casa deve ser preenchido");
        }
    }
}
