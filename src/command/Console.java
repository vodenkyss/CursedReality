package command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Console {

    private boolean exit = false;
    private HashMap<String, Command> map = new HashMap<>();

    Scanner sc = new Scanner(System.in);

    public void inicialization() throws IOException {
        map.put("jdi", new Movement());
        map.put("konec", new Exit());
        map.put("batoh", new Backpack());
        map.put("vezmi", new PickUp());
        map.put("pomoc", new Help(map));

    }

    public void doCommand() throws IOException {
        System.out.print(">>");
        String command = sc.nextLine();
        command = command.trim();
        command = command.toLowerCase();
        if (map.containsKey(command)) {
            System.out.println(">> " + map.get(command).execute());
        } else {
            System.out.println(">> Cos to napsal? Zkus to znova..");
        }
    }

    public void start() throws IOException {
        inicialization();
        //String text = Text.loadFile();
        //System.out.println(text);
        try {
            do {
                doCommand();
            } while (!exit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String keys() {
        for (int i = 0; i < map.size(); i++) {
           return String.valueOf(map.get(i));

        }
        return null;
    }

}
