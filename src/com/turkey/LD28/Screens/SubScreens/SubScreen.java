package com.turkey.LD28.Screens.SubScreens;

import java.awt.Color;
import java.awt.Graphics;

public class SubScreen
{

	protected int x, y, width, height;
	protected String name;

	public SubScreen(int xloc, int yloc, int w, int h, String n)
	{
		x = xloc;
		y = yloc;
		width = w;
		height = h;
		name = n;
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}

	public void update()
	{
	}

	public String getName()
	{
		return name;
	}

	public void OnKeyEvent(String Input, Boolean pressed)
	{

	}

	public void onClick(int x, int y)
	{

	}

	public void onMouseMove(int x, int y)
	{

	}
}