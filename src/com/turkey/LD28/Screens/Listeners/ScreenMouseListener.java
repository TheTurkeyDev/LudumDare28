package com.turkey.LD28.Screens.Listeners;

import com.turkey.LD28.Screens.ScreenManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScreenMouseListener extends MouseAdapter
{
	public void mouseClicked(MouseEvent e)
	{
		int xloc = e.getX();
		int yloc = e.getY();
		ScreenManager.manager.getCurrentScreen().onClick(xloc, yloc);
	}
}