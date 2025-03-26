package Test;

import command.Help;
import command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class HelpTest {

    private Help helpCommand;
    private HashMap<String, Command> commandMap;

    @BeforeEach
    void setUp() {
        commandMap = new HashMap<>();


        commandMap.put("jdi", new Command() {
            @Override
            public String execute() {
                return "Přesun do jiné místnosti";
            }

            @Override
            public boolean exit() {
                return false;
            }
        });

        commandMap.put("prozkoumej", new Command() {
            @Override
            public String execute() {
                return "Prozkoumat";
            }

            @Override
            public boolean exit() {
                return false;
            }
        });

        helpCommand = new Help(commandMap);
    }

    @Test
    void testExecute() throws IOException {
        String result = helpCommand.execute();

        assertTrue(result.contains("Dostupné příkazy:"));
        assertTrue(result.contains("- jdi"));
        assertTrue(result.contains("- prozkoumej"));
    }
}
