package command;

import World.Item;
import World.LoadMap;
import World.Location;
import World.Player;

import java.io.IOException;
import java.util.Scanner;

/**
 * Class for moving between rooms.
 */
public class Movement implements Command {


    private Scanner sc = new Scanner(System.in);
    private LoadMap map;



    private Player player;

    public Movement(Player player, LoadMap map) throws IOException {
        this.map = map;
        this.player = player;


    }


    /**
     * Method to get new Location when moving.
     * @param direction
     * @param currentLocation
     * @return
     */
    public Location getNewLocation(String direction, Location currentLocation) {
        Location newLocation = player.getCurrentLocation().getConnections().get(direction);

        if (newLocation != null) {
            return newLocation;
        } else {
            System.out.println("Nemůžeš jít tímto směrem.");
            return currentLocation;
        }
    }


    /**
     * Method with main logic for moving between rooms
     * Checks current location and connections between rooms
     * @throws IOException
     */
    @Override
    public String execute() throws IOException {
        try{
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



        }catch(NullPointerException e){
            System.out.println("Něco není správně inicializováno! "+ e.getMessage());

        }catch (Exception e){
            System.out.println("Nastala chyba: "+e.getMessage());
        }
        return "Co budeš dělat dál?";
    }

    /**
     * Method for checking right food name.
     */
    private void checkFoodInProstreno() {
        System.out.print("Zadej název jídla, které ti Karel uvařil: (pro pokračování do místnosti napiš cokoli jiného) ");
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

    /**
     * Mathod for checking spell to finish the game.
     */

    private void checkSpellForPortal() {
        System.out.print("Zadej kouzlo pro vstup do portálu: ");
        sc.nextLine();
        String spell = sc.nextLine().trim();

        if (spell.equalsIgnoreCase("Bizar konci svet se lame zpatky domu zmizte v jame")) {
            System.out.println("🎇🎇Kouzlo bylo správně napsáno! Konečně z toho blázince utečeš... Hra je splněna!🎇🎇");
            System.exit(0);
        } else {
            System.out.println("❌❌Kouzlo není spravně!! Jsi zpět na spawnu❌❌");
            player.setCurrentLocation(map.getSpawnRoom());
        }
    }
    @Override
    public boolean exit() {
        return false;
    }
}

