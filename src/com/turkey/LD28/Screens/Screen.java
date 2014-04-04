package com.turkey.LD28.Screens;

import java.awt.Graphics;
import java.util.ArrayList;

import com.turkey.LD28.Screens.SubScreens.SettingsSubScreen;
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
		if(ss.getName().equalsIgnoreCase("settings"))
		{
			SettingsSubScreen sss = (SettingsSubScreen) ss;
			sss.saveSettings();
		}

	}

	public void removeAllSubScreens()
	{
		for(SubScreen ss: subScreens)
		{
			if(ss.getName().equalsIgnoreCase("settings"))
			{
				SettingsSubScreen sss = (SettingsSubScreen) ss;
				sss.saveSettings();
			}
		}
		subScreens.clear();
	}
}