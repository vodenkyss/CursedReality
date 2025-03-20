import World.Player;
import command.Console;
import command.Movement;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try{
            Game g = new Game();
            g.startGame();

        }catch (IOException e){
            System.out.println("Chyba při načítání hry: "+ e.getMessage());
        }catch (Exception e){
            System.out.println("Nastala chyba: "+ e.getMessage());
        }
    }
}