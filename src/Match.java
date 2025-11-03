import java.io.File;

public class Match
{
	private final int matchID;
	private final Game[] gamesPlayed;
	private final int[] numberOfRounds;
	private final Gamer gamer;
	
	private int rawPoints;
	private int skillPoints;
	private int bonusPoints;
	private int matchPoints;
	
	public static final int GAMES_PER_MATCH = 3;
	public static final int MIN_ROUNDS_PER_GAME = 1;
	public static final int MAX_ROUNDS_PER_GAME = 10;
	
	public Match(int matchNumber, Gamer gamer)
	{
		this.gamer = gamer;
		this.matchID = matchNumber;
		
		this.gamesPlayed = new Game[GAMES_PER_MATCH];
		this.numberOfRounds = new int[GAMES_PER_MATCH];
        
		setupGames();
		this.rawPoints = calculateRawPoints();
		this.skillPoints = calculateSkillPoints();
		this.bonusPoints = calculateBonusPoints();
		this.matchPoints = rawPoints + skillPoints + bonusPoints;
	}
	
	private void setupGames()
	{
		File gamesFile = new File("./src/games.csv");
        Game[] allGames = Game.returnObjectArray(gamesFile);
        
        int[] randomGameIndices = RandomIntGenerator.getUniqueRandomIntsInRange(0, allGames.length - 1, GAMES_PER_MATCH);
        
        for (int i = 0; i < GAMES_PER_MATCH; i++)
        {
            gamesPlayed[i] = allGames[randomGameIndices[i]];
            numberOfRounds[i] = RandomIntGenerator.getRandomIntInRange(MIN_ROUNDS_PER_GAME, MAX_ROUNDS_PER_GAME);
        }
	}
	
	public Match(Match aMatch) //Copy constructor
	{
		if (aMatch == null)
		{
			throw new IllegalArgumentException("Invalid argument.");
		}
		
		this.gamer = aMatch.gamer;
		this.matchID = aMatch.matchID;
		this.gamesPlayed = aMatch.gamesPlayed;
		this.numberOfRounds = aMatch.numberOfRounds;
		this.rawPoints = aMatch.rawPoints;
		this.skillPoints = aMatch.skillPoints;
		this.bonusPoints = aMatch.bonusPoints;
		this.matchPoints = aMatch.matchPoints;
	}
	
	
	
	private int calculateRawPoints()
	{
		int totalRaw = 0;
		for (int i = 0; i < GAMES_PER_MATCH; i++)
		{
			totalRaw += numberOfRounds[i] * gamesPlayed[i].getGameBasePoint();
		}
		return totalRaw;
	}
	
	private int calculateSkillPoints()
	{
		int totalSkill = (int) Math.floor(rawPoints * (1 + (Math.min(gamer.getGamerExperienceYears(), 10) * 0.02)));
		return totalSkill;
	}
	
	private int calculateBonusPoints()
	{
		int totalBonus = 0;
		
		if (rawPoints < 0)
		{
			throw new IllegalArgumentException("Game points cannot be negative.");
		}
		else if (rawPoints <= 199)
		{
			totalBonus = 10;
		}
		else if (rawPoints <= 399)
		{
			totalBonus = 25;
		}
		else if (rawPoints <= 599)
		{
			totalBonus = 50;
		}
		else if (rawPoints >= 600)
		{
			totalBonus = 100;
		}
		return totalBonus;
	}
	
	
	
	public int getMatchID()
	{
		return matchID;
	}
	
	public Game[] getGamesPlayed()
	{
		return gamesPlayed.clone();
	}
	
	public int[] getNumberOfRounds()
	{
		return numberOfRounds.clone();
	}
	
	public int getRawPoints()
	{
		return rawPoints;
	}
	
	public int getSkillPoints()
	{
		return skillPoints;
	}
	
	public int getMatchPoints()
	{
		return matchPoints;
	}
}
