import java.io.File;

public class MatchManagement
{
	private final Gamer[] allGamers;
	private final Match[][] allMatches;
	
	public MatchManagement()
	{
		File gamersFile = new File("./src/gamers.csv");
        allGamers = Gamer.returnObjectArray(gamersFile);
        
		allMatches = new Match[allGamers.length][15];
		for (int gamerIndex = 0; gamerIndex < allGamers.length; gamerIndex++)
		{
			for (int matchIndex = 0; matchIndex < 15; matchIndex++)
			{
				int matchNumber = (gamerIndex * matchIndex) + matchIndex + 1;
				allMatches[gamerIndex][matchIndex] = new Match(matchNumber, allGamers[gamerIndex]);
			}
		}
	}
	
	public Match getMatch(int gamerIndex, int matchIndex)
	{
		return allMatches.clone()[gamerIndex][matchIndex];
	}
}
