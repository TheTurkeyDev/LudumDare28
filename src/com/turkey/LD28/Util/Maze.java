package com.turkey.LD28.Util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class Maze
{
	private int xSize = 20; private int ySize = 15;
	private int[][] map = new int[xSize][ySize];
	private ArrayList<Location> walls = new ArrayList<Location>();
	private Random r = new Random();

	private int currentX = 1;
	private int currentY = 1;

	private int nonWall = 0;
	private int wall = 1;
	private BufferedImage key;
	private boolean displaykey = true;
	private Location keyLoc = new Location(724, 524);

	private boolean generated = false;

	public void paint(Graphics g)
	{
		for (int y = 0; y < ySize; y++)
		{
			for (int x = 0; x < xSize; x++)
			{
				if (map[x][y] == wall)
				{

					g.setColor(Color.BLACK);
					g.fillRect(x * 40, y * 40, 40, 40);
				}
				else
				{
					if(x == 1 && y == 1)
					{
						g.setColor(Color.green);
						g.fillRect(x * 40, y * 40, 40, 40);
					}
					else
					{
						g.setColor(Color.GRAY);
						g.fillRect(x * 40, y * 40, 40, 40);
					}
				}
			}
		}
		if (displaykey)
		{
			g.drawImage(key, 724, 524, null);
		}
	}

	public void generate()
	{
		try
		{
			key = ImageIO.read(getClass().getResource("/Key.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		for (int y = 0; y < ySize; y++)
		{
			for (int x = 0; x < xSize; x++)
			{
				map[x][y] = wall;
			}
		}

		map[1][1] = nonWall;
		currentX = 1;
		currentY = 1;
		Location current = new Location(currentX, currentY);
		Location north = current.add(0, -1);
		Location east = current.add(1, 0);
		Location south = current.add(0, 1);
		Location west = current.add(-1, 0);

		if ((map[north.getX()][north.getY()] == wall) && (north.getY() - 1 > 0) && (map[north.getX()][(north.getY() - 1)] == wall))
		{
			walls.add(north);
		}
		if ((map[east.getX()][east.getY()] == wall) && (east.getX() + 1 < xSize) && (map[(east.getX() + 1)][east.getY()] == wall))
		{
			walls.add(east);
		}
		if ((map[south.getX()][south.getY()] == wall) && (south.getY() + 1 < ySize) && (map[south.getX()][(south.getY() + 1)] == wall))
		{
			walls.add(south);
		}
		if ((map[west.getX()][west.getY()] == wall) && (west.getX() - 1 > 0) && (map[(west.getX() - 1)][west.getY()] == wall))
		{
			walls.add(west);
		}

		while (walls.size() > 0)
		{
			int randomLoc = r.nextInt(walls.size());
			currentX = ((Location)walls.get(randomLoc)).getX();
			currentY = ((Location)walls.get(randomLoc)).getY();
			current = new Location(currentX, currentY);
			north = current.add(0, -1);
			east = current.add(1, 0);
			south = current.add(0, 1);
			west = current.add(-1, 0);

			if (!checkwalls(current))
			{
				map[currentX][currentY] = nonWall;
				walls.remove(randomLoc);

				if ((map[north.getX()][north.getY()] == wall) && (north.getY() - 1 > 0) && (map[north.getX()][(north.getY() - 1)] == wall))
				{
					walls.add(north);
				}
				if ((map[east.getX()][east.getY()] == wall) && (east.getX() + 1 < xSize) && (map[(east.getX() + 1)][east.getY()] == wall))
				{
					walls.add(east);
				}
				if ((map[south.getX()][south.getY()] == wall) && (south.getY() + 1 < ySize) && (map[south.getX()][(south.getY() + 1)] == wall))
				{
					walls.add(south);
				}
				if ((map[west.getX()][west.getY()] == wall) && (west.getX() - 1 > 0) && (map[(west.getX() - 1)][west.getY()] == wall))
				{
					walls.add(west);
				}
			}
			else
			{
				walls.remove(randomLoc);
			}
		}
		map[18][13] = nonWall;
		boolean Inaccessible = true;
		int i = 1;
		while (Inaccessible)
		{
			map[(18 - i)][13] = nonWall;
			map[18][(13 - i)] = nonWall;
			i++;
			if ((map[(18 - i)][13] == nonWall) || (map[18][(13 - i)] == nonWall) || (map[(18 - i)][12] == nonWall) || (map[17][(13 - i)] == nonWall))
			{
				Inaccessible = false;
			}
		}
		generated = true;
	}

	public boolean checkwalls(Location loc)
	{
		Location north = loc.add(0, -1);
		Location east = loc.add(1, 0);
		Location south = loc.add(0, 1);
		Location west = loc.add(-1, 0);

		int yes = 0;
		if (map[north.getX()][north.getY()] == nonWall)
		{
			yes++;
		}
		if (map[east.getX()][east.getY()] == nonWall)
		{
			yes++;
		}
		if (map[south.getX()][south.getY()] == nonWall)
		{
			yes++;
		}
		if (map[west.getX()][west.getY()] == nonWall)
		{
			yes++;
		}
		return yes > 1;
	}

	public boolean isGenrated()
	{
		return generated;
	}

	public boolean isWall(int x, int y, int size)
	{
		int x1 = x / 40;
		int x2 = (x + size) / 40;
		int y1 = y / 40;
		int y2 = (y + size) / 40;

		if (map[x1][y1] == wall)
		{
			return true;
		}
		if (map[x1][y2] == wall)
		{
			return true;
		}
		if (map[x2][y1] == wall)
		{
			return true;
		}
		if (map[x2][y2] == wall)
		{
			return true;
		}
		return false;
	}

	public boolean isWall(int x, int y)
	{
		if (map[x][y] == wall)
		{
			return true;
		}
		return false;
	}

	public void keyPickedUp()
	{
		displaykey = false;
	}

	public void keyDropped() {
		displaykey = true;
	}

	public Location getFreeLoc()
	{
		int x = r.nextInt(xSize);
		int y = r.nextInt(ySize);
		boolean valid = false;
		while (!valid)
		{
			if (map[x][y] == nonWall)
			{
				valid = true;
			}
			else
			{
				x = r.nextInt(xSize);
				y = r.nextInt(ySize);
			}
		}
		return new Location(x, y);
	}

	public Location getKeyLoc()
	{
		return keyLoc;
	}
}