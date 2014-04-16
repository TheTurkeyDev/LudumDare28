package com.turkey.LD28.Screens;

import java.util.ArrayList;

public class ScreenManager
{
	public static ScreenManager manager = new ScreenManager();
	private Screen currentScreen;
	private ArrayList<Screen> screens = new ArrayList<Screen>();

	public Screen getCurrentScreen()
	{
		if(currentScreen == null)
			currentScreen = new BeginningScreen("Begging screen");
		return currentScreen;
	}

	public void setCurrentScreen(String name)
	{
		for (Screen s : screens)
		{
			if (s.getName().equalsIgnoreCase(name))
			{
				currentScreen = s;
			}
		}
	}

	public void setCurrentScreen(Screen screen) {
		currentScreen = screen;
		if (!screens.contains(screen))
		{
			screens.add(screen);
		}
	}

	public Screen newScreen(Screen newScreen)
	{
		Screen screen = newScreen;
		screens.add(screen);
		return screen;
	}
}