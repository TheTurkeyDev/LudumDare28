package com.turkey.LD28;

import javax.swing.JApplet;

public class AppletPart extends JApplet
{
	private static final long serialVersionUID = 1L;
	private Application app = new Application();

	public void init()
	{
		super.setSize(800, 600);
		super.add(app);
	}

	public void start()
	{
		app.start();
	}

	public void stop()
	{
		app.stop();
	}

	public void destroy() {
		app.stop();
	}
}