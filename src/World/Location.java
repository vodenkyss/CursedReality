package World;

import java.util.ArrayList;
import java.util.HashMap;

public class Location {

    private String name;

    private ArrayList<Item> items= new ArrayList<>();


    public ArrayList<Item> getItems() {
        return items;
    }


    public Location() {
    }
    HashMap<String, Location> connections = new HashMap<>();

    Location(String name) {
        this.name = name;
    }

    void connect(String roomName, Location room) {
        connections.put(roomName, room);
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Location> getConnections() {
        return connections;
    }
}
