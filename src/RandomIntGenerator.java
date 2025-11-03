import java.util.Random;

public class RandomIntGenerator
{
	private static final Random random = new Random();
	
	public static int getRandomIntInRange(int min, int max)
	{
		if (min >= max)
		{
			throw new IllegalArgumentException("First integer must be smaller than the second.");
		}
		
    	return random.nextInt((max - min) + 1) + min;
	}
	
	public static int[] getUniqueRandomIntsInRange(int min, int max, int count)
	{
		if (min >= max)
		{
			throw new IllegalArgumentException("First integer must be smaller than the second.");
		}
		
		int rangeSize = max - min + 1;
        if (count > rangeSize)
        {
            throw new IllegalArgumentException("Count larger than range size.");
        }

        int[] result = new int[count];
        int[] pool = new int[rangeSize];

        for (int i = 0; i < rangeSize; i++)
        {
            pool[i] = min + i;
        }

        for (int i = 0; i < count; i++)
        {
            int j = i + random.nextInt(rangeSize - i);
            int temp = pool[i];
            pool[i] = pool[j];
            pool[j] = temp;
            result[i] = pool[i];
        }

        return result;
	}
}
