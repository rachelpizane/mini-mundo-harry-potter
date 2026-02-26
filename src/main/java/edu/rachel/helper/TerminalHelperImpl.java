package edu.rachel.helper;

import edu.rachel.constant.TerminalConstants;
import edu.rachel.dto.AppResponse;
import edu.rachel.enums.AppStatusEnum;

import java.util.Scanner;

public class TerminalHelperImpl implements TerminalHelper {
    private Scanner scanner;

    public TerminalHelperImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void finalizarTerminal(){
        scanner.close();
    }

    @Override
    public String lerEntrada(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public Long capturarLong(String prompt, String erro){
        String numStr = lerEntrada(prompt);

        Long num;
        try {
            num = Long.parseLong(numStr);

        } catch (NumberFormatException e) {
            exibirMensagemAlerta(erro);
            return null;
        }

        return num;
    }

    @Override
    public void exibirMensagem(String texto) {
        System.out.println(texto);
    }

    @Override
    public void exibirMensagemSemQuebraLinha(String texto) {
        System.out.print(texto);
    }

    @Override
    public boolean exibirFalhaSeErro(AppResponse<?> response) {
        if(response.status().equals(AppStatusEnum.ERRO)) {
            exibirMensagemAlerta("[FALHA] " + response.messageErro());
            return true;
        }

        return false;
    }

    @Override
    public void exibirMensagemAlerta(String texto) {
        exibirMensagem(destacarTextoAlerta(texto));
    }

    public String destacarTextoAlerta(String texto) {
        return TerminalConstants.ANSI_RED + texto + TerminalConstants.ANSI_RESET;
    }
}
