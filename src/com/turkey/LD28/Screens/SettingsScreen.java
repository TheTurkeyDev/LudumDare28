package com.turkey.LD28.Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.turkey.LD28.Game.GameSettings;

public class SettingsScreen extends Screen
{
	int speed = 1;

	BufferedImage backS, backUS, addS, addUS, minusS, minusUS;
	boolean bSelected = false;
	boolean aSelected = false;
	boolean mSelected = false;
	public SettingsScreen(String name)
	{
		super(name);
		try{
		speed = GameSettings.getSettings().getSpeed();
		}catch(NullPointerException e){speed = 1;}
		try
		{
			backS = ImageIO.read(getClass().getResource("/Images/BackSelectedButton.png"));
			backUS = ImageIO.read(getClass().getResource("/Images/BackUnSelectedButton.png"));
			addS = ImageIO.read(getClass().getResource("/Images/PlusSelected.png"));
			addUS = ImageIO.read(getClass().getResource("/Images/PlusUnSelected.png"));
			minusS = ImageIO.read(getClass().getResource("/Images/MinusSelected.png"));
			minusUS = ImageIO.read(getClass().getResource("/Images/MinusUnSelected.png"));
		} catch (IOException e){System.out.println("Missing a texture in Setting screen");}
	}
	
	public void paint(Graphics g)
	{
		g.setFont(new Font("Serif", 1, 32));
		g.setColor(Color.BLACK);
		g.drawString("Movement Speed: ", 75, 315);
		g.setFont(new Font("Serif", 1, 50));
		g.drawString("" + speed, 325, 315);
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
			GameSettings.getSettings().setSpeed(speed);
			ScreenManager.manager.setCurrentScreen("BeginningScreen");
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
}
