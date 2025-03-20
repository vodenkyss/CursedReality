package command;

import World.Item;
import World.LoadMap;
import World.Location;
import World.Player;

import java.io.IOException;
import java.util.Scanner;

/**
 * Class for picking up items from rooms.
 */

public class PickUp implements Command {


    private Player player;


    Scanner sc = new Scanner(System.in);


    public PickUp(Player player)  {
        this.player = player;
    }

    /**
     * Executes the command to pick up an item from the player's current location.
     * Prompts the player to enter the name of the item they want to collect.
     * @return which item you picked up
     * @throws IOException
     */
    @Override
    public String execute() throws IOException {
        System.out.println("Co chceš sebrat?");
        String itemName = sc.next().toLowerCase();
        if (player.getCurrentLocation().hasItem(itemName)) {
            Item item = player.getCurrentLocation().getItem(itemName);
            if (item.isVisible()) {
                player.getBackpack().addToBackpack(item);
                player.getCurrentLocation().removeItem(itemName);
                return "Sebral jsi item: " + itemName+ " Pro více info se na něj podívej v batohu";
            } else {
                return "Tento item ještě nemůžeš sebrat!";

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
