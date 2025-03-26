package World;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for declaration of location.
 */
public class Location {

    private String name;

    private ArrayList<Item> items= new ArrayList<>();
    private ArrayList<NPC> npcs= new ArrayList<>();

    HashMap<String, Location> connections = new HashMap<>();


    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<NPC> getNPCs(){
        return npcs;
    }


    public Location() throws IOException {
    }


    public Location(String name) throws IOException {
        this.name = name;
    }

    /**
     * Connects room with the accessible rooms.
     * @param roomName
     * @param room
     */
    void connect(String roomName, Location room) {
        connections.put(roomName, room);
    }

    /**
     * Checks if the location contains item
     * @param itemName
     * @return true or false
     */
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

    /**
     * Gets item from the room
     * @param itemName
     * @return item
     */
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

    public void addNPC(NPC npc){
        npcs.add(npc);
    }

    public boolean hasNpc(String npcName) {
        for (NPC npc : npcs) {
            if (npc.getName().equalsIgnoreCase(npcName)) {
                return true;
            }
        }
        return false;
    }

    public NPC getNpc(String npcName) {
        for (NPC npc : npcs) {
            if (npc.getName().equalsIgnoreCase(npcName)) {
                return npc;
            }
        }
        return null;
    }




    @Override
    public String toString() {
        return "Location{" +
                "items=" + items +
                '}';
    }
}
