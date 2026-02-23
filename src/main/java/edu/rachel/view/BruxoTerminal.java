package edu.rachel.view;

import edu.rachel.constant.TerminalConstants;

import java.util.Scanner;

public class BruxoTerminal {
    Scanner scanner;

    public BruxoTerminal(Scanner scanner){
        this.scanner = scanner;
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
                    // TODO: Implementar cadastro
                case "2":
                    // TODO: Implementar listagem
                case "3":
                    sair();
                    iniciado = false;
                    break;
                default:
                    System.out.println(
                            TerminalConstants.ANSI_RED + "Opcao invalida. Tente novamente\n" + TerminalConstants.ANSI_RESET);
            }
        }
    }

    private void sair(){
        System.out.println("\n" + TerminalConstants.ENCERRAMENTO);
        scanner.close();
    }

    private void exibirCabecalhoPrincipal(){
        String cabecalhoPrincipal =
                """
                ================================================
                *_*_*_*_ Mini Mundo Harry Potter _*_*_*_*
                ================================================
                """;

        System.out.println(cabecalhoPrincipal);
    }

    private void exibirCabecalho(String titulo){
        String cabecalho = String.format(
                """
                ------------------------------------------------
                %s
                ------------------------------------------------
                """,titulo.toUpperCase());

        System.out.print(cabecalho);
    }

    private String escolherOpcaoMenuPrincipal(){
        String opcoes = String.format(
                """
                1. %s
                2. %s
                3. %s
                
                """,
                TerminalConstants.MENU_PRINCIPAL_OPCAO_CADASTRAR,
                TerminalConstants.MENU_PRINCIPAL_OPCAO_LISTAR,
                TerminalConstants.MENU_PRINCIPAL_OPCAO_SAIR);

        System.out.print(opcoes);
        System.out.print(TerminalConstants.MENU_PRINCIPAL_DESCRICAO);

        return scanner.nextLine();
    }
}
