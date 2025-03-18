package World;

import command.Backpack;
import command.Movement;

import java.io.IOException;

public class Player {


    private Location currentLocation;
    private Backpack backpack;

    public Player() throws IOException {
    }


    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }




}
