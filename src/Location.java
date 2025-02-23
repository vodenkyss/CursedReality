import java.util.HashMap;

public class Location {

    private String name;



    public Location() {
    }
    HashMap<String, Location> connections = new HashMap<>();

    Location(String name) {
        this.name = name;
    }

    void connect(String roomName, Location room) {
        connections.put(roomName, room);
    }


}
