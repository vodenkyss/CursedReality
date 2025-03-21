package command;

import java.io.IOException;

public interface Command {

    String execute() throws IOException;

    boolean exit();
}
