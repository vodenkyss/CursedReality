package command;

import World.Item;
import World.Location;
import World.Player;

import java.io.IOException;
import java.util.Scanner;

public class Use implements Command {


    private Backpack backpack;
    private Player player;
    private Scanner sc = new Scanner(System.in);

    public Use(Backpack backpack, Player player) {
        this.backpack = backpack;
        this.player = player;
    }

    public Use() {
    }

    @Override
    public String execute() {
        System.out.println("Co chceš použít?");
        String itemName = sc.nextLine().trim().toLowerCase();

        Item itemToUse = backpack.getItem(itemName);
        Location currentLocation = player.getCurrentLocation();

        if (itemToUse == null) {
            return "Tento předmět nemáš v batohu!";
        }

        if (itemToUse.getUsableLocation() != null &&
                itemToUse.getUsableLocation().equalsIgnoreCase(currentLocation.getName())) {
            return itemToUse.getUseActionResult();
        }

        return "Použil jsi " + itemToUse.getName() + ", ale nic se nestalo...";
    }


    @Override
    public boolean exit() {
        return false;
    }
}

