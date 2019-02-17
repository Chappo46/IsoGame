package isoGame;

public enum Direction {
	N("North"),
	NE("North East"),
	E("East"),
	SE("South East"),
	S("South"),
	SW("South West"),
	W("West"),
	NW("North West");
	
	private String name;
	
	private Direction(String str)
	{
		name = str;
	}
	
	@Override
	public String toString()
	{
		return name;
	}

}
