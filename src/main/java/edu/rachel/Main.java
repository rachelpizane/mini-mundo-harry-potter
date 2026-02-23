package edu.rachel;

import edu.rachel.view.BruxoTerminal;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        BruxoTerminal terminal = new BruxoTerminal(scanner);

        terminal.iniciar();
    }
}