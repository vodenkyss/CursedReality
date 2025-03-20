package command;

import World.Location;
import World.NPC;
import World.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class for loading and solving communication with npc
 */

public class Speak implements Command {

    private Player player;
    private HashMap<String, String> npcDialogues = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    public Speak(Player player) throws IOException {
        this.player = player;
        loadDialogues("dialogues.txt");
    }

    /**
     * Method for readinf text file with dialogues
     * @param filename name of the text file
     * @throws IOException
     */
    private void loadDialogues(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";", 2);
            if (parts.length == 2) {
                npcDialogues.put(parts[0].trim().toLowerCase(), parts[1].trim());
            }
        }
        reader.close();
    }

    /**
     * Method for communication with npc
     * @return npc dialogue
     */
    @Override
    public String execute() {
        System.out.println("S kým chceš mluvit?");
        String npcName = sc.nextLine().toLowerCase();

        if (player.getCurrentLocation().hasNpc(npcName)) {
           NPC npc = player.getCurrentLocation().getNpc(npcName);

            System.out.println("Mluvis s: "+ npc.getName());

            return npcDialogues.get(npcName);
        } else {
            return "Nikdo jménem " + npcName + " tu není."+ "Nebo si zkontroluj zda oslovujes spravnym jmenem!";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}

