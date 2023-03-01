package Cityscape;

import java.awt.*;
import java.awt.event.*;

public class UFO
{
	private int x;
	private int y;
	private int width;
	private int height;
	private int ix = 1;
	private int iy = 1;
	private static final int diameter = 80;
	private Color ufoColor = new Color(119, 136, 153);
	private Color beamColor = new Color(0, 255, 51, 128);
	
	private boolean right = false, left = false;
	private Boolean up = false, down = false;
	private boolean beam = false;
	
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			beam = true;
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = false;
			ix = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = false;
			ix = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			up = false;
			iy = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = false;
			iy = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			beam = false;
		}
	}
	
	public UFO(int x, int y, int width, int height)
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
	
	public boolean getBeam()
	{
		return this.beam;
	}
	
	public void paint(Graphics2D g2d, boolean moveable)
	{
		if (beam)
		{
			g2d.setColor(beamColor);
			int[] Xs = {this.getX() + 20, this.getX() + 60, this.getX() + 100, this.getX() - 20};
			int[] Ys = {this.getY() + 15, this.getY() + 15, 640, 640};
			g2d.fillPolygon(Xs, Ys, 4);
		}
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillOval(this.getX(), this.getY() + 5, this.getWidth(), this.getHeight());
		g2d.setColor(ufoColor);
		g2d.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g2d.setColor(Color.BLUE);
		g2d.fillArc(this.getX() + 20, this.getY() - 10, 40, 50, 10, 160);
		g2d.setColor(Color.YELLOW);
		if (moveable)
		{
			g2d.setColor(Color.GREEN);
		}
		g2d.fillOval(this.getX() + 27, this.getY() + 17, 6, 6);
		g2d.fillOval(this.getX() + 47, this.getY() + 17, 6, 6);
		g2d.fillOval(this.getX() + 10, this.getY() + 12, 6, 6);
		g2d.fillOval(this.getX() + 64, this.getY() + 12, 6, 6);
	}
	
	public void move(boolean moveable)
	{
		if (moveable)
		{
			ix = 0;
			iy = 0;
		}
		if (right)
		{
			this.ix = 1;
		}
		if (left)
		{
			this.ix = -1;
		}
		if (down)
		{
			this.iy = 1;
		}
		if (up)
		{
			this.iy = -1;
		}
		
		if (getX() == 0)
		{
			this.ix = 1;
		}
		if (getX() == 940)
		{
			this.ix = -1;
		}
		if (getY() == 10)
		{
			this.iy = 1;
		}
		if (getY() == 205)
		{
			this.iy = -1;
		}
		
		this.x = this.getX() + this.ix;
		this.y = this.getY() + this.iy;
	}
	
	public void collision (UFO otherUFO, boolean thisMoveable, boolean otherMoveable)
	{
		int dx = (this.getX() - otherUFO.getX()) + (this.ix - otherUFO.ix);
		int dy = (this.getY() - otherUFO.getY()) + (this.iy - otherUFO.iy);
		
		if (Math.sqrt(dx*dx + dy*dy) <= diameter)
		{
			if (thisMoveable)
			{
				otherUFO.ix *= -1;
				otherUFO.iy *= -1;
			}
			else if (otherMoveable)
			{
				this.ix *= -1;
				this.iy *= -1;
			}
			else
			{
				int tempix = this.ix;
				this.ix = otherUFO.ix;
				otherUFO.ix = tempix;
				int tempiy = this.iy;
				this.iy = otherUFO.iy;
				otherUFO.iy = tempiy;
			}
		}
	}
}