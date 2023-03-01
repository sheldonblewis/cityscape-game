package Cityscape;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class Car
{
	BufferedImage carpic = null;
	public int x;
	public int y;
	public int ix = 1;
	
	public Car(int x, int y)
	{
		this.x = x;
		this.y = y;
		try
		{
			//Replace path with the path to "mater.png" in your local. Uncomment line 22.
			//carpic = ImageIO.read(new File("C:\\Users\\shelb\\eclipse-workspace\\Cityscape\\src\\Cityscape\\mater.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int getIX()
	{
		return this.ix;
	}
	
	public void paint(Graphics g)
	{
		if (ix == 1)
		{
			g.drawImage(carpic, this.getX(), this.getY(), -120, 75, null);
		}
		else
		{
			g.drawImage(carpic, this.getX(), this.getY(), 120, 75, null);
		}
	}
	
	public void move(boolean caught, int ufox, int ufoy)
	{
		if (caught)
		{
			if (ix == 1)
			{
				this.x = ufox + 100;
			}
			else
			{
				this.x = ufox - 20;
			}
			if (y > ufoy + 35)
			{
				this.y--;
			}
		}
		
		else if (caught == false && this.getY() < 565)
		{
			y++;
		}
		
		else
		{
			if (this.getX() == -120)
			{
				this.ix = 1;
				this.x = -60;
			}
			else if (this.getX() == 1140)
			{
				this.ix = -1;
				this.x = 1080;
			}
			this.x = this.getX() + this.ix;
		}
	}
}
