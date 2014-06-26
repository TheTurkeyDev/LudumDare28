package com.turkey.LD28.Player;

import com.turkey.LD28.Game.Game;
import com.turkey.LD28.Util.Direction;
import com.turkey.LD28.Util.Entity;
import com.turkey.LD28.Util.Location;
import com.turkey.LD28.Util.Maze;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player
{
	private boolean canMove = false;
	private int xPlayerCord = 50;
	private int yPlayerCord = 50;
	private boolean w = false;
	private boolean a = false;
	private boolean s = false;
	private boolean d = false;
	private boolean space = false;
	private boolean shoot = false;
	private boolean key = false;

	private int speed;

	private int playerMouseXCord = 0;
	private int playerMouseYCord = 0;
	private BufferedImage north;
	private BufferedImage east;
	private BufferedImage south;
	private BufferedImage west;
	private Direction facing = Direction.North;
	private Maze map;
	private Game game;

	public Player(Maze maze, Game g, int moveSpeed)
	{
		try
		{
			map = maze;
			north = ImageIO.read(getClass().getResource("/Images/NinjaNorth.png"));
			east = ImageIO.read(getClass().getResource("/Images/NinjaEast.png"));
			west = ImageIO.read(getClass().getResource("/Images/NinjaWest.png"));
			south = ImageIO.read(getClass().getResource("/Images/NinjaSouth.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		game = g;
		speed = moveSpeed;
	}

	public void paint(Graphics g)
	{
		switch (facing)
		{
			case North:
				g.drawImage(north, xPlayerCord, yPlayerCord, 16, 16, null);
				break;
			case South:
				g.drawImage(south, xPlayerCord, yPlayerCord, 16, 16, null);
				break;
			case West:
				g.drawImage(west, xPlayerCord, yPlayerCord, 16, 16, null);
				break;
			case East:
				g.drawImage(east, xPlayerCord, yPlayerCord, 16, 16, null);
		}
	}

	public double getPlayerXCord()
	{
		return xPlayerCord;
	}

	public double getPlayerYCord() {
		return yPlayerCord;
	}

	public void onPress(String key)
	{
		if (key.equalsIgnoreCase("w"))
		{
			w = true;
		}
		if (key.equalsIgnoreCase("a"))
		{
			a = true;
		}
		if (key.equalsIgnoreCase("s"))
		{
			s = true;
		}
		if (key.equalsIgnoreCase("d"))
		{
			d = true;
		}
		if (key.equalsIgnoreCase(" "))
		{
			space = true;
		}
	}

	public void onDePress(String key) {
		if (key.equalsIgnoreCase("w"))
		{
			w = false;
		}
		if (key.equalsIgnoreCase("a"))
		{
			a = false;
		}
		if (key.equalsIgnoreCase("s"))
		{
			s = false;
		}
		if (key.equalsIgnoreCase("d"))
		{
			d = false;
		}
		if (key.equalsIgnoreCase(" "))
		{
			space = false;
		}
	}

	public void updatePlayerPos() {
		if (!canMove)
			return;
		if (w)
		{
			yPlayerCord -= speed;
			if (map.isWall(xPlayerCord, yPlayerCord, 16))
			{
				yPlayerCord += speed;
			}
			else
			{
				facing = Direction.North;
			}
		}
		if (a)
		{
			xPlayerCord -= speed;
			if (map.isWall(xPlayerCord, yPlayerCord, 16))
			{
				xPlayerCord += speed;
			}
			else
			{
				facing = Direction.West;
			}
		}
		if (s)
		{
			yPlayerCord += speed;
			if (map.isWall(xPlayerCord, yPlayerCord, 16))
			{
				yPlayerCord -= speed;
			}
			else
			{
				facing = Direction.South;
			}
		}
		if (d)
		{
			xPlayerCord += speed;
			if (map.isWall(xPlayerCord, yPlayerCord, 16))
			{
				xPlayerCord -= speed;
			}
			else
			{
				facing = Direction.East;
			}
		}
		if (space)
		{
			if (!shoot)
			{
				Entity ent = new Entity(new Location(xPlayerCord, yPlayerCord), map);
				switch (facing)
				{
					case South:
						ent.setVelocity(0, 5);
						break;
					case North:
						ent.setVelocity(0, -5);
						break;
					case East:
						ent.setVelocity(0, 5);
						break;
					case West:
						ent.setVelocity(-5, 0);
						break;
					default:
						ent.setVelocity(0, 0);
				}

				ent.fire();
				game.addEntity(ent);
				shoot = true;
			}
		}

		if ((xPlayerCord > 720) && (yPlayerCord > 520) && (!key))
		{
			map.keyPickedUp();
			key = true;
		}
		if ((xPlayerCord < 80) && (yPlayerCord < 80) && (key))
		{
			map.keyDropped();
			key = false;
			game.NextRound();
		}
	}

	public int getX() {
		return xPlayerCord;
	}

	public int getY() {
		return yPlayerCord;
	}

	public Location getAbsoluteLocation() 
	{
		return new Location(xPlayerCord, yPlayerCord);
	}
	
	public Location getLocation() 
	{
		return new Location(xPlayerCord / 40, yPlayerCord / 40);
	}

	public void setMousePos(int x, int y)
	{
		playerMouseXCord = x;
		playerMouseYCord = y;
	}

	public int getMouseX()
	{
		return playerMouseXCord;
	}

	public int getMouseY() {
		return playerMouseYCord;
	}

	public void start()
	{
		canMove = true;
	}

	public void stop() {
		canMove = false;
	}

	public void reset() {
		xPlayerCord = 50;
		yPlayerCord = 50;
		shoot = false;
	}

	public void changeSpeed(int s)
	{
		speed = s;
	}

	public void repsawn()
	{
		xPlayerCord = 50;
		yPlayerCord = 50;
	}
}