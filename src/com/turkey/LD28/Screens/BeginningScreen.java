package com.turkey.LD28.Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BeginningScreen extends Screen
{
	Font font1 = new Font("Serif", 1, 32);
	Font font2 = new Font("Serif", 1, 21);
	Font font3 = new Font("Serif", 1, 12);
	BufferedImage startS, startUS, settingsS, settingsUS;
	boolean stSelected = false;
	boolean seSelected = false;
	String version ="1.1.4";
	String intro1 = "Welcome to my game made for Ludum Dare 28!";
	String intro2 = "The object of this game is to make you way though the maze, retrive the key and bring";
	String intro3 = "the key back to the starting area. Beware! There are gaurds thoughout the maze and if";
	String intro4 = "they spot you the game is over! You only have one gun that contains only 1 bullet";
	String intro5 = "(resets every round), so if you get in a sticky situation you can take out a gaurd and";
	String intro6 = "continue on your journey. How many rounds can you make it through? Try and find out.";
	
	SettingsScreen settings;

	public BeginningScreen(String sName) 
	{
		super(sName);
		settings = new SettingsScreen("Settings");
		try
		{
			startS = ImageIO.read(getClass().getResource("/StartSelectedButton.png"));
			startUS = ImageIO.read(getClass().getResource("/StartUnSelectedButton.png"));
			settingsS = ImageIO.read(getClass().getResource("/SettingsSelectedButton.png"));
			settingsUS = ImageIO.read(getClass().getResource("/SettingsUnSelectedButton.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		g.setFont(font1);
		g.drawString(intro1, 50, 50);
		g.setFont(font2);
		g.setColor(Color.BLACK);
		g.drawString(intro2, 25, 150);
		g.drawString(intro3, 25, 175);
		g.drawString(intro4, 25, 200);
		g.drawString(intro5, 25, 225);
		g.drawString(intro6, 25, 250);
		g.setFont(font3);
		g.drawString("Version : " + version, 0,590);
		if (stSelected)
		{
			g.drawImage(startS, 300, 450, 200, 40, null);
		}
		else
		{
			g.drawImage(startUS, 300, 450, 200, 40, null);
		}
		
		if (seSelected)
		{
			g.drawImage(settingsS, 300, 500, 200, 40, null);
		}
		else
		{
			g.drawImage(settingsUS, 300, 500, 200, 40, null);
		}
	}

	public void onClick(int x, int y)
	{
		if ((x > 300) && (x < 500) && (y > 450) && (y < 490))
		{
			ScreenManager.manager.newScreen(new MainScreen("MainScreen" , settings.getSpeed()));
			ScreenManager.manager.setCurrentScreen("MainScreen");
		}
		if ((x > 300) && (x < 500) && (y > 500) && (y < 540))
		{
			ScreenManager.manager.newScreen(settings);
			ScreenManager.manager.setCurrentScreen("Settings");
		}
	}

	public void onMouseMove(int x, int y)
	{
		if ((x > 300) && (x < 500) && (y > 450) && (y < 490))
		{
			stSelected = true;
		}
		else
		{
			stSelected = false;
		}
		
		if ((x > 300) && (x < 500) && (y > 500) && (y < 540))
		{
			seSelected = true;
		}
		else
		{
			seSelected = false;
		}
	}
}