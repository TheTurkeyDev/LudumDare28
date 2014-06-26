package com.turkey.LD28.Screens.SubScreens;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.turkey.LD28.Game.Game;
import com.turkey.LD28.Screens.Screen;
import com.turkey.LD28.Screens.ScreenManager;

public class PauseSubScreen extends SubScreen
{
	BufferedImage pauseScreen, settingsS, settingsUS, backS, backUS, restartS, restartUS, mainmenuS, mainmenuUS ;

	boolean seSelected = false, bSelected = false, rSelected = false, rtmSelected = false;

	public PauseSubScreen(int xloc, int yloc, int w, int h, String n, Screen parent)
	{
		super(xloc, yloc, w, h, n, parent);
		try
		{
			pauseScreen = ImageIO.read(getClass().getResource("/Images/PauseScreen.png"));
			settingsS = ImageIO.read(getClass().getResource("/Images/SettingsSelectedButton.png"));
			settingsUS = ImageIO.read(getClass().getResource("/Images/SettingsUnSelectedButton.png"));
			backS = ImageIO.read(getClass().getResource("/Images/BackSelectedButton.png"));
			backUS = ImageIO.read(getClass().getResource("/Images/BackUnSelectedButton.png"));
			restartS = ImageIO.read(getClass().getResource("/Images/RestartSelectedButton.png"));
			restartUS = ImageIO.read(getClass().getResource("/Images/RestartUnSelectedButton.png"));
			mainmenuS = ImageIO.read(getClass().getResource("/Images/RTMSelectedButton.png"));
			mainmenuUS = ImageIO.read(getClass().getResource("/Images/RTMUnSelectedButton.png"));
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

		if (rSelected)
		{
			g.drawImage(restartS, 300, 300, 200, 40, null);
		}
		else
		{
			g.drawImage(restartUS, 300, 300, 200, 40, null);
		}

		if (bSelected)
		{
			g.drawImage(backS, 300, 350, 200, 40, null);
		}
		else
		{
			g.drawImage(backUS, 300, 350, 200, 40, null);
		}
		
		if (rtmSelected)
		{
			g.drawImage(mainmenuS, 300, 400, 200, 40, null);
		}
		else
		{
			g.drawImage(mainmenuUS, 300, 400, 200, 40, null);
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

		if(x > 300 && x < 500 && y > 300 && y < 340)
		{
			parent.removeAllSubScreens();
			Game.instance().restart();
		}

		if(x > 300 && x < 500 && y > 350 && y < 390)
		{
			parent.removeAllSubScreens();
			Game.instance().unpause();
		}
		
		if(x > 300 && x < 500 && y > 400 && y < 440)
		{
			ScreenManager.manager.setCurrentScreen("BeginningScreen");
		}
	}

	public void onMouseMove(int x, int y)
	{
		if(x > 300 && x < 500 && y > 250 && y < 290)
			seSelected = true;
		else
			seSelected = false;

		if(x > 300 && x < 500 && y > 300 && y < 340)
			rSelected = true;
		else
			rSelected = false;
		
		if(x > 300 && x < 500 && y > 350 && y < 390)
			bSelected = true;
		else
			bSelected = false;
		
		if(x > 300 && x < 500 && y > 400 && y < 440)
			rtmSelected = true;
		else
			rtmSelected = false;
	}

}
