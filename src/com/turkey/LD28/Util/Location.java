package com.turkey.LD28.Util;

public class Location
{
  private int x;
  int y;

  public Location(int xloc, int yloc)
  {
    x = xloc;
    y = yloc;
  }

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }

  public Location add(int addx, int addy)
  {
    return new Location(x + addx, y + addy);
  }
}