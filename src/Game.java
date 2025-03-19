import World.LoadMap;
import World.Location;
import World.Player;
import World.Text;
import command.Backpack;
import command.Console;
import command.Movement;

import java.io.IOException;

public class Game {

    private Backpack backpack;
    private LoadMap loadMap;
    private Player player;


    public Game() throws IOException {
        backpack = new Backpack();
        loadMap = new LoadMap();
        loadMap.loadFromTxt("Map.txt");
        loadMap.loadItemFromTxt("Items.txt");
        loadMap.loadNPCFromTxt("npc.txt");
        player = new Player();

        player.setBackpack(backpack);
        Location spawnRoom = loadMap.getSpawnRoom();
        player.setCurrentLocation(spawnRoom);
    }

    public void startGame() throws IOException {
        Console console = new Console(player, backpack, loadMap);
        console.inicialization();
        console.start();
    }
}


