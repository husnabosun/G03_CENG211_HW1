public class Query {
    private MatchManagement matchManagement;
    private PointsBoard pointsBoard;
    private Gamer[] gamers;

    public Query(MatchManagement matchManagement, PointsBoard pointsBoard, Gamer[] gamers) {
        this.matchManagement = matchManagement;
        this.pointsBoard = pointsBoard;
        this.gamers = gamers;
    }

    public void printHighestScoringMatch(){
        Match match = matchManagement.findHighestScoringMatch();

        System.out.println("== HIGHEST-SCORING MATCH ==");
        System.out.println("Match ID: " + match.getMatchId());
        System.out.println("Games: [" + formatGames(match.getGames()) + "]");
        System.out.println("Rounds: [" + formatRounds(match.getRounds()) + "]");
        System.out.println("Raw Points: " + match.getRawPoints());
        System.out.println("Skill Points: " + match.getSkillPoints());
        System.out.println("Bonus Points: " + match.getBonusPoints());
        System.out.println("Match Points: " + match.getMatchPoints());
        System.out.println();
    }
    public void printLowestScoringMatchAndMostContributingGame() {
        Match match = matchManagement.findLowestScoringMatch();

        System.out.println("=== LOWEST-SCORING MATCH ===");
        System.out.println("Match ID: " + match.getMatchId());
        System.out.println("Games: [" + formatGames(match.getGames()) + "]");
        System.out.println("Rounds: [" + formatRounds(match.getRounds()) + "]");
        System.out.println("Raw Points: " + match.getRawPoints());
        System.out.println("Skill Points: " + match.getSkillPoints());
        System.out.println("Bonus Points: " + match.getBonusPoints());
        System.out.println("Match Points: " + match.getMatchPoints());

        GameContribution contributor = match.getMostContributingGame();

        System.out.println("Most Contributing Game in this Match:");
        System.out.println("Game: " + contributor.getGameName());
        System.out.println("Contribution: " +
                contributor.getRounds() + " rounds × " +
                contributor.getBasePoints() + " points = " +
                contributor.getContribution());
    }
    public void printLowestBonusPointMatch(){
        Match match = matchManagement.findLowestBonusMatch();

        System.out.println("== MATCH WITH THE LOWEST BONUS POINTS ==");
        System.out.println("Match ID: " + match.getMatchId());
        System.out.println("Games: [" + formatGames(match.getGames()) + "]");
        System.out.println("Skill Points: " + match.getSkillPoints());
        System.out.println("Bonus Points: " + match.getBonusPoints());
        System.out.println("Match Points: " + match.getMatchPoints());
        System.out.println();

    }

    public void printHighestScorerInfo(){
        int highestScorerIndex = pointsBoard.getHighestScorerIndex();

        String highestScorerNickName = gamers[highestScorerIndex].getGamerNickname();
        String highestScorerName = gamers[highestScorerIndex].getGamerName();
        int highestScorerTotal = pointsBoard.getTotalPointsAt(highestScorerIndex);
        double highestScorerAvrg = pointsBoard.getAveragePointsAt(highestScorerIndex);
        MedalType highestScorerMedal = pointsBoard.getMedalAt(highestScorerIndex);

        System.out.println("=== HIGHEST-SCORING GAMER ===");
        System.out.println("Nickname: " + highestScorerNickName);
        System.out.println("Name: " + highestScorerName);
        System.out.println("Total Points: " + highestScorerTotal);
        System.out.println("Average Per Match: " + highestScorerAvrg);
        System.out.println("Medal: " + highestScorerMedal);
        System.out.println();

    }

    public void printTotalTournamentPoints() {
        int total = matchManagement.calculateTotalTournamentPoints();
        System.out.println("== TOTAL TOURNAMENT POINTS");
        System.out.println("Total Tournament Points across 1500 matches: " + total);
    }


    public void printMedalStats(){
        int[] medalStats = pointsBoard.countMedals();
        int numberOfGamers = gamers.length;
        int goldCount = medalStats[0];
        int silverCount = medalStats[1];
        int bronzeCount = medalStats[2];
        int noneCount = medalStats[3];

        System.out.println("=== MEDAL DISTRIBUTION ===");
        printMedalLine(MedalType.GOLD, goldCount, numberOfGamers);
        printMedalLine(MedalType.SILVER, silverCount, numberOfGamers);
        printMedalLine(MedalType.BRONZE, bronzeCount, numberOfGamers);
        printMedalLine(MedalType.NONE, noneCount, numberOfGamers);
        System.out.println();
    }
    private void printMedalLine(MedalType medal, int count, int total) {
        double percentage = (count * 100.0) / total;
        System.out.println(medal + ": " + count + " gamers (" + percentage + "%)");
    }
    private String formatGames(Game[] games){
        String gamesLine = "";
        for (int i = 0; i < games.length; i++) {
            gamesLine += games[i].getGameName();
            if (i < games.length - 1) {
                gamesLine += ", ";
            }
        }
        return gamesLine;
    }
    private String formatRounds(int[] rounds){
        String roundsLine = "";
        for (int i = 0; i < rounds.length; i++) {
            roundsLine += rounds[i];
            if (i < rounds.length - 1) roundsLine += ", ";
        }
        return roundsLine;
    }


}
