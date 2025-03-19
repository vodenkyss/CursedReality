package command;

import World.Item;
import World.Location;
import World.NPC;
import World.Player;

import java.io.IOException;

public class Look implements Command{


    private Player player;

    public Look(Player player) {
        this.player = player;
    }

    @Override
    public String execute() throws IOException {
        if (player.getCurrentLocation() == null) {
            return "Hráč nemá nastavenou aktuální lokaci.";
        }
        Location currentLocation = player.getCurrentLocation();
        StringBuilder output = new StringBuilder();
        output.append("Nacházíš se v místnosti: ").append(currentLocation.getName()).append("\n");
        output.append("Itemy v místnosti:\n");


        if (currentLocation.getItems().isEmpty()) {
            output.append("Žádné itemy v této místnosti.\n");
        } else {
            for (Item item : currentLocation.getItems()) {
                output.append("- ").append(item.getName()).append("\n");
            }
        }

        output.append("Postavy v místnosti:\n");
        if (currentLocation.getNPCs().isEmpty()) {
            output.append("Žádná postava v této místnosti.\n");
        } else {
            for (NPC npc : currentLocation.getNPCs()) {
                output.append("- ").append(npc.getName()).append(": ").append(npc.getDescription()).append("HP: ").append(npc.getHP()).append("\n");
            }
        }


        return output.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
