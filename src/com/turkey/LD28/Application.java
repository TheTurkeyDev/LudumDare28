package com.turkey.LD28;

import com.turkey.LD28.Game.GameSettings;
import com.turkey.LD28.Screens.BeginningScreen;
import com.turkey.LD28.Screens.ScreenManager;
import com.turkey.LD28.Screens.Listeners.ScreenKeyListener;
import com.turkey.LD28.Screens.Listeners.ScreenMouseListener;
import com.turkey.LD28.Screens.Listeners.ScreenMouseMotionListener;

import java.awt.Component;
import java.awt.Graphics;

public class Application extends Component implements Runnable
{
	private static final long serialVersionUID = 1L;
	private Thread thread;
	public boolean running = false;

	public synchronized void start()
	{
		ScreenManager.manager.newScreen(new BeginningScreen("BeginningScreen"));
		ScreenManager.manager.setCurrentScreen("BeginningScreen");
		super.setFocusable(true);
		super.requestFocusInWindow();
		super.addKeyListener(new ScreenKeyListener());
		super.addMouseListener(new ScreenMouseListener());
		super.addMouseMotionListener(new ScreenMouseMotionListener());
		new GameSettings();
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop()
	{
		running = false;
		try
		{
			thread.join(); } catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public void paint(Graphics g) {
		ScreenManager.manager.getCurrentScreen().paint(g);
	}

	public void update()
	{
		ScreenManager.manager.getCurrentScreen().update();
	}

	public void run()
	{
		long time = System.nanoTime();
		while (running)
		{
			repaint();
			if (System.nanoTime() - time > 15000000L)
			{
				update();
				time = System.nanoTime();
			}
		}
	}
}