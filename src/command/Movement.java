package command;

import World.LoadMap;
import World.Location;
import World.Player;

import java.io.IOException;
import java.util.Scanner;

public class Movement implements Command {


    private Scanner sc = new Scanner(System.in);
    private LoadMap map;


    private Player player;

    public Movement(Player player, LoadMap map) throws IOException {
        this.map=map;
        this.player = player;
        //map.loadItemFromTxt("Items.txt");
        //map.loadNPCFromTxt("npc.txt");

    }



    /*
    @Override
    public String execute() throws IOException {
       // Location currentLocation = player.getCurrentLocation();

        System.out.println("Aktuální pozice: " + player.getCurrentLocation().getName());
        System.out.println("****************************");

        if (player.getCurrentLocation() == map.getSpawnRoom()) {
            System.out.println("Odsud můžeš jít do: " + String.join(", ", map.getSpawnRoom().getConnections().keySet()));
        } else {
            System.out.println("Můžeš se vrátit pouze na Spawn.");
        }

        System.out.print("Kam chceš jít? ");
        String direction = sc.next().toLowerCase();

        if (player.getCurrentLocation() == map.getSpawnRoom()) {
            if (map.getSpawnRoom().getConnections().containsKey(direction)) {
                Location newLocation = getNewLocation(direction, player.getCurrentLocation());
                player.setCurrentLocation(newLocation);
                System.out.println("Přesunuto do: " + player.getCurrentLocation().getName());
            } else {
                System.out.println("Tato místnost neexistuje.");
            }
        } else if (direction.equals("spawn")) {
            player.setCurrentLocation(map.getSpawnRoom());
            System.out.println("Vrátil ses na Spawn.");
        } else {
            System.out.println("Musíš se nejprve vrátit na Spawn, než půjdeš jinam.");
        }

        return "Co budes delat dal?";
    }


     */

    public Location getNewLocation(String direction, Location currentLocation) {
        //currentLocation=player.getCurrentLocation(); //zkouska
        Location newLocation = player.getCurrentLocation().getConnections().get(direction);

        if (newLocation != null) {
            return newLocation;
        } else {
            System.out.println("Nemůžeš jít tímto směrem.");
            return currentLocation; //
        }
    }



    @Override
    public String execute() throws IOException {
        System.out.println("Aktuální pozice: " + player.getCurrentLocation().getName());
        System.out.println("****************************");

        if (player.getCurrentLocation() == map.getSpawnRoom()) {
            System.out.println("Odsud můžeš jít do: " + String.join(", ", map.getSpawnRoom().getConnections().keySet()));
        } else {
            System.out.println("Můžeš se vrátit pouze na Spawn.");
        }

        System.out.print("Kam chceš jít? ");
        String direction = sc.next().toLowerCase();

        if (player.getCurrentLocation() == map.getSpawnRoom()) {
            if (map.getSpawnRoom().getConnections().containsKey(direction)) {
                if (direction.equals("portal")) {
                    System.out.print("Zadej kouzlo pro vstup do portálu: ");
                    sc.nextLine();
                    String spell = sc.nextLine().trim();
                    if (spell.equalsIgnoreCase("Bizar konci svet se lame zpatky domu zmizte v jame")) {
                        System.out.println("Kouzlo bylo správně napsáno! Hra je splněna.");
                        System.exit(0);
                    } else {
                        System.out.println("Kouzlo neni spravne!! Jsi zpet na spawn");
                        player.setCurrentLocation(map.getSpawnRoom());
                    }
                } else {
                    Location newLocation = getNewLocation(direction, player.getCurrentLocation());
                    player.setCurrentLocation(newLocation);
                    System.out.println("Přesunuto do: " + player.getCurrentLocation().getName());
                }
            } else {
                System.out.println("Tato místnost neexistuje.");
            }
        } else if (direction.equals("spawn")) {
            player.setCurrentLocation(map.getSpawnRoom());
            System.out.println("Vrátil ses na Spawn.");
        } else {
            System.out.println("Musíš se nejprve vrátit na Spawn, než půjdeš jinam.");
        }

        return "Co budes delat dal?";
    }


    @Override
    public boolean exit() {
        return false;
    }
}

