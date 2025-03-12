import World.LoadMap;
import World.Location;
import World.Player;
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
        //loadMap.loadItemFromTxt("Items.txt");
        player = new Player();



        Location spawnRoom= loadMap.getSpawnRoom();
        System.out.println("Před přiřazením: " + spawnRoom.getName());

        player.setCurrentLocation(spawnRoom);
        System.out.println("Po přiřazení: " + player.getCurrentLocation().getName());

    }

    public void startGame() throws IOException {
        Console console = new Console(player,backpack);
        console.inicialization();
        console.start();
    }
}


