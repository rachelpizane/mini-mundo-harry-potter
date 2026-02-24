package edu.rachel.view;

import edu.rachel.constant.TerminalConstants;
import edu.rachel.controller.BruxoController;
import edu.rachel.dto.AppResponse;
import edu.rachel.dto.BruxoRequestDTO;
import edu.rachel.dto.BruxoResponseDTO;
import edu.rachel.enums.AppStatusEnum;
import edu.rachel.enums.CasaBruxoEnum;

import java.util.Objects;
import java.util.Scanner;

public class BruxoTerminal {
    Scanner scanner;
    BruxoController controller;

    public BruxoTerminal(Scanner scanner, BruxoController controller){
        this.scanner = scanner;
        this.controller = controller;
    }

    public void iniciar(){
        exibirCabecalhoPrincipal();
        System.out.println(TerminalConstants.INTRODUCAO);
        exibirMenuPrincipal();
    }

    private void exibirMenuPrincipal() {
        boolean iniciado = true;

        while (iniciado) {
            exibirCabecalho(TerminalConstants.TITULO_MENU_PRINCIPAL);
            String opcao = escolherOpcaoMenuPrincipal();

            switch (opcao) {
                case "1":
                    exibirCadastroBruxo();
                    break;
                case "2":
                    exibirDetalhesBruxo();
                    break;
                case "3":
                    // TODO: Implementar listagem
                case "4":
                    iniciado = false;
                    sair();
                    break;
                default:
                    System.out.println(alertarTexto(TerminalConstants.OPCAO_INVALIDA));
            }
        }
    }

    private void exibirCadastroBruxo() {
        exibirCabecalho(TerminalConstants.TITULO_CADASTRO);

        System.out.print(TerminalConstants.CADASTRO_NOME);
        String nomeBruxo = scanner.nextLine();

        CasaBruxoEnum casaBruxo = definirCasaBruxo();

        BruxoRequestDTO request = new BruxoRequestDTO(nomeBruxo, casaBruxo);
        cadastrarBruxo(request);
    }

    public void exibirDetalhesBruxo(){
        Long id = capturarId();
        if (Objects.isNull(id)) return;

        buscarDetalhesBruxo(id);
    }

    public void cadastrarBruxo(BruxoRequestDTO bruxoRequest){
        AppResponse response = controller.criarBruxo(bruxoRequest);

        if(exibirFalha(response)) return;

        BruxoResponseDTO bruxoSalvo = (BruxoResponseDTO) response.resposta();
        exibirCadastroSucesso(bruxoSalvo);
    }

    public void buscarDetalhesBruxo(Long id){
        AppResponse response = controller.buscarDetalhesBruxo(id);

        if(exibirFalha(response)) return;

        String detalhes = (String) response.resposta();

        exibirCabecalho(TerminalConstants.TITULO_DETALHES);
        System.out.print(detalhes);
    }

    private void sair(){
        System.out.println("\n" + TerminalConstants.ENCERRAMENTO);
        scanner.close();
    }

    private void exibirCabecalhoPrincipal(){
        String cabecalhoPrincipal =
                """
                =================================================
                *_*_*_*_*_*_ Mini Mundo Harry Potter _*_*_*_*_*_*
                =================================================
                """;

        System.out.println(cabecalhoPrincipal);
    }

    private void exibirCabecalho(String titulo){
        String cabecalho = String.format("\n" +
                """
                ------------------------------------------------
                %s
                ------------------------------------------------
                """,titulo.toUpperCase());

        System.out.print(cabecalho);
    }

    public Long capturarId(){
        System.out.print(TerminalConstants.PERGUNTA_DETALHES_ID);
        String idStr = scanner.nextLine();

        Long id;
        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            System.out.println(alertarTexto(TerminalConstants.ID_INVALIDO));
            return null;
        }

        return id;
    }

    private String escolherOpcaoMenuPrincipal(){
        String opcoes = String.format(
                """
                1. %s
                2. %s
                3. %s
                4. %s
                
                """,
                TerminalConstants.MENU_PRINCIPAL_OPCAO_CADASTRAR,
                TerminalConstants.MENU_PRINCIPAL_OPCAO_DETALHES,
                TerminalConstants.MENU_PRINCIPAL_OPCAO_LISTAR,
                TerminalConstants.MENU_PRINCIPAL_OPCAO_SAIR);

        System.out.print(opcoes);
        System.out.print(TerminalConstants.MENU_PRINCIPAL_DESCRICAO);

        return scanner.nextLine();
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
                    System.out.println(alertarTexto(TerminalConstants.OPCAO_INVALIDA));
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

        System.out.println(TerminalConstants.CADASTRO_CASA_DESCRICAO);
        System.out.print(opcoes);
        System.out.print(TerminalConstants.CADASTRO_CASA);

        return scanner.nextLine();
    }

    public void exibirCadastroSucesso(BruxoResponseDTO bruxoSalvo) {
        String dadosBruxo = String.format(TerminalConstants.CADASTRO_DETALHES,
                bruxoSalvo.id(),
                bruxoSalvo.nome(),
                bruxoSalvo.casa(),
                bruxoSalvo.tipo()
        );

        exibirCabecalho(TerminalConstants.TITULO_CADASTRO_CONCLUIDO);
        System.out.print(dadosBruxo);
    }

    public boolean exibirFalha(AppResponse response){
        if(response.status().equals(AppStatusEnum.ERRO)) {
            System.out.println(alertarTexto("[FALHA] " + response.resposta()));
            return true;
        }

        return false;
    }

    public String alertarTexto(String texto) {
        return TerminalConstants.ANSI_RED + texto + TerminalConstants.ANSI_RESET;
    }
}
