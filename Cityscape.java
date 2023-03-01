package Cityscape;

import javax.swing.*;
import java.awt.*;
import java.lang.Math;
import java.awt.event.*;

public class Cityscape extends JPanel
{
	private Building build1 = new Building(20, 360, 120, 280);
	private Building build2 = new Building(160, 240, 120, 400);
	private Building build3 = new Building(300, 330, 120, 310);
	private Building build4 = new Building(440, 300, 120, 340);
	private Building build5 = new Building(580, 450, 120, 190);
	private static boolean[] lights = new boolean[100];
	private static boolean done = true;
	public static int lightcounter = 0;
	
	private static UFO[] list = new UFO[4];
	private static UFO ufo1 = new UFO(randomizeX(430, 0), randomizeY(70, 10), 80, 30);
	private static UFO ufo2 = new UFO(randomizeX(430, 0), randomizeY(70, 130), 80, 30);
	private static UFO ufo3 = new UFO(randomizeX(430, 510), randomizeY(70, 10), 80, 30);
	private static UFO ufo4 = new UFO(randomizeX(430, 510), randomizeY(70, 130), 80, 30);
	
	private Car car = new Car(-60, 565);
	
	private void move()
	{
		for (int i = 0; i < list.length; i++)
		{
			for (int j = i+1; j<list.length; j++)
			{
				if (i == 3)
				{
					list[i].collision(list[j], true, false);
				}
				else if (j == 3)
				{
					list[i].collision(list[j], false, true);
				}
				else
				{
					list[i].collision(list[j], false, false);
				}
			}
		}

		ufo1.move(false);
		ufo2.move(false);
		ufo3.move(false);
		ufo4.move(true);
		
		if (car.getIX() == 1 && car.getX() - 60 >= ufo4.getX() + 39 && car.getX() - 60 <= ufo4.getX() + 41 && ufo4.getBeam())
		{
			car.move(true, ufo4.getX(), ufo4.getY());
		}
		else if (car.getIX() == -1 && car.getX() + 60 >= ufo4.getX() + 39 && car.getX() + 60 <= ufo4.getX() + 41 && ufo4.getBeam())
		{
			car.move(true, ufo4.getX(), ufo4.getY());
		}
		else
		{
			car.move(false, ufo4.getX(), ufo4.getY());
		}
	}
	
	public Cityscape()
	{
		addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
				ufo4.keyReleased(e);
			}
			
			@Override
			public void keyPressed(KeyEvent e)
			{
				ufo4.keyPressed(e);
			}
		});
		setFocusable(true);
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 1020, 640);
		build1.paint(g2d, build1, done, lights, lightcounter);
		build2.paint(g2d, build2, done, lights, lightcounter);
		build3.paint(g2d, build3, done, lights, lightcounter);
		build4.paint(g2d, build4, done, lights, lightcounter);
		build5.paint(g2d, build5, done, lights, lightcounter);
		
		ufo1.paint(g2d, false);
		ufo2.paint(g2d, false);
		ufo3.paint(g2d, false);
		ufo4.paint(g2d, true);
		
		if (car.getY() > ufo4.getY() + 35)
		{
			car.paint(g);
		}
		done = false;
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		JFrame frame = new JFrame ("Sheldon's City");
		Cityscape c = new Cityscape();
		frame.add(c);
		frame.setSize(1020, 640);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		list[0] = ufo1;
		list[1] = ufo2;
		list[2] = ufo3;
		list[3] = ufo4;

		while (true)
		{
			c.move();
			c.repaint();
			Thread.sleep(10);
		}
	}
	
	public static int randomizeX(int range, int min)
	{
		return (int)(Math.random() * range + min);
	}
	
	public static int randomizeY(int range, int min)
	{
		return (int)(Math.random() * range + min);
	}
}