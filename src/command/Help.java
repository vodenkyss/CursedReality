package command;

import java.io.IOException;
import java.util.HashMap;

public class Help implements Command{

    private HashMap<String, Command> commandMap;

    public Help(HashMap<String, Command> commandMap) {
        this.commandMap = commandMap;
    }

    /**
     * Method that shows your
     * @return
     * @throws IOException
     */
    @Override
    public String execute() throws IOException {
        StringBuilder helpMessage = new StringBuilder("Dostupné příkazy:\n");

        for (String command : commandMap.keySet()) {
            helpMessage.append("- ").append(command).append("\n");
        }
        return helpMessage.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
