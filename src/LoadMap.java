import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LoadMap {
    HashMap<String, Location> rooms = new HashMap<>();

    Location spawnRoom;

    void loadFromTxt(String filename) throws IOException {
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

    }

}
