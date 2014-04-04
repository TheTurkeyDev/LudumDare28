package com.turkey.LD28.Screens;

import com.turkey.LD28.Computer.ComputerPlayer;
import com.turkey.LD28.Game.Game;
import com.turkey.LD28.Screens.SubScreens.PauseSubScreen;
import com.turkey.LD28.Util.Entity;
import java.awt.Graphics;
import java.util.ConcurrentModificationException;

public class MainScreen extends Screen
{
	Game game;
	public MainScreen(String sName)
	{
		super(sName);
		game = new Game();
	}

	public void paint(Graphics g)
	{
		game.getMaze().paint(g);
		game.getPlayer().paint(g);
		try{
			for (ComputerPlayer cpu : game.getCPUs())
			{
				cpu.paint(g);
			}
			for (Entity ent : game.getEntities())
			{
				ent.paint(g);
			}
		}catch(ConcurrentModificationException e)
		{
			System.out.println("Error CME has occured on painting");
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
		super.update();
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
			PauseSubScreen pss = new PauseSubScreen(100, 100, 600, 400, "pause", this);
			if(game.isPaused())
			{
				game.unpause();
				removeAllSubScreens();
			}
			else
			{
				game.pause();
				displaySubScreen(pss);
			}
		}
		super.OnKeyEvent(input, pressed);
	}

	public void onClick(int x, int y)
	{
		super.onClick(x, y);
	}

	public void onMouseMove(int x, int y)
	{
		super.onMouseMove(x, y);
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