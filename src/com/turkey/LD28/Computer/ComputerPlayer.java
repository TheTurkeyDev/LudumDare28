package com.turkey.LD28.Computer;

import com.turkey.LD28.Player.Player;
import com.turkey.LD28.Util.Direction;
import com.turkey.LD28.Util.Location;
import com.turkey.LD28.Util.Maze;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class ComputerPlayer
{
	@SuppressWarnings("unused")
	private Player player;
	private Maze map;
	private BufferedImage north;
	private BufferedImage east;
	private BufferedImage south;
	private BufferedImage west;
	private BufferedImage flnorth;
	private BufferedImage fleast;
	private BufferedImage flsouth;
	private BufferedImage flwest;
	private Direction LastDir = null;

	boolean up = false;
	boolean down = false;
	boolean right = false;
	boolean left = false;
	private int xCompCord;
	private int yCompCord;
	private int currentX;
	private int currentY;
	private int speed;
	private ArrayList<Direction> primary = new ArrayList<Direction>();
	private ArrayList<Direction> secondary = new ArrayList<Direction>();

	private boolean stop = false;

	public ComputerPlayer(Player playerC, Maze maze, Location loc, int moveSpeed)
	{
		try {
			north = ImageIO.read(getClass().getResource("/GaurdNorth.png"));
			east = ImageIO.read(getClass().getResource("/GaurdEast.png"));
			west = ImageIO.read(getClass().getResource("/GaurdWest.png"));
			south = ImageIO.read(getClass().getResource("/GaurdSouth.png"));
			flnorth = ImageIO.read(getClass().getResource("/FlashLightNorth.png"));
			fleast = ImageIO.read(getClass().getResource("/FlashLightEast.png"));
			flsouth = ImageIO.read(getClass().getResource("/FlashLightSouth.png"));
			flwest = ImageIO.read(getClass().getResource("/FlashLightWest.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		player = playerC;
		map = maze;
		xCompCord = (loc.getX() * 40 + 10);
		yCompCord = (loc.getY() * 40 + 10);
		currentX = loc.getX();
		currentY = loc.getY();
		speed = moveSpeed;
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.BLUE);
		if (LastDir == null)
		{
			g.drawImage(north, xCompCord, yCompCord, 16, 16, null);
			return;
		}
		switch (LastDir)
		{
			case North:
				g.drawImage(north, xCompCord, yCompCord, 16, 16, null);
				g.drawImage(flnorth, xCompCord - 10, yCompCord - 50, 40, 50, null);
				break;
			case East:
				g.drawImage(east, xCompCord, yCompCord, 16, 16, null);
				g.drawImage(fleast, xCompCord + 15, yCompCord - 15, 50, 40, null);
				break;
			case South:
				g.drawImage(south, xCompCord, yCompCord, 16, 16, null);
				g.drawImage(flsouth, xCompCord - 10, yCompCord + 15, 40, 50, null);
				break;
			case West:
				g.drawImage(west, xCompCord, yCompCord, 16, 16, null);
				g.drawImage(flwest, xCompCord - 50, yCompCord - 15, 50, 40, null);
				break;
			default:
				g.drawImage(north, xCompCord, yCompCord, 16, 16, null);
		}
	}

	public void updateCompPos()
	{
		if (stop)
		{
			return;
		}
		if ((currentX != xCompCord / 40) || (currentY != yCompCord / 40) || (LastDir == null))
		{
			centerComp();
			if (isCentered())
			{
				choseDir();
				currentX = (xCompCord / 40);
				currentY = (yCompCord / 40);
			}
		}
		if (up)
		{
			yCompCord -= speed;
			if (map.isWall(xCompCord, yCompCord, 20))
			{
				yCompCord += speed;
				choseDir();
			}
			else
			{
				LastDir = Direction.North;
			}
		}
		if (left)
		{
			xCompCord -= speed;
			if (map.isWall(xCompCord, yCompCord, 20))
			{
				xCompCord += speed;
				choseDir();
			}
			else
			{
				LastDir = Direction.West;
			}
		}
		if (down)
		{
			yCompCord += speed;
			if (map.isWall(xCompCord, yCompCord, 20))
			{
				yCompCord -= speed;
				choseDir();
			}
			else
			{
				LastDir = Direction.South;
			}
		}
		if (right)
		{
			xCompCord += speed;
			if (map.isWall(xCompCord, yCompCord, 20))
			{
				xCompCord -= speed;
				choseDir();
			}
			else
			{
				LastDir = Direction.East;
			}
		}
	}

	private int getAboveCord(int y) {
		int y2 = y / 40;
		y2--;
		return y2;
	}

	private int getBelowCord(int y) {
		int y2 = y / 40;
		y2++;
		return y2;
	}

	private int getLeftCord(int x) {
		int x2 = x / 40;
		x2--;
		return x2;
	}

	private int getRightCord(int x) {
		int x2 = x / 40;
		x2++;
		return x2;
	}

	private void choseDir() {
		int AboveY = getAboveCord(yCompCord);
		int BelowY = getBelowCord(yCompCord);
		int LeftX = getLeftCord(xCompCord);
		int RightX = getRightCord(xCompCord);
		int NormX = xCompCord / 40;
		int NormY = yCompCord / 40;
		up = false;
		down = false;
		right = false;
		left = false;
		if (!map.isWall(LeftX, NormY))
		{
			if (LastDir == Direction.East)
			{
				secondary.add(Direction.West);
			}
			else
			{
				primary.add(Direction.West);
			}
		}
		if (!map.isWall(RightX, NormY))
		{
			if (LastDir == Direction.West)
			{
				secondary.add(Direction.East);
			}
			else
			{
				primary.add(Direction.East);
			}
		}
		if (!map.isWall(NormX, AboveY))
		{
			if (LastDir == Direction.South)
			{
				secondary.add(Direction.North);
			}
			else
			{
				primary.add(Direction.North);
			}
		}
		if (!map.isWall(NormX, BelowY))
		{
			if (LastDir == Direction.North)
			{
				secondary.add(Direction.South);
			}
			else
			{
				primary.add(Direction.South);
			}
		}
		pickADir();
	}

	private void pickADir()
	{
		if (primary.size() == 0)
		{
			primary.addAll(secondary);
		}
		Random r = new Random();
		int a = r.nextInt(primary.size());
		Direction pickedDir = (Direction)primary.get(a);
		switch (pickedDir)
		{
			case North:
				up = true;
				break;
			case West:
				left = true;
				break;
			case South:
				down = true;
				break;
			case East:
				right = true;
		}

		primary.clear();
		secondary.clear();
	}

	private void centerComp() {
		if (LastDir == null)
		{
			return;
		}
		if (LastDir == Direction.North)
		{
			up = true;
		}
		if (LastDir == Direction.South)
		{
			down = true;
		}
		if (LastDir == Direction.West)
		{
			left = true;
		}
		if (LastDir == Direction.East)
		{
			right = true;
		}
	}

	private boolean isCentered()
	{
		if ((xCompCord % 40 == 10) && (yCompCord % 40 == 10))
		{
			return true;
		}
		return false;
	}

	public Location getAbsoluteLocation()
	{
		return new Location(xCompCord, yCompCord);
	}

	public void stop()
	{
		stop = true;
	}

	public void start() {
		stop = false;
	}

	public boolean isFacing(Direction dir) {
		return LastDir == dir;
	}
}