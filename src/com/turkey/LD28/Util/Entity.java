package com.turkey.LD28.Util;

import com.turkey.LD28.Computer.ComputerPlayer;
import com.turkey.LD28.Game.Game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entity
{
	private int x;
	private int y;
	private int xVelocity = 0; private int yVelocity = 0;
	private Maze map;
	private boolean isMoving = false;
	private BufferedImage bullet;

	public Entity(Location loc, Maze maze)
	{
		x = loc.getX();
		y = loc.getY();
		map = maze;
		try
		{
			bullet = ImageIO.read(getClass().getResource("/bulletEntity.png"));
		} catch (IOException e){e.printStackTrace();}
	}

	public void setVelocity(int xVel, int yVel)
	{
		xVelocity = xVel;
		yVelocity = yVel;
	}

	public void fire()
	{
		isMoving = true;
	}

	public void updateLocation()
	{
		Game game = Game.instance();
		if (!isMoving)
			return;
		x += xVelocity;
		y += yVelocity;
		if (map.isWall(x, y, 10))
		{
			game.removeEntity(this);
		}
		for (ComputerPlayer cpu : game.getCPUs())
		{
			if ((cpu.getAbsoluteLocation().getX() / 40 == x / 40) && (cpu.getAbsoluteLocation().getY() / 40 == y / 40))
			{
				game.removeEntity(this);
				game.removeCPU(cpu);
				return;
			}
		}
	}

	public void paint(Graphics g)
	{
		g.drawImage(bullet, x, y, 10, 10,null);
	}
}