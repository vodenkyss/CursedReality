package command;

import World.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Backpack implements Command{

    ArrayList<Item>items = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    @Override
    public String execute() throws IOException {
        if(items.isEmpty()){
            return "nic tu neni!";
        }
        else{
            for (Item item: items){
                System.out.println(item.getName());
            }
        }
        return "batoh je zavreny";
    }

    public boolean addToBackpack(Item item){
        if(item != null){
            items.add(item);
            return true;
        }
        return false;
    }

    public boolean removeItem(Item item){
        for (int i = 0; i < items.size(); i++) {
            if(item == items.get(i)){
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean hasItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
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
