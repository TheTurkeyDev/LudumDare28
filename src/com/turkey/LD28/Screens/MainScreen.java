package com.turkey.LD28.Screens;

import com.turkey.LD28.Computer.ComputerPlayer;
import com.turkey.LD28.Game.Game;
import com.turkey.LD28.Util.Entity;
import java.awt.Graphics;

public class MainScreen extends Screen
{
	Game game;
	public MainScreen(String sName, int movementSpeed)
	{
		super(sName);
		game = new Game();
	}

	public void paint(Graphics g)
	{
		game.getMaze().paint(g);
		game.getPlayer().paint(g);
		for (ComputerPlayer cpu : game.getCPUs())
		{
			cpu.paint(g);
		}
		for (Entity ent : game.getEntities())
		{
			ent.paint(g);
		}
	}

	public void update()
	{
		game.getPlayer().updatePlayerPos();
		for (ComputerPlayer cpu : game.getCPUs())
		{
			cpu.updateCompPos();
			if (game.cansee(game.getPlayer(), cpu))
			{
				endGame();
				return;
			}
		}
		for (Entity ent : game.getEntities())
		{
			ent.updateLocation();
		}
		game.clearMaze();
	}

	public void OnKeyEvent(String Input, Boolean pressed)
	{
		if (pressed.booleanValue())
		{
			game.getPlayer().onPress(Input);
		}
		else
		{
			game.getPlayer().onDePress(Input);
		}
	}

	public void onClick(int x, int y)
	{
	}

	public void onMouseMove(int x, int y)
	{
	}
	
	public void endGame()
	{
		game.getPlayer().stop();
		for (ComputerPlayer cpu : game.getCPUs())
		{
			cpu.stop();
		}
		ScreenManager.manager.newScreen(new EndScreen("EndScreen"));
		ScreenManager.manager.setCurrentScreen("EndScreen");
	}
}