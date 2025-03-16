package World;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LoadMap {
    HashMap<String, Location> rooms = new HashMap<>();

    Location spawnRoom;


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


    /*
    public void loadItemFromTxt(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Items.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            String roomName = parts[0];

            Location room = rooms.get(roomName);
            if (room != null) {
                for (int i = 1; i < parts.length; i++) {
                    String itemName = parts[i];
                    if (itemName != null && !itemName.isEmpty()) {
                        Item item = new Item();
                        item.setName(itemName);
                        room.addItem(item);
                    }
                }
            } else {
                System.out.println("Místnost '" + roomName + "' nebyla nalezena.");
            }
        }
        reader.close();
    }

     */

    public void loadItemFromTxt(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            String roomName = parts[0];
            Location room = rooms.get(roomName);
            if (room != null) {
                for (int i = 1; i < parts.length; i++) {
                    String[] itemParts = parts[i].split(":", 3); // jméno, popis, damage
                    String itemName = itemParts[0];
                    String itemDescription = itemParts.length > 1 ? itemParts[1] : "Žádný popis";
                    int damage = itemParts.length > 2 ? Integer.parseInt(itemParts[2]) : 0;

                    Item item = new Item();
                    item.setName(itemName);
                    item.setDescription(itemDescription);
                    item.setDmg(damage);

                    room.addItem(item);
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
