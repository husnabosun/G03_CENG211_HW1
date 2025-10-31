import java.io.*;
import java.util.Arrays;

public class EsportsManagementApp {
    public static void main(String[] args){
        // Reading the gamers file and putting them in an array.(Just for example, code block can be added to its original spot later.)
        File gamersFile = new File("./src/gamers.csv");
        Gamer[] gamers = Gamer.returnObjectArray(gamersFile);
        
        // Reading the games file and putting them in an array.(Just for example, code block can be added to its original spot later.)
        File gamesFile = new File("./src/games.csv");
        Game[] games = Game.returnObjectArray(gamesFile);
        
        // Just for example not included in the code.
        System.out.println(Arrays.deepToString(gamers));
        System.out.println("-----------------------------------");
        System.out.println(Arrays.deepToString(games));
    }
}
