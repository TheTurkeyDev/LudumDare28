package com.turkey.LD28.Screens.Listeners;

import com.turkey.LD28.Screens.ScreenManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ScreenMouseMotionListener extends MouseMotionAdapter
{
  public void mouseMoved(MouseEvent e)
  {
    int xloc = e.getX();
    int yloc = e.getY();
    ScreenManager.manager.getCurrentScreen().onMouseMove(xloc, yloc);
  }
}