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


    public PickUp(Backpack backpack, Player player) throws IOException {
        this.backpack = backpack;
        this.player = player;
    }

    @Override
    public String execute() throws IOException {
        System.out.println("Co chces sebrat?");
        String itemName = sc.next().toLowerCase();
        if (player.getCurrentLocation().hasItem(itemName)) {
            Item item = player.getCurrentLocation().getItem(itemName);

            backpack.addToBackpack(item);
            System.out.println(backpack.toString());
            player.getCurrentLocation().removeItem(itemName);
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
