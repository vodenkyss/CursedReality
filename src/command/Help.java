package command;

import java.io.IOException;
import java.util.HashMap;

public class Help implements Command{

    private HashMap<String, Command> commandMap; // Mapa příkazů

    public Help(HashMap<String, Command> commandMap) {
        this.commandMap = commandMap; // Předání mapy příkazů
    }

    @Override
    public String execute() throws IOException {
        StringBuilder helpMessage = new StringBuilder("Dostupné příkazy:\n");

        // Procházení mapy příkazů a přidání názvů příkazů do zprávy
        for (String command : commandMap.keySet()) {
            helpMessage.append("- ").append(command).append("\n");
        }

        return helpMessage.toString(); // Vrátí seznam příkazů
    }

    @Override
    public boolean exit() {
        return false;
    }
}
