package com.turkey.LD28.Screens;

import com.turkey.LD28.Computer.ComputerPlayer;
import com.turkey.LD28.Game.Game;
import com.turkey.LD28.Screens.SubScreens.PauseSubScreen;
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
		super.paint(g);
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

	public void OnKeyEvent(String input, Boolean pressed)
	{
		if (pressed.booleanValue())
		{
			game.getPlayer().onPress(input);
		}
		else
		{
			game.getPlayer().onDePress(input);
		}
		if(input.equalsIgnoreCase("esc"))
		{
			PauseSubScreen pss = new PauseSubScreen(100, 100, 600, 400, "pause");
			if(game.isPaused())
			{
				game.unpause();
				toggleSubScreen(pss);
			}
			else
			{
				game.pause();
				toggleSubScreen(pss);
			}
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