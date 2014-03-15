package com.turkey.LD28;

import com.turkey.LD28.Screens.Listeners.ScreenKeyListener;
import com.turkey.LD28.Screens.Listeners.ScreenMouseListener;
import com.turkey.LD28.Screens.Listeners.ScreenMouseMotionListener;
import javax.swing.JApplet;

public class AppletPart extends JApplet
{
  private static final long serialVersionUID = 1L;
  private static Application app;

  public void init()
  {
    app = new Application();
    super.setSize(800, 600);
    super.setFocusable(true);
    super.requestFocusInWindow();
    super.add(app);
    super.addKeyListener(new ScreenKeyListener());
    super.addMouseListener(new ScreenMouseListener());
    super.addMouseMotionListener(new ScreenMouseMotionListener());
    app.start();
  }

  public void start()
  {
  }

  public void stop()
  {
    app.stop();
  }

  public void destroy() {
    app.stop();
  }

  public static Application getApp()
  {
    return app;
  }
}