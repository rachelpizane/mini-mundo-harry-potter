package edu.rachel.view;

import edu.rachel.constant.TerminalConstants;
import edu.rachel.controller.BruxoController;
import edu.rachel.dto.AppResponse;
import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.dto.BruxoResponseDTO;
import edu.rachel.dto.BruxoResumeDTO;
import edu.rachel.enums.CasaBruxoEnum;
import edu.rachel.helper.TerminalHelper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BruxoTerminalImpl implements BruxoTerminal {
    TerminalHelper terminalHelper;
    BruxoController controller;

    public BruxoTerminalImpl(TerminalHelper terminalHelper, BruxoController controller){
        this.controller = controller;
        this.terminalHelper = terminalHelper;
    }

    @Override
    public void executar(){
        exibirCabecalhoPrincipal();
        terminalHelper.exibirMensagem(TerminalConstants.INTRODUCAO);

        exibirMenuPrincipal();
        sair();
    }

    private void exibirMenuPrincipal() {
        boolean rodando = true;

        while (rodando) {
            exibirCabecalho(TerminalConstants.TITULO_MENU_PRINCIPAL);
            String opcao = escolherOpcaoMenuPrincipal();

            switch (opcao) {
                case "1":
                    exibirBruxos();
                    break;
                case "2":
                    exibirCadastroBruxo();
                    break;
                case "3":
                    exibirDetalhesBruxo();
                    break;
                case "4":
                    exibirLancamentoFeitico();
                    break;
                case "5":
                    rodando = false;
                    break;
                default:
                    terminalHelper.exibirMensagemAlerta(TerminalConstants.OPCAO_INVALIDA);
            }
        }
    }

    private void exibirBruxos(){
        exibirCabecalho(TerminalConstants.TITULO_BRUXOS);
        buscarBruxos();
    }

    private void exibirCadastroBruxo() {
        exibirCabecalho(TerminalConstants.TITULO_CADASTRO);

        String nomeBruxo = terminalHelper.lerEntrada(TerminalConstants.CADASTRO_NOME);

        CasaBruxoEnum casaBruxo = definirCasaBruxo();

        BruxoRequestDTO request = new BruxoRequestDTO(nomeBruxo, casaBruxo);
        cadastrarBruxo(request);
    }

    public void exibirDetalhesBruxo(){
        Long id = capturarId();
        if (Objects.isNull(id)) return;

        buscarDetalhesBruxo(id);
    }

    public void exibirLancamentoFeitico(){
        Long id = capturarId();
        if (Objects.isNull(id)) return;

        realizarFeitico(id);
    }

    public void buscarBruxos(){
        AppResponse<List<BruxoResumeDTO>> response = controller.buscarBruxos();

        if(terminalHelper.exibirFalhaSeErro(response)) return;

        List<BruxoResumeDTO> bruxos = response.resposta();

        exibirBruxos(bruxos);
    }

    public void cadastrarBruxo(BruxoRequestDTO bruxoRequest){
        AppResponse<BruxoResponseDTO> response = controller.criarBruxo(bruxoRequest);

        if(terminalHelper.exibirFalhaSeErro(response)) return;

        BruxoResponseDTO bruxoSalvo = response.resposta();
        exibirCadastroSucesso(bruxoSalvo);
    }

    public void buscarDetalhesBruxo(Long id){
        AppResponse<String> response = controller.buscarDetalhesBruxo(id);

        if(terminalHelper.exibirFalhaSeErro(response)) return;

        String detalhes = response.resposta();

        exibirCabecalho(TerminalConstants.TITULO_DETALHES);
        terminalHelper.exibirMensagemSemQuebraLinha(detalhes);
    }

    public void realizarFeitico(Long id){
        AppResponse<String> response = controller.realizarMagia(id);

        if(terminalHelper.exibirFalhaSeErro(response)) return;

        String feitico = response.resposta();

        exibirCabecalho(TerminalConstants.TITULO_FEITICO);
        terminalHelper.exibirMensagem(feitico);
    }

    private void sair(){
        terminalHelper.exibirMensagem(System.lineSeparator() + TerminalConstants.ENCERRAMENTO);
        terminalHelper.finalizarTerminal();
    }

    private void exibirCabecalhoPrincipal(){
        String cabecalhoPrincipal =
                """
                =================================================
                *_*_*_*_*_*_ Mini Mundo Harry Potter _*_*_*_*_*_*
                =================================================
                """;

        terminalHelper.exibirMensagem(cabecalhoPrincipal);
    }

    private void exibirCabecalho(String titulo){
        String cabecalho = System.lineSeparator() + String.format(
                """
                ------------------------------------------------
                %s
                ------------------------------------------------
                """,titulo.toUpperCase());

        terminalHelper.exibirMensagemSemQuebraLinha(cabecalho);
    }

    private String escolherOpcaoMenuPrincipal(){
        String opcoes = String.format(
                """
                1. %s
                2. %s
                3. %s
                4. %s
                5. %s
                
                """,
                TerminalConstants.MENU_PRINCIPAL_OPCAO_LISTAR,
                TerminalConstants.MENU_PRINCIPAL_OPCAO_CADASTRAR,
                TerminalConstants.MENU_PRINCIPAL_OPCAO_DETALHES,
                TerminalConstants.MENU_PRINCIPAL_OPCAO_FEITICO,
                TerminalConstants.MENU_PRINCIPAL_OPCAO_SAIR);

        terminalHelper.exibirMensagemSemQuebraLinha(opcoes);
        return terminalHelper.lerEntrada(TerminalConstants.MENU_PRINCIPAL_DESCRICAO);
    }

    private CasaBruxoEnum definirCasaBruxo(){
        while (true) {
            String opcao = escolherOpcaoCasaBruxo();

            switch (opcao) {
                case "1":
                    return CasaBruxoEnum.GRIFINORIA;
                case "2":
                    return CasaBruxoEnum.SONSERINA;
                default:
                    terminalHelper.exibirMensagemAlerta(TerminalConstants.OPCAO_INVALIDA + System.lineSeparator());
            }

        }
    }

    private String escolherOpcaoCasaBruxo(){
        String opcoes = String.format(
                """
                1. %s
                2. %s
                """,
                TerminalConstants.CADASTRO_CASA_OPCAO_GRIFINORIA,
                TerminalConstants.CADASTRO_CASA_OPCAO_SONSERINA
        );

        terminalHelper.exibirMensagem(TerminalConstants.CADASTRO_CASA_DESCRICAO);
        terminalHelper.exibirMensagemSemQuebraLinha(opcoes);
        return terminalHelper.lerEntrada(TerminalConstants.CADASTRO_CASA);
    }

    public Long capturarId(){
        return terminalHelper.capturarLong(
                TerminalConstants.PERGUNTA_DETALHES_ID,
                TerminalConstants.ID_INVALIDO);
    }

    public void exibirBruxos(List<BruxoResumeDTO> bruxos){
        if(bruxos.isEmpty()){
            terminalHelper.exibirMensagem(TerminalConstants.SEM_BRUXOS);
            return;
        }

        String listaBruxos = bruxos.stream()
                .map(bruxo -> String.format("ID: %d - NOME: %s", bruxo.id(), bruxo.nome()))
                .collect(Collectors.joining(System.lineSeparator()));

        terminalHelper.exibirMensagem(listaBruxos);
    }

    public void exibirCadastroSucesso(BruxoResponseDTO bruxoSalvo) {
        String dadosBruxo = String.format(TerminalConstants.CADASTRO_DETALHES,
                bruxoSalvo.id(),
                bruxoSalvo.nome(),
                bruxoSalvo.casa(),
                bruxoSalvo.tipo()
        );

        exibirCabecalho(TerminalConstants.TITULO_CADASTRO_CONCLUIDO);
        terminalHelper.exibirMensagemSemQuebraLinha(dadosBruxo);
    }
}
