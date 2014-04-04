package com.turkey.LD28.Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.turkey.LD28.Game.Game;

public class EndScreen extends Screen
{
	BufferedImage restartS, restartUS;
	boolean selected = false;
	Font font1 = new Font("Serif", 1, 64);
	Font font2 = new Font("Serif", 1, 21);
	int rounds;
	String intro1 = "You Were Found!";
	String intro3 = "Play again?";

	public EndScreen(String sName)
	{
		super(sName);
		rounds = Game.instance().getRound();
		try
		{
			restartS = ImageIO.read(getClass().getResource("/RestartSelectedButton.png"));
			restartUS = ImageIO.read(getClass().getResource("/RestartUnSelectedButton.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		g.setFont(font1);
		g.drawString(intro1, 150, 50);
		g.setFont(font2);
		g.setColor(Color.BLACK);
		String intro2 = "You lasted " + rounds + " rounds!";
		g.drawString(intro2, 150, 200);
		g.drawString(intro3, 150, 250);
		if (selected)
		{
			g.drawImage(restartS, 300, 500, 200, 40, null);
		}
		else
		{
			g.drawImage(restartUS, 300, 500, 200, 40, null);
		}
	}

	public void onClick(int x, int y)
	{
		if ((x > 300) && (x < 500) && (y > 500) && (y < 540))
		{
			ScreenManager.manager.setCurrentScreen("BeginningScreen");
		}
	}

	public void onMouseMove(int x, int y)
	{
		if ((x > 300) && (x < 500) && (y > 500) && (y < 540))
		{
			selected = true;
		}
		else
		{
			selected = false;
		}
	}
}