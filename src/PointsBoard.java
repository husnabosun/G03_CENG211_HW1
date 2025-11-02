import java.util.Arrays;

/**
 * PointsBoard Class Contract:
 * Responsibility: To centrally store, calculate, and manage the seasonal performance data
 * (Total Points, Average Points Per Match, Medal) for all gamers. It also provides access
 * to this data for calculations and queries (for the Query class).
 */

public class PointsBoard {
    private final Gamer[] gamers;
    private final int[] totalPoints;
    private final double[] avrgPoints;
    private final MedalType[] medals;

    // Medal score boundaries (Total Score) specified in the homework
    private static final int GOLDEN_LOWER_BOUND = 4400;
    private static final int SILVER_LOWER_BOUND = 3800;
    private static final int BRONZE_LOWER_BOUND = 3500;


    // Constructor Method
    // Initializes the PointsBoard and allocates the necessary space for all statistics arrays.
    public PointsBoard(Gamer[] gamers){
        this.gamers = gamers;
        int numOfGamers = gamers.length;

        totalPoints = new int[numOfGamers];
        avrgPoints = new double[numOfGamers];
        medals = new MedalType[numOfGamers];

    }

    // Calculates the seasonal statistics (Total Points, Average, Medal) for each gamer
    // using Match Points in MatchManagement
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

    //Determines the medal to be assigned to the gamer based on their Total Points.
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

    // Calculates the Average Points per match
    private double calcAvrgPoints(int totalPoints){
        int matchCountPerGamer = Gamer.MATCH_COUNT_PER_GAMER;
        return (double) totalPoints / matchCountPerGamer;
    }

    // Getters (Used by the Query class to access statistics)
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

    // Counts the medal distribution (for counts and percentages)for Query
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

    // Finds the index of the gamer with the highest total score for Query
    public int getHighestScorerIndex() {
        return findIndexOfMax(totalPoints);
    }

    // Helper method: Finds the index of the maximum value in an array.
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
