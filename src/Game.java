import java.io.File;

public class Game {
    private final int gameID;
    private final String gameName;
    private final int gameBasePoint;

    public Game(String[] gameArray){
        gameID = Integer.parseInt(gameArray[0]);
        gameName = gameArray[1];
        gameBasePoint = Integer.parseInt(gameArray[2]);
    }

    public Game(Game game){ //Copy constructor.
        this.gameID = game.gameID;
        this.gameName = game.gameName;
        this.gameBasePoint = game.gameBasePoint;
    }

    public static Game[] returnObjectArray(File file){
        Game[] games = new Game[FileIO.numOfLines(file)];
        int i = 0;
        for (String[] arr: FileIO.readLines(file)){
            if (arr != null){
                Game game = new Game(arr);
                games[i++] = game;
            } 
        }

        return games;
    }

    public int getGameID(){
        return gameID;
    }

    public String getGameName(){
        return gameName;
    }

    public int gameBasePoint(){
        return gameBasePoint;
    }

    @Override
    public String toString(){
        return "Game: ID:" + String.valueOf(gameID) + " ,Name:" + gameName + " ,Base Points per Round:" + String.valueOf(gameBasePoint);
    }
}