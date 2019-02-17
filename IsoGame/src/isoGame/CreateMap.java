package isoGame;

public abstract class CreateMap {
	
	
	public static Tile[][] getMapLayer1(int mapNum)
	{
		switch(mapNum)
		{
		case 1:
			Tile[][] map1 = {
									{nT(1,7)},							
							   {nT(1,11),nT(1,15)},						
						   {nT(1,11),nT(1,2),nT(1,15)},						
					   {nT(1,11),nT(1,2),nT(1,2),nT(1,15)},									
				  {nT(1,11), nT(1,2),nT(1,2),nT(1,2),nT(1,15)},
			  {nT(1,11),nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,15)},
		   {nT(1,11),nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,15)},
			   {nT(1,2),nT(1,2),nT(1,2),nT(1,2), nT(1,2),nT(1,2)},
				  {nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,2)},
				      {nT(1,2),nT(1,2),nT(1,2),nT(1,2)},
				          {nT(1,2),nT(1,2),nT(1,2)},
					 	     {nT(1,2),nT(1,2)},					
					 		     { nT(1,2)},
				};
			return map1;
			
		case 2:
			Tile[][] map2 = {
					{nT(1,7)},							
				{nT(1,11),nT(1,15)},						
					{nT(1,1)}
				};
			return map2;
			default:
				Tile[][] mapD = {
						{nT(1,7)},							
					{nT(1,11),nT(1,15)},						
					{nT(1,11),nT(1,2),nT(1,15)},						
					{nT(1,11),nT(1,2),nT(1,2),nT(1,15)},									
					{nT(1,11), nT(1,2),nT(1,2),nT(1,2),nT(1,15)},
					{nT(1,11),nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,15)},
					{nT(1,11),nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,15)},
					{nT(1,19),nT(1,2),nT(1,2),nT(1,2), nT(1,2),nT(1,23)},
					{nT(1,19),nT(1,2),nT(1,2),nT(1,2),nT(1,23)},
					{nT(1,19),nT(1,2),nT(1,2),nT(1,23)},
					 {nT(1,19),nT(1,2),nT(1,23)},
						 	{nT(1,19),nT(1,23)},					
						 		{ nT(1,19)},
					};
				return mapD;
			case 3:
				Tile[][] map3 = {
										{nT(1,7)},							
								  {nT(1,11),nT(1,15)},						
					           {nT(1,11),nT(1,2),nT(1,15)},						
					       {nT(1,11),nT(1,2),nT(1,2),nT(1,15)},									
					  {nT(1,11), nT(1,2),nT(1,2),nT(1,2),nT(1,15)},
				  {nT(1,11),nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,15)},
			  {nT(1,11),nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,15)},
		 {nT(1,11),nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,15)},
			  {nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,2)},
				 {nT(1,2),nT(1,2),nT(1,2),nT(1,2), nT(1,2),nT(1,2)},
					{nT(1,2),nT(1,2),nT(1,2),nT(1,2),nT(1,2)},
						 {nT(1,2),nT(1,2),nT(1,2),nT(1,2)},
						 	{nT(1,2),nT(1,2),nT(1,2)},
						 		 {nT(1,2),nT(1,2)},					
						 		 	 {nT(1,2)},
					};
				return map3;
		}
	}
	
	public static Tile[][] getMapLayer4(int mapNum)
	{
		switch(mapNum)
		{
		case 1:
			Tile[][] map1 = {
					{nT(1,0)},							
				{nT(1,0),nT(1,0)},						
				{nT(1,0),nT(1,0),nT(1,0)},						
				{nT(1,0),nT(1,0),nT(1,0),nT(1,0)},									
				{nT(1,0), nT(1,0),nT(1,0),nT(1,0),nT(1,0)},
				{nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0)},
				{nT(1,19),nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,23)},
				{nT(1,19),nT(1,0),nT(1,0),nT(1,0), nT(1,0),nT(1,23)},
				{nT(1,19),nT(1,0),nT(1,0),nT(1,0),nT(1,23)},
				{nT(1,19),nT(1,0),nT(1,0),nT(1,23)},
				 {nT(1,19),nT(1,0),nT(1,23)},
					 	{nT(1,19),nT(1,23)},					
					 		{ nT(1,27)},
				};
			return map1;
			
		case 2:
			Tile[][] map2 = {
					{nT(1,0)},							
				{nT(1,19),nT(1,23)},						
					{nT(1,27)}
				};
			return map2;
			
		case 3:
			Tile[][] map3 = {
									{nT(1,0)},							
							    {nT(1,0),nT(1,0)},						
							{nT(1,0),nT(1,0),nT(1,0)},						
						{nT(1,0),nT(1,0),nT(1,0),nT(1,0)},									
				   {nT(1,0), nT(1,0),nT(1,0),nT(1,0),nT(1,0)},
				{nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0)},
			{nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0)},
		{nT(1,19),nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,23)},
		   {nT(1,19),nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,23)},
			   {nT(1,19),nT(1,0),nT(1,0),nT(1,0), nT(1,0),nT(1,23)},
				   {nT(1,19),nT(1,0),nT(1,0),nT(1,0),nT(1,23)},
				       {nT(1,19),nT(1,0),nT(1,0),nT(1,23)},
				           {nT(1,19),nT(1,0),nT(1,23)},
					 	       {nT(1,19),nT(1,23)},					
					 			   { nT(1,27)},
				};
			return map3;
			default:
				Tile[][] mapD = {
						{nT(1,0)},							
						{nT(1,0),nT(1,0)},						
						{nT(1,0),nT(1,0),nT(1,0)},						
						{nT(1,0),nT(1,0),nT(1,0),nT(1,0)},									
						{nT(1,0), nT(1,0),nT(1,0),nT(1,0),nT(1,0)},
						{nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0)},
						{nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0),nT(1,0)},
						{nT(1,19),nT(1,0),nT(1,0),nT(1,0), nT(1,0),nT(1,23)},
						{nT(1,19),nT(1,0),nT(1,0),nT(1,0),nT(1,23)},
						{nT(1,19),nT(1,0),nT(1,0),nT(1,23)},
						 {nT(1,19),nT(1,0),nT(1,23)},
							 	{nT(1,19),nT(1,23)},					
							 		{ nT(1,27)},
						};
				return mapD;
		}
	}
	
	//CREATE EXPIREMENAL TILE	
	private static Tile nT(int set,int idNum)
	{
			return new Tile(set, idNum);			
	}
	
//		private static Tile nT(String tile)
//		{
//				return new Tile(tile);			
//		}

}
