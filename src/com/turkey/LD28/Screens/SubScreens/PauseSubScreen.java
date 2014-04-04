package com.turkey.LD28.Screens.SubScreens;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.turkey.LD28.Screens.Screen;

public class PauseSubScreen extends SubScreen
{
	BufferedImage pauseScreen, settingsS, settingsUS;

	boolean seSelected = false;

	public PauseSubScreen(int xloc, int yloc, int w, int h, String n, Screen parent)
	{
		super(xloc, yloc, w, h, n, parent);
		try
		{
			pauseScreen = ImageIO.read(getClass().getResource("/PauseScreen.png"));
			settingsS = ImageIO.read(getClass().getResource("/SettingsSelectedButton.png"));
			settingsUS = ImageIO.read(getClass().getResource("/SettingsUnSelectedButton.png"));
		} catch (IOException e){e.printStackTrace();}
	}

	public void paint(Graphics g)
	{
		g.drawImage(pauseScreen, x, y, width, height, null);
		if (seSelected)
		{
			g.drawImage(settingsS, 300, 250, 200, 40, null);
		}
		else
		{
			g.drawImage(settingsUS, 300, 250, 200, 40, null);
		}

	}

	public String getName()
	{
		return name;
	}

	public void onClick(int x, int y)
	{
		if(x > 300 && x < 500 && y > 250 && y < 290)
		{
			SettingsSubScreen sss = new SettingsSubScreen(100, 100, 600, 400, "settings", parent);
			parent.removeSubScreen(this);
			parent.displaySubScreen(sss);
		}
	}

	public void onMouseMove(int x, int y)
	{
		if(x > 300 && x < 500 && y > 250 && y < 290)
			seSelected = true;
		else
			seSelected = false;
	}

}
