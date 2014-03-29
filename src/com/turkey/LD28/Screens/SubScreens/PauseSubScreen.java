package com.turkey.LD28.Screens.SubScreens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PauseSubScreen extends SubScreen
{
	BufferedImage pauseScreen;
	
	public PauseSubScreen(int xloc, int yloc, int w, int h, String n)
	{
		super(xloc, yloc, w, h, n);
		try
		{
			pauseScreen = ImageIO.read(getClass().getResource("/PauseScreen.png"));
		} catch (IOException e){e.printStackTrace();}
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(pauseScreen, x, y, width, height, null);
	}

	public String getName()
	{
		return name;
	}

	public void onClick(int x, int y)
	{

	}

	public void onMouseMove(int x, int y)
	{

	}

}
