package command;

import World.Item;
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
        this.map = map;
        this.player = player;


    }


    public Location getNewLocation(String direction, Location currentLocation) {
        Location newLocation = player.getCurrentLocation().getConnections().get(direction);

        if (newLocation != null) {
            return newLocation;
        } else {
            System.out.println("Nemůžeš jít tímto směrem.");
            return currentLocation; //
        }
    }


    private void checkSpellForPortal() {
        System.out.print("Zadej kouzlo pro vstup do portálu: ");
        sc.nextLine();
        String spell = sc.nextLine().trim();

        if (spell.equalsIgnoreCase("Bizar konci svet se lame zpatky domu zmizte v jame")) {
            System.out.println("🎇🎇Kouzlo bylo správně napsáno! Hra je splněna.🎇🎇");
            System.exit(0);
        } else {
            System.out.println("❌❌Kouzlo neni spravne!! Jsi zpet na spawn❌❌");
            player.setCurrentLocation(map.getSpawnRoom());
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

        switch (direction) {
            case "portal":
                if (player.getCurrentLocation() == map.getSpawnRoom()) {
                    checkSpellForPortal();
                } else {
                    System.out.println("Musíš se nejprve vrátit na Spawn, než půjdeš do portálu.");
                }
                break;

            case "spawn":
                player.setCurrentLocation(map.getSpawnRoom());
                System.out.println("Vrátil ses na Spawn.");
                break;

            case "prostreno":
                if (player.getCurrentLocation() == map.getSpawnRoom() && map.getSpawnRoom().getConnections().containsKey("prostreno")) {
                    Location newLocation = getNewLocation("prostreno", player.getCurrentLocation());
                    player.setCurrentLocation(newLocation);
                    System.out.println("Přesunuto do: " + player.getCurrentLocation().getName());
                    checkFoodInProstreno();
                } else {
                    System.out.println("Musíš se nejprve vrátit na Spawn, než půjdeš do prostreno.");
                }
                break;


            default:
                if (player.getCurrentLocation() == map.getSpawnRoom()) {
                    if (map.getSpawnRoom().getConnections().containsKey(direction)) {
                        Location newLocation = getNewLocation(direction, player.getCurrentLocation());
                        player.setCurrentLocation(newLocation);
                        System.out.println("Přesunuto do: " + player.getCurrentLocation().getName());
                    } else {
                        System.out.println("Tato místnost neexistuje.");
                    }
                } else {
                    System.out.println("Musíš se nejprve vrátit na Spawn, než půjdeš jinam.");
                }
                break;
        }


        return "Co budes delat dal?";
    }

    private void checkFoodInProstreno() {
        System.out.print("Zadej název jídla, které ti Karel uvařil: (pro pokracovani do mistnosti napis cokoli jineho) ");
        sc.nextLine();
        String foodName = sc.nextLine().trim();

        if (foodName.equalsIgnoreCase("koprovka")) {
            System.out.println("🟢🟢Jo fakt to byla koprovka! Můžeš pokračovat.🟢🟢");

            for (Item item : player.getCurrentLocation().getItems()) {
                if (!item.isVisible()){
                    item.setVisible(true);
                    System.out.println("Předmět " + item.getName() + " se stal viditelným.");
                }

            }
        } else {
            System.out.println("❌❌Jídlo není správné! Zkus to znovu.❌❌");
        }
    }


    @Override
    public boolean exit() {
        return false;
    }
}

