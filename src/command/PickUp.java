package command;

import World.Item;
import World.LoadMap;
import World.Location;
import World.Player;

import java.io.IOException;
import java.util.Scanner;

public class PickUp implements Command {


    private Player player;


    Scanner sc = new Scanner(System.in);


    public PickUp(Player player) throws IOException {
        this.player = player;
    }

    @Override
    public String execute() throws IOException {
        System.out.println("Co chces sebrat?");
        String itemName = sc.next().toLowerCase();
        if (player.getCurrentLocation().hasItem(itemName)) {
            Item item = player.getCurrentLocation().getItem(itemName);
            if (item.isVisible()) {
                player.getBackpack().addToBackpack(item);
                player.getCurrentLocation().removeItem(itemName);
                return "Sebral jsi item: " + itemName;
            } else {
                return "Tento item jeste nemuzes sebrat!";

            }
        } else {
            return "Item " + itemName + " není v této místnosti.";
        }

    }

    @Override
    public boolean exit() {
        return false;
    }
}
