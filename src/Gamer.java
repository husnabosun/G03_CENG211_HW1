import java.io.*;

public class Gamer {
    private final int gamerID;
    private final String gamerNickname;
    private final String gamerName;
    private final String gamerPhone;
    private final int gamerExprienceYears;

    public Gamer(String[] gamerArray){
        gamerID = Integer.parseInt(gamerArray[0]);
        gamerNickname = gamerArray[1];
        gamerName = gamerArray[2];
        gamerPhone = gamerArray[3];
        gamerExprienceYears = Integer.parseInt(gamerArray[4]); 
    }

    public Gamer(Gamer gamer){ //Copy constructor.
        this.gamerID = gamer.gamerID;
        this.gamerNickname = gamer.gamerNickname;
        this.gamerName = gamer.gamerName;
        this.gamerPhone = gamer.gamerPhone;
        this.gamerExprienceYears = gamer.gamerExprienceYears;
    }

    public static Gamer[] returnObjectArray(File file){
        Gamer[] gamers = new Gamer[FileIO.numOfLines(file)];
        int i = 0;
        for (String[] arr: FileIO.readLines(file)){
            if (arr != null){
                Gamer gamer = new Gamer(arr);
                gamers[i++] = gamer;
            } 
        }

        return gamers;
    }

    public int getGamerID(){
        return gamerID;
    }

    public String getGamerNickname(){
        return gamerNickname;
    }

    public String getGamerName(){
        return gamerName;
    }

    public String getGamerPhone(){
        return gamerPhone;
    }

    public int getGamerExperienceYears(){
        return gamerExprienceYears;
    }

    @Override
    public String toString(){
        return "Gamer: ID:" + String.valueOf(gamerID) + " ,Nickame:" + gamerNickname + " ,Name:" + gamerName + " ,Phone:" + gamerPhone + " ,Experience Years:" + String.valueOf(gamerExprienceYears);
    }
}