package command;

import World.Item;

import java.io.IOException;

public class Use implements Command  {

    private Backpack backpack;

    private String itemName;



    public Use(Backpack backpack, String itemName) {
        this.backpack = backpack;
        this.itemName = itemName;
    }

    public Use() {

    }

    @Override
    public String execute() throws IOException {
        Item itemToUse = backpack.getItem(itemName);

        if (itemToUse != null) {
            //neco
            return "Použil jsi: " + itemToUse.getName();
        } else {
            return "Předmět " + itemName + " není v batohu!";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
