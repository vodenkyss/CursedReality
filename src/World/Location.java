package World;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Location {

    private String name;

    ArrayList<Item> items= new ArrayList<>();

    HashMap<String, Location> connections = new HashMap<>();


    public ArrayList<Item> getItems() {
        return items;
    }


    public Location() throws IOException {
    }


    Location(String name) throws IOException {
        this.name = name;
    }

    void connect(String roomName, Location room) {
        connections.put(roomName, room);
    }

    public boolean hasItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void removeItem(String itemName) {
        items.removeIf(item -> item.getName().equals(itemName));
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Location> getConnections() {
        return connections;
    }

    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void addItem(Item item){
        items.add(item);
    }

    @Override
    public String toString() {
        return "Location{" +
                "items=" + items +
                '}';
    }
}
