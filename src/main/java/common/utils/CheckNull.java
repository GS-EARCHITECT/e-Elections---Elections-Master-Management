package common.utils;


public class CheckNull 
{
	public static Double checkDoubleNull(Double d)
	{
		if(d == null)
		{	
		return (double) 0;
		}
		else
		{
		return d;	
		}
	}
	
	public static Float checkFloatNull(Float f)
	{
		if(f == null)
		{	
		return (float) 0;
		}
		else
		{
		return f;	
		}
	}
	
	public static Integer checkFloatNull(Integer i)
	{
		if(i == null)
		{	
		return 0;
		}
		else
		{
		return i;	
		}
	}
}
