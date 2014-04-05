package com.turkey.LD28.Screens.Listeners;

import com.turkey.LD28.Screens.ScreenManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ScreenMouseMotionListener implements MouseMotionListener
{
	public void mouseMoved(MouseEvent e)
	{
		int xloc = e.getX();
		int yloc = e.getY();
		ScreenManager.manager.getCurrentScreen().onMouseMove(xloc, yloc);
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		
	}
}