package World;

import command.Backpack;
import command.Movement;

import java.io.IOException;

public class Player {


    public Backpack backpack= new Backpack();
    private Location currentLocation;

    public Player() throws IOException {
    }


    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }




}
