package com.turkey.LD28.Screens.Listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.turkey.LD28.Screens.ScreenManager;

public class ScreenKeyListener extends KeyAdapter
{
  String key;

  public void keyReleased(KeyEvent e)
  {
    char keychar = e.getKeyChar();
    String typedletter = Character.toString(keychar);
    ScreenManager.manager.getCurrentScreen().OnKeyEvent(typedletter, Boolean.valueOf(false));
  }

  public void keyPressed(KeyEvent e) {
    char keychar = e.getKeyChar();
    String typedletter = Character.toString(keychar);
    ScreenManager.manager.getCurrentScreen().OnKeyEvent(typedletter, Boolean.valueOf(true));
  }

  public void keyTyped(KeyEvent e)
  {
    char keychar = e.getKeyChar();
    String typedletter = Character.toString(keychar);
    if (e.getKeyChar() == '\b')
    {
      typedletter = "delete";
    }
    if (e.getKeyChar() == '\n')
    {
      typedletter = "Enter";
    }
    ScreenManager.manager.getCurrentScreen().OnKeyEvent(typedletter, Boolean.valueOf(true));
  }
}