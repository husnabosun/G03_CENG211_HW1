import java.util.Arrays;

public class PointsBoard {
    private final Gamer[] gamers;
    private final int[] totalPoints;
    private final double[] avrgPoints;
    private final MedalType[] medals;

    private static final int GOLDEN_LOWER_BOUND = 4400;
    private static final int SILVER_LOWER_BOUND = 3800;
    private static final int BRONZE_LOWER_BOUND = 3500;


    public PointsBoard(Gamer[] gamers){
        this.gamers = gamers;
        int numOfGamers = gamers.length;

        totalPoints = new int[numOfGamers];
        avrgPoints = new double[numOfGamers];
        medals = new MedalType[numOfGamers];

    }

    public void computeGamerStats(MatchManagement matches){
        int i, j;
        int numOfGamers = gamers.length;

        for(i = 0; i < numOfGamers ; i++ ){
            int totalPointsPerGamer = 0;
            double avrgPointsPerGamer = 0;
            MedalType gamerMedal;

            for (j = 0; j < Gamer.MATCH_COUNT_PER_GAMER; j++ ){
                totalPointsPerGamer += matches.getMatch(i, j).getMatchPoints();
            }

            totalPoints[i] = totalPointsPerGamer;
            avrgPoints[i] = calcAvrgPoints(totalPointsPerGamer);
            medals[i] = decideMedal(totalPointsPerGamer);

        }
    }

    private MedalType decideMedal(int totalPoints){
        if (totalPoints >= GOLDEN_LOWER_BOUND){
            return MedalType.GOLD;
        }
        else if(totalPoints >= SILVER_LOWER_BOUND){
            return MedalType.SILVER;
        }
        else if(totalPoints >= BRONZE_LOWER_BOUND){
            return MedalType.BRONZE;
        }
        else{
            return MedalType.NONE;
        }
    };

    private double calcAvrgPoints(int totalPoints){
        int matchCountPerGamer = Gamer.MATCH_COUNT_PER_GAMER;
        return (double) totalPoints / matchCountPerGamer;
    }

    public MedalType[] getMedals(){
        return medals;
    }
    public int[] getTotalPoints(){
        return Arrays.copyOf(totalPoints, totalPoints.length);
    }
    public double[] getAvrgPoints(){
        return Arrays.copyOf(avrgPoints, avrgPoints.length);
    }
    public int getTotalPointsAt(int index) {
        return totalPoints[index];
    }

    public double getAveragePointsAt(int index) {
        return avrgPoints[index];
    }

    public MedalType getMedalAt(int index) {
        return medals[index];
    }

    public int[] countMedals(){
        int goldCount = 0, silverCount = 0, bronzeCount = 0, noneCount = 0;
        for (MedalType  medal : medals){
            switch (medal) {
                case GOLD: goldCount++; break;
                case SILVER: silverCount++; break;
                case BRONZE: bronzeCount++; break;
                case NONE: noneCount++; break;
            }
        }
        return new int[]{goldCount, silverCount, bronzeCount, noneCount};
    }
    public int getHighestScorerIndex() {
        return findIndexOfMax(totalPoints);
    }
    public static int findIndexOfMax(int[] arr){
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int max = arr[0];
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

}
