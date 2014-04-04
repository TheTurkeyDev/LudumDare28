package com.turkey.LD28.Screens.SubScreens;

import java.awt.Color;
import java.awt.Graphics;

import com.turkey.LD28.Screens.Screen;

public class SubScreen
{

	protected int x, y, width, height;
	protected String name;
	protected Screen parent;

	public SubScreen(int xloc, int yloc, int w, int h, String n, Screen p)
	{
		x = xloc;
		y = yloc;
		width = w;
		height = h;
		name = n;
		parent = p;
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