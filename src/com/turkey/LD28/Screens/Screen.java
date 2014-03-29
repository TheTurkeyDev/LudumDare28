package com.turkey.LD28.Screens;

import java.awt.Graphics;
import java.util.ArrayList;

import com.turkey.LD28.Screens.SubScreens.SubScreen;

public class Screen
{
	private String name;
	private ArrayList<SubScreen> subScreens = new ArrayList<SubScreen>();

	public Screen(String sName)
	{
		name = sName;
	}

	public void paint(Graphics g)
	{
		for(SubScreen ss: subScreens)
			ss.paint(g);
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
		for(SubScreen ss: subScreens)
			ss.OnKeyEvent(Input, pressed);
	}

	public void onClick(int x, int y)
	{
		for(SubScreen ss: subScreens)
			ss.onClick(x, y);
	}

	public void onMouseMove(int x, int y)
	{
		for(SubScreen ss: subScreens)
			ss.onMouseMove(x, y);
	}
	
	public void displaySubScreen(SubScreen ss)
	{
		subScreens.add(ss);
	}
	
	public void removeSubScreen(SubScreen ss)
	{
		subScreens.remove(ss);
	}
	
	public boolean toggleSubScreen(SubScreen ss)
	{
		SubScreen same = null;
		for(SubScreen sub: subScreens)
			if(sub.getName().equalsIgnoreCase(ss.getName()))
				same = sub;
		if(same != null)
		{
			System.out.println("here");
			subScreens.remove(same);
			return false;
		}
		else
		{
			System.out.println("q");
			subScreens.add(ss);
			return true;
		}
	}
}