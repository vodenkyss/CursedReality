package command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Console  {

    private boolean exit = false;
    private HashMap<String, Command> map = new HashMap<>();

    Scanner sc = new Scanner(System.in);

    public void inicialization() throws IOException {
        map.put("jdi", new Movement());

    }

    public void doCommand() throws IOException {
        System.out.print(">>");
        String command = sc.nextLine();
        command = command.trim();
        command = command.toLowerCase();
        if(map.containsKey(command)){
            System.out.println(">> "+map.get(command).execute());
        }else{
            System.out.println(">> Nondefined command");
        }
    }

    public void start() throws IOException {
        inicialization();
        //String text = Text.loadFile();
        //System.out.println(text);
        try{
            do{
                doCommand();
            }while(!exit);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
