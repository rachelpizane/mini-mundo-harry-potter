package edu.rachel.helper;

import edu.rachel.dto.AppResponse;

public interface TerminalHelper {
    void finalizarTerminal();

    String lerEntrada(String prompt);

    Long capturarLong(String prompt, String mensagemErro);

    void exibirMensagem(String texto);

    void exibirMensagemSemQuebraLinha(String texto);

    void exibirMensagemAlerta(String texto);

    boolean exibirFalhaSeErro(AppResponse<?> response);
}
