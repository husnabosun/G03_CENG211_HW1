public class Game {
    private final int gameID;
    private final String gameName;
    private final int gameBasePoint;

    public Game(String[] gameArray){
        gameID = Integer.parseInt(gameArray[0]);
        gameName = gameArray[1];
        gameBasePoint = Integer.parseInt(gameArray[2]);
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