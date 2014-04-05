package com.turkey.LD28.Screens.Listeners;

import com.turkey.LD28.Screens.ScreenManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ScreenMouseListener implements MouseListener
{
	public void mouseClicked(MouseEvent e)
	{
		int xloc = e.getX();
		int yloc = e.getY();
		ScreenManager.manager.getCurrentScreen().onClick(xloc, yloc);
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		//TODO: use for drag move subsclasses around screen
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		//TODO: use for drag move subsclasses around screen
	}
}