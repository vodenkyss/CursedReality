package command;

import World.Location;
import World.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Console {

    private boolean exit = false;
    private HashMap<String, Command> map = new HashMap<>();

    private Backpack backpack;

    private Player player;

    private Location currentLocation;

    public Console(Player player, Backpack backpack) {
        this.backpack = backpack;
        this.player= player;
    }

    Scanner sc = new Scanner(System.in);

    public void inicialization() throws IOException {
        map.put("jdi", new Movement(player));
        map.put("konec", new Exit());
        map.put("batoh", new Backpack());
        map.put("vezmi", new PickUp(backpack,player.getCurrentLocation()));
        map.put("pomoc", new Help(map));
        map.put("pouzij", new Use());
        map.put("prozkoumej", new Look(player));

    }

    public void doCommand() throws IOException {
        Location currentLocation = player.getCurrentLocation();
        System.out.println("Aktuální pozice: " + currentLocation.getName());
        System.out.print(">>");
        String command = sc.nextLine();
        command = command.trim();
        command = command.toLowerCase();
        if (map.containsKey(command)) {
            System.out.println(">> " + map.get(command).execute());
        } else {
            System.out.println(">> Cos to napsal? Zkus to znova..");
        }

    }

    public void start() throws IOException {
        inicialization();
        //String text = Text.loadFile();
        //System.out.println(text);
        try {
            do {
                doCommand();
            } while (!exit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
