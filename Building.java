package Cityscape;

import java.awt.*;
import java.util.*;

public class Building 
{
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Building(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public void paint(Graphics2D g2d, Building building, boolean done, boolean[] lights, int lightcounter)
	{
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(building.getX(), building.getY(), building.getWidth(), building.getHeight());
		for (int i = building.getX() + 4; i <= building.getX() + 91; i = i + 29)
		{
			for (int j = 605; j >= 640 - building.getHeight(); j = j - 30)
			{
				Random ran = new Random();
				if (done == true)
				{
					lights[lightcounter] = ran.nextBoolean();
				}
				if (lights[lightcounter] == true)
				{
					g2d.setColor(Color.YELLOW);
				}
				else
				{
					Color windowBlue = new Color(25, 25, 112);
					g2d.setColor(windowBlue);
				}
				g2d.fillRect(i, j, 25, 25);
				lightcounter++;
			}
		}
	}
}