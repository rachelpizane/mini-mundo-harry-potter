package edu.rachel;

import edu.rachel.controller.BruxoController;
import edu.rachel.controller.BruxoControllerImpl;
import edu.rachel.helper.TerminalHelper;
import edu.rachel.helper.TerminalHelperImpl;
import edu.rachel.mapper.BruxoMapper;
import edu.rachel.mapper.BruxoMapperImpl;
import edu.rachel.repository.BruxoRepository;
import edu.rachel.repository.BruxoRepositoryImpl;
import edu.rachel.service.BruxoService;
import edu.rachel.service.BruxoServiceImpl;
import edu.rachel.view.BruxoTerminal;
import edu.rachel.view.BruxoTerminalImpl;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        TerminalHelper terminalHelper = new TerminalHelperImpl(scanner);

        BruxoRepository repository = new BruxoRepositoryImpl();
        BruxoMapper mapper = new BruxoMapperImpl();
        BruxoService service = new BruxoServiceImpl(mapper, repository);
        BruxoController controller = new BruxoControllerImpl(service);

        BruxoTerminal terminal = new BruxoTerminalImpl(terminalHelper, controller);

        terminal.executar();
    }
}