import java.io.*;

public class EsportsManagementApp {
    public static void main(String[] args){
        // Reading the gamers file and putting them in an array.
        File gamersFile = new File("./src/gamers.csv");
        Gamer[] gamers = new Gamer[FileIO.numOfLines(gamersFile)];
        int i = 0;
        for (String[] arr: FileIO.readLines(gamersFile)){
            if (arr != null){
                Gamer gamer = new Gamer(arr);
                gamers[i++] = gamer;
            } 
        }
        
        // Reading the games file and putting them in an array.
        File gamesFile = new File("./src/games.csv");
        Game[] games = new Game[FileIO.numOfLines(gamesFile)];
        int j = 0;
        for (String[] arr: FileIO.readLines(gamesFile)){
            if (arr != null){    
                Game game = new Game(arr);
                games[j++] = game; 
            }
        }
    }
}
