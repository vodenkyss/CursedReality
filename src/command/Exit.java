package command;

import java.io.IOException;

public class Exit implements Command{

    @Override
    public String execute() throws IOException {
        return "Konec";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
