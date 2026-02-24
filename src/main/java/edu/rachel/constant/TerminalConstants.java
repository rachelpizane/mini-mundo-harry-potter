package edu.rachel.constant;

import edu.rachel.enums.CasaBruxoEnum;

public class TerminalConstants {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String INTRODUCAO = "Seja bem-vindo(a) ao mundo de Harry Potter!";
    public static final String ENCERRAMENTO = "Obrigado(a) pela visita!";

    public static final String TITULO_MENU_PRINCIPAL = "Menu Principal";
    public static final String TITULO_CADASTRO = "Cadastrar Bruxo";

    public static final String MENU_PRINCIPAL_DESCRICAO = "Escolha uma das opcoes acima: ";
    public static final String MENU_PRINCIPAL_OPCAO_CADASTRAR = "Cadastrar novo Bruxo";
    public static final String MENU_PRINCIPAL_OPCAO_LISTAR = "Listar Bruxos";
    public static final String MENU_PRINCIPAL_OPCAO_SAIR = "Sair";

    public static final String CADASTRO_NOME = "Informe o nome do bruxo: ";
    public static final String CADASTRO_CASA_DESCRICAO = "Escolha uma das casas abaixo para o bruxo:";
    public static final String CADASTRO_CASA = "Casa escolhida: ";
    public static final String CADASTRO_CASA_OPCAO_GRIFINORIA = CasaBruxoEnum.GRIFINORIA.getNome();
    public static final String CADASTRO_CASA_OPCAO_SONSERINA = CasaBruxoEnum.SONSERINA.getNome();

    public static final String CADASTRO_CONCLUIDO = "O cadastro foi feito com sucesso!";
    public static final String CADASTRO_DETALHES =
                """
                ID: %d
                NOME: %s
                CASA: %s
                TIPO MAGIA: %s
                """;

    public static final String OPCAO_INVALIDA = ANSI_RED + "Opcao invalida. Tente novamente\n" + ANSI_RESET;

    private TerminalConstants() {}
}
