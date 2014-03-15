package com.turkey.LD28.Screens;

import com.turkey.LD28.Computer.ComputerPlayer;
import com.turkey.LD28.Player.Player;
import com.turkey.LD28.Util.Direction;
import com.turkey.LD28.Util.Entity;
import com.turkey.LD28.Util.Location;
import com.turkey.LD28.Util.Maze;
import java.awt.Graphics;
import java.util.ArrayList;

public class MainScreen extends Screen
{
	private Maze maze = new Maze();
	private Player player;

	private static ArrayList<ComputerPlayer> cpus = new ArrayList<ComputerPlayer>();
	private static ArrayList<Entity> entities = new ArrayList<Entity>();
	private static ArrayList<ComputerPlayer> cpusToRemove = new ArrayList<ComputerPlayer>();
	private static ArrayList<Entity> entitiesToRemove = new ArrayList<Entity>();

	private int round = 1;
	private int speed;

	public MainScreen(String sName, int movementSpeed)
	{
		super(sName);
		speed = movementSpeed;
		player = new Player(maze, this , speed);
		NextRound();
	}

	public void paint(Graphics g)
	{
		maze.paint(g);
		player.paint(g);
		for (ComputerPlayer cpu : cpus)
		{
			cpu.paint(g);
		}
		for (Entity ent : entities)
		{
			ent.paint(g);
		}
	}

	public void update()
	{
		player.updatePlayerPos();
		for (ComputerPlayer cpu : cpus)
		{
			cpu.updateCompPos();
			if (cansee(player, cpu))
			{
				endGame();
				return;
			}
		}
		for (Entity ent : entities)
		{
			ent.updateLocation();
		}
		removeItems();
	}

	public void OnKeyEvent(String Input, Boolean pressed)
	{
		if (pressed.booleanValue())
		{
			player.onPress(Input);
		}
		else
		{
			player.onDePress(Input);
		}
	}

	public void onClick(int x, int y)
	{
	}

	public void onMouseMove(int x, int y)
	{
	}

	public double getNearest(Location loc)
	{
		double dist = Math.sqrt(Math.pow(loc.getX() - 1, 2.0D) + Math.pow(loc.getY() - 1, 2.0D));
		double anotherDist = Math.sqrt(Math.pow(loc.getX() - 50, 2.0D) + Math.pow(loc.getY() - 50, 2.0D));
		if (anotherDist < dist)
		{
			dist = anotherDist;
		}
		return dist;
	}

	public boolean cansee(Player player, ComputerPlayer cpu)
	{
		Location ploc = player.getLocation();
		Location cloc = cpu.getLocation();
		if ((ploc.getX() / 40 - cloc.getX() / 40 != 0) && (ploc.getY() / 40 - cloc.getY() / 40 != 0))
			return false;
		double dist = Math.sqrt(Math.pow(ploc.getX() - cloc.getX(), 2.0D) + Math.pow(ploc.getY() - cloc.getY(), 2.0D));
		if ((ploc.getX() / 40 - cloc.getX() / 40 == 0) && (dist < 50.0D))
		{
			if (ploc.getY() > cloc.getY())
			{
				if (cpu.isFacing(Direction.South))
				{
					return true;
				}
			}
			else if (ploc.getY() < cloc.getY())
			{
				if (cpu.isFacing(Direction.North))
				{
					return true;
				}
			}
		}
		else if ((ploc.getY() / 40 - cloc.getY() / 40 == 0) && (dist < 50.0D))
		{
			if (ploc.getX() > cloc.getX())
			{
				if (cpu.isFacing(Direction.East))
				{
					return true;
				}
			}
			else if (ploc.getX() < cloc.getX())
			{
				if (cpu.isFacing(Direction.West))
				{
					return true;
				}
			}
		}
		return false;
	}

	public void endGame()
	{
		player.stop();
		for (ComputerPlayer cpu : cpus)
		{
			cpu.stop();
		}
		ScreenManager.manager.newScreen(new EndScreen("EndScreen", round));
		ScreenManager.manager.setCurrentScreen("EndScreen");
	}

	public static void addEntity(Entity ent)
	{
		entities.add(ent);
	}

	public static void removeEntity(Entity ent) {
		entitiesToRemove.add(ent);
	}

	public static void removeCPU(ComputerPlayer cpu) {
		cpusToRemove.add(cpu);
	}

	public static ArrayList<ComputerPlayer> getCPUs() {
		return cpus;
	}

	private void removeItems()
	{
		entities.removeAll(entitiesToRemove);
		cpus.removeAll(cpusToRemove);
	}

	public void NextRound()
	{
		for (ComputerPlayer cpu : cpus)
		{
			removeCPU(cpu);
		}
		maze.generate();
		player.start();
		player.reset();
		for (int i = 0; i < round; i++)
		{
			Location loc = maze.getFreeLoc();
			double dist = getNearest(loc);
			while (dist < 10.0D)
			{
				loc = maze.getFreeLoc();
				dist = getNearest(loc);
			}
			ComputerPlayer cpu = new ComputerPlayer(player, maze, loc, speed);
			cpus.add(cpu);
		}
		round += 1;
	}
}