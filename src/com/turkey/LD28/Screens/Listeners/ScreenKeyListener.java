package com.turkey.LD28.Screens.Listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.turkey.LD28.Screens.ScreenManager;

public class ScreenKeyListener implements KeyListener
{
	String key;

	public void keyReleased(KeyEvent e)
	{
		char keychar = e.getKeyChar();
		String typedletter = Character.toString(keychar);
		ScreenManager.manager.getCurrentScreen().OnKeyEvent(typedletter, false);
	}

	public void keyPressed(KeyEvent e) {
		char keychar = e.getKeyChar();
		String typedletter = Character.toString(keychar);
		ScreenManager.manager.getCurrentScreen().OnKeyEvent(typedletter, true);
	}

	public void keyTyped(KeyEvent e)
	{
		char keychar = e.getKeyChar();
		String typedletter = Character.toString(keychar);
		if (keychar == '\b')
		{
			typedletter = "delete";
		}
		else if (keychar == '\n')
		{
			typedletter = "Enter";
		}
		else if(keychar == '1')
		{
			typedletter = "esc";
		}
		ScreenManager.manager.getCurrentScreen().OnKeyEvent(typedletter, true);
	}
}