package World;

import command.Backpack;

public class Player {



    private Backpack backpack= new Backpack();
    private Location currentLocation;



    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }




}
