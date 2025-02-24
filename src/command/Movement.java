package command;

import World.LoadMap;
import World.Location;

import java.io.IOException;
import java.util.Scanner;

public class Movement implements Command {


    private Scanner sc = new Scanner(System.in);
    private LoadMap map = new LoadMap();
    private Location currentLocation;

    public Movement() throws IOException {
        map.loadFromTxt("Map.txt");
        currentLocation = map.getSpawnRoom();
    }

    @Override
    public String execute() throws IOException {
        System.out.println("Aktuální pozice: " + currentLocation.getName());

        if (currentLocation == map.getSpawnRoom()) {
            System.out.println("Odsud můžeš jít do: " + String.join(", ", map.getSpawnRoom().getConnections().keySet()));
        } else {
            System.out.println("Můžeš se vrátit pouze na Spawn.");
        }

        System.out.print("Kam chceš jít? ");
        String direction = sc.next();

        if (currentLocation == map.getSpawnRoom()) {
            if (map.getSpawnRoom().getConnections().containsKey(direction)) {
                currentLocation = map.getSpawnRoom().getConnections().get(direction);
                System.out.println("Přesunuto do: " + currentLocation.getName());
            } else {
                System.out.println("Tato místnost neexistuje.");
            }
        } else if (direction.equals("Spawn")) {
            currentLocation = map.getSpawnRoom();
            System.out.println("Vrátil ses na Spawn.");
        } else {
            System.out.println("Musíš se nejprve vrátit na Spawn, než půjdeš jinam.");
        }

        return null;
    }

    @Override
    public boolean exit() {
        return false;
    }
}

