package com.turkey.LD28.Screens.SubScreens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.turkey.LD28.Game.Game;
import com.turkey.LD28.Game.GameSettings;
import com.turkey.LD28.Screens.Screen;

public class SettingsSubScreen extends SubScreen
{
	int speed = 1;

	BufferedImage backS, backUS, addS, addUS, minusS, minusUS, pauseScreen;
	boolean bSelected = false;
	boolean aSelected = false;
	boolean mSelected = false;
	
	public SettingsSubScreen(int xloc, int yloc, int w, int h, String n, Screen parent)
	{
		super(xloc, yloc, w, h, n, parent);
		speed = GameSettings.getSettings().getSpeed();
		try
		{
			backS = ImageIO.read(getClass().getResource("/BackSelectedButton.png"));
			backUS = ImageIO.read(getClass().getResource("/BackUnSelectedButton.png"));
			addS = ImageIO.read(getClass().getResource("/PlusSelected.png"));
			addUS = ImageIO.read(getClass().getResource("/PlusUnSelected.png"));
			minusS = ImageIO.read(getClass().getResource("/MinusSelected.png"));
			minusUS = ImageIO.read(getClass().getResource("/MinusUnSelected.png"));
			pauseScreen = ImageIO.read(getClass().getResource("/PauseScreen.png"));
		} catch (IOException e){e.printStackTrace();}
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(pauseScreen, x, y, width, height, null);
		g.setFont(new Font("Serif", 1, 28));
		g.setColor(Color.BLACK);
		g.drawString("Movement Speed: ", 110, 310);
		g.setFont(new Font("Serif", 1, 28));
		g.drawString("" + speed, 335, 310);
		if(bSelected)
		{
			g.drawImage(backS, 300, 450, 200, 50, null);
		}
		else
		{
			g.drawImage(backUS, 300, 450, 200, 50, null);
		}
		
		if(mSelected)
		{
			g.drawImage(minusS, 380, 280, 40, 40, null);
		}
		else
		{
			g.drawImage(minusUS, 380, 280, 40, 40, null);
		}
		
		if(aSelected)
		{
			g.drawImage(addS, 430, 280, 40, 40, null);
		}
		else
		{
			g.drawImage(addUS, 430, 280, 40, 40, null);
		}
	}
	
	public void onClick(int x, int y)
	{
		if ((x > 430) && (x < 470) && (y > 280) && (y < 320))
		{
			speed++;
			if(speed > 5)
			{
				speed = 5;
			}
		}
		if ((x > 380) && (x < 420) && (y > 280) && (y < 320))
		{
			speed--;
			if(speed < 1)
			{
				speed = 1;
			}
		}
		if ((x > 300) && (x < 500) && (y > 450) && (y < 490))
		{
			PauseSubScreen pss = new PauseSubScreen(100, 100, 600, 400, "pause", parent);
			parent.removeSubScreen(this);
			parent.displaySubScreen(pss);
		}
	}
	
	public void onMouseMove(int x, int y)
	{
		bSelected = false;
		aSelected = false;
		mSelected = false;
		if ((x > 430) && (x < 470) && (y > 280) && (y < 320))
		{
			aSelected = true;
		}
		if ((x > 380) && (x < 420) && (y > 280) && (y < 320))
		{
			mSelected = true;
		}
		if ((x > 300) && (x < 500) && (y > 450) && (y < 490))
		{
			bSelected = true;
		}
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public void saveSettings()
	{
		GameSettings gs = GameSettings.getSettings();
		gs.setSpeed(speed);
		Game.instance().updateSettings();
	}

}
