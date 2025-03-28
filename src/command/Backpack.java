package command;

import World.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class Backpack implements Command {

    private ArrayList<Item> items = new ArrayList<>();

    private Scanner sc = new Scanner(System.in);

    /**
     * Method that shows you your backpack.
     * Checks if backpack is not empty
     * @return String of items in backpack or Item and its description
     */
    public String execute() {
        System.out.print("Zadej název předmětu nebo nech prázdné pro zobrazení celého batohu: ");
        String input = sc.nextLine().trim().toLowerCase();

        if (input.isEmpty()) {
            if (items.isEmpty()) {
                return "Batoh je prázdný.";
            } else {
                StringBuilder sb = new StringBuilder("V batohu máš:\n");
                for (Item item : items) {
                    sb.append("- ").append(item.getName()).append("\n");
                }
                return sb.toString();
            }
        } else {
            for (Item item : items) {
                if (item.getName().equalsIgnoreCase(input)) {
                    return "Informace o předmětu '" + item.getName() + "':\n" +
                            "Popis: " + item.getDescription() + "\n" +
                            "Damage: " + item.getDmg();
                }
            }
            return "Předmět '" + input + "' v batohu není.";
        }
    }

    /**
     * Method that gives item to backpack if not null
     * @param item
     */
    public void addToBackpack(Item item) {
        if (item != null) {
            items.add(item);

        }
    }




    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public String toString() {
        return "Backpack{" +
                "items=" + items +
                '}';
    }
}
