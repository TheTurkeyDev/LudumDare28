package com.turkey.LD28.Game;

public class GameSettings
{
	
	private int speed = 1;
	private static GameSettings gameSettings;
	
	public GameSettings()
	{
		gameSettings = this;
	}
	
	
	public void setSpeed(int s)
	{
		speed = s;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public static GameSettings getSettings()
	{
		return gameSettings;
	}
}