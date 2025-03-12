package command;

import World.Item;
import World.LoadMap;
import World.Location;
import World.Player;

import java.io.IOException;
import java.util.Scanner;

public class PickUp implements Command {

    private Backpack backpack;

    private LoadMap map = new LoadMap();
    private Location currentLocation;

    private Player player;




    Scanner sc = new Scanner(System.in);


    public PickUp(Backpack backpack, Location currentLocation) throws IOException {
        this.backpack = backpack;
        this.currentLocation= currentLocation;
    }

    @Override
    public String execute() throws IOException {

        String itemName = sc.next().toLowerCase();
        if (player.getCurrentLocation().hasItem(itemName)) {
            Item item = player.getCurrentLocation().getItem(itemName);
            backpack.addToBackpack(item);
            player.getCurrentLocation().removeItem(itemName); // Odeberte item z místnosti
            return "Sebral jsi item: " + itemName;
        } else {
            return "Item " + itemName + " není v této místnosti.";
        }

    }

    @Override
    public boolean exit() {
        return false;
    }
}
