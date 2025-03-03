package World;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LoadMap {
    HashMap<String, Location> rooms = new HashMap<>();

    private int start = 0;
    private int currentPosition = start;

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
    }

    public Location getCurrentPosition(){
        return rooms.get(currentPosition);
    }

    public Location getSpawnRoom() {
        return spawnRoom;
    }

}
