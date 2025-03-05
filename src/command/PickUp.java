package command;

import World.Location;

import java.io.IOException;

public class PickUp implements Command{

    private Backpack backpack;
    private Location currentLocation;



    @Override
    public String execute() throws IOException {
        return null;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
