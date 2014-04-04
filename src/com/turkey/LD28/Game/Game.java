package com.turkey.LD28.Game;

import java.util.ArrayList;

import com.turkey.LD28.Computer.ComputerPlayer;
import com.turkey.LD28.Player.Player;
import com.turkey.LD28.Util.Direction;
import com.turkey.LD28.Util.Entity;
import com.turkey.LD28.Util.Location;
import com.turkey.LD28.Util.Maze;

public class Game
{
	private Maze maze;
	private Player player;
	private GameSettings settings;
	private static Game game;
	private int round = 1;
	private boolean paused = false;

	private static ArrayList<ComputerPlayer> cpus = new ArrayList<ComputerPlayer>();
	private static ArrayList<Entity> entities = new ArrayList<Entity>();
	private static ArrayList<ComputerPlayer> cpusToRemove = new ArrayList<ComputerPlayer>();
	private static ArrayList<Entity> entitiesToRemove = new ArrayList<Entity>();

	public Game()
	{
		settings = GameSettings.getSettings();
		game = this;
		maze = new Maze();
		player = new Player(maze, this , settings.getSpeed());
		NextRound();
	}

	public static Game instance()
	{
		return game;
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
		Location ploc = player.getAbsoluteLocation();
		Location cloc = cpu.getAbsoluteLocation();
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

	public void addEntity(Entity ent)
	{
		entities.add(ent);
	}

	public void removeEntity(Entity ent) {
		entitiesToRemove.add(ent);
	}

	public void removeCPU(ComputerPlayer cpu) {
		cpusToRemove.add(cpu);
	}

	public ArrayList<ComputerPlayer> getCPUs() {
		return cpus;
	}

	public void clearMaze()
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
			ComputerPlayer cpu = new ComputerPlayer(player, maze, loc, settings.getSpeed());
			cpus.add(cpu);
		}
		round += 1;
	}

	public Maze getMaze()
	{
		return maze;
	}
	public Player getPlayer()
	{
		return player;
	}
	public ArrayList<Entity> getEntities()
	{
		return entities;
	}

	public int getRound()
	{
		return round;
	}
	
	public void pause()
	{
		paused = true;
		player.stop();
		for(ComputerPlayer cpu: cpus)
		{
			cpu.stop();
		}
		for(Entity ent: entities)
		{
			ent.stop();
		}
	}
	public void unpause()
	{
		paused = false;
		player.start();
		for(ComputerPlayer cpu: cpus)
		{
			cpu.start();
		}
		for(Entity ent: entities)
		{
			ent.fire();
		}
	}
	
	public boolean isPaused()
	{
		return paused;
	}
	
	public void updateSettings()
	{
		player.changeSpeed(GameSettings.getSettings().getSpeed());
		for(ComputerPlayer cpu: cpus)
		{
			cpu.changeSpeed(GameSettings.getSettings().getSpeed());
		}
	}
}
