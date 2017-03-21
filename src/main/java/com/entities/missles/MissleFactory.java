package com.entities.missles;

import com.entities.missles.impl.BigMissle;
import com.entities.missles.impl.NormalMissle;
import com.entities.missles.impl.PlayerMissle;
import com.entities.ship.Player;

public class MissleFactory {

	public static MissleInterface getNormalMissle(int posX, int posY){
		return new NormalMissle(posX, posY);
	}
	
	public static MissleInterface getBigMissle(int posX, int posY, int playerPositionX){
		return new BigMissle(posX, posY, playerPositionX );
	}
	
	public static MissleInterface getPlayerMissle(int posX, int posY){
		return new PlayerMissle(posX, posY);
	}
}
