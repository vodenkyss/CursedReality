package command;

import java.io.IOException;

public class Speak implements Command{
    @Override
    public String execute() throws IOException {
        return null;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
