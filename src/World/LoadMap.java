package World;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LoadMap {
    private HashMap<String, Location> rooms = new HashMap<>();

    private Location spawnRoom;


    public void loadFromTxt(String filename) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            String roomName = parts[0];
            rooms.put(roomName, new Location(roomName));
        }
        reader.close();

        reader = new BufferedReader(new FileReader(filename));
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            Location room = rooms.get(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                if (rooms.containsKey(parts[i])) {
                    room.connect(parts[i], rooms.get(parts[i]));
                }
            }
        }

        reader.close();
        spawnRoom = rooms.get("spawn");
        if (spawnRoom == null) {
            System.out.println("Chyba: Místnost 'spawn' nebyla nalezena.");
        }


    }

    public void loadItemFromTxt(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            String roomName = parts[0];
            Location room = rooms.get(roomName);

            if (room != null) {
                for (int i = 1; i < parts.length; i++) {
                    if (parts[i].equals("null")) continue;

                    String[] itemParts = parts[i].split(":", 7); // jméno, popis, dmg, usableLoc, vysledek, viditelny, wasUsed

                    String itemName = itemParts.length > 0 ? itemParts[0] : ""; // ternarni operatory mam naucene z internetu.
                    String itemDescription = itemParts.length > 1 ? itemParts[1] : "";

                    int damage = 0;
                    try {
                        damage = itemParts.length > 2 ? Integer.parseInt(itemParts[2].trim()) : 0;
                    } catch (NumberFormatException e) {
                        System.out.println("Chybný damage u itemu '" + itemName + "': " + itemParts[2]);
                    }

                    String usableLocation = itemParts.length > 3 ? itemParts[3] : null;
                    String usableText = itemParts.length > 4 ? itemParts[4] : null;
                    boolean isVisible = itemParts.length > 5 && itemParts[5].equalsIgnoreCase("true");
                    boolean wasUsed = itemParts.length>6 && itemParts[6].equalsIgnoreCase("true");
                    String requiredItemUsed = itemParts.length > 7 ? itemParts[7] : null;


                    Item item = new Item();
                    item.setName(itemName);
                    item.setDescription(itemDescription);
                    item.setDmg(damage);
                    item.setUsableLocation(usableLocation);
                    item.setUseActionResult(usableText);
                    item.setVisible(isVisible);
                    item.setWasUsed(wasUsed);
                    item.setRequiredItemUsed(requiredItemUsed);
                    room.addItem(item);
                }
            } else {
                System.out.println("Místnost '" + roomName + "' nebyla nalezena.");
            }
        }
        reader.close();
    }




    public void loadNPCFromTxt(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            String roomName = parts[0];
            Location room = rooms.get(roomName);
            if (room != null) {
                for (int i = 1; i < parts.length; i++) {
                    String[] itemParts = parts[i].split(":", 3); // jméno, popis, hp
                    String npcName = itemParts[0];
                    String npcDescription = itemParts.length > 1 ? itemParts[1] : "Žádný popis";
                    int hp = itemParts.length > 2 ? Integer.parseInt(itemParts[2]) : 0;

                    NPC npc = new NPC();
                    npc.setName(npcName);
                    npc.setDescription(npcDescription);
                    npc.setHP(hp);
                    room.addNPC(npc);
                }
            } else {
                System.out.println("Místnost '" + roomName + "' nebyla nalezena.");
            }
        }
        reader.close();
    }






    public Location getSpawnRoom() {
        return spawnRoom;
    }


}
