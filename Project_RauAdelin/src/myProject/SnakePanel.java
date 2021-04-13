package myProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import java.util.Random;

@SuppressWarnings("serial")
public class SnakePanel extends JPanel implements ActionListener
{
	static final int Screen_Width = 600;
	static final int Screen_Height = 600;
	static final int Unit_Size = 25;
	static final int Game_Units = (Screen_Width*Screen_Height)/(Unit_Size*Unit_Size);
	static int Delay = 250;
	static boolean Game_Over = false;
	final int x[] = new int[Game_Units];
	final int y[] = new int[Game_Units];
	int bodyParts = 6;
	int foodEaten;
	int foodX;
	int foodY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	
	SnakePanel()
	{
		random = new Random();
		this.setPreferredSize(new Dimension(Screen_Width,Screen_Height));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	public void startGame() 
	{
		newFood();
		running = true;
		delayTimer();
	}
	
	public void delayTimer()
	{
		timer = new Timer(Delay,this);
		timer.start();
	}
	
	public void paintComponent(Graphics gph) 
	{
		super.paintComponent(gph);
		draw(gph);
	}
	
	public void draw(Graphics gph) 
	{

		if(running)
		{
			/*for(int i=0;i<Screen_Height/Unit_Size;i++) {
				gph.drawLine(i*Unit_Size, 0, i*Unit_Size, Screen_Height);
				gph.drawLine(0, i*Unit_Size, Screen_Width, i*Unit_Size);
			}*/
			
			gph.setColor(Color.green);
			gph.fillOval(foodX, foodY, Unit_Size, Unit_Size);
	
				for(int i = 0; i < bodyParts; i++)
				{
					if(i==0)
					{
						gph.setColor(Color.red);
						gph.fillRect(x[i], y[i], Unit_Size, Unit_Size);
					}
					else
					{
						gph.setColor(Color.blue);
						gph.fillRect(x[i], y[i], Unit_Size, Unit_Size);
					}
				}
				gph.setColor(Color.pink);
				gph.setFont(new Font("Helvetica", Font.BOLD, 25));
				FontMetrics fontMetrics = getFontMetrics(gph.getFont());
				gph.drawString("Score: " + foodEaten, (Screen_Width - fontMetrics.stringWidth("Score: " + foodEaten))/2, gph.getFont().getSize());
		}
		else 
			gameOver(gph);
	}
	
	public void move()
	{
		for (int i = bodyParts;i > 0; i--)
		{
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		
		switch(direction)
		{
		case 'U':
			y[0] = y[0] - Unit_Size;
			break;
		case 'D':
			y[0] = y[0] + Unit_Size;
			break;
		case 'R':
			x[0] = x[0] + Unit_Size;
			break;
		case 'L':
			x[0] = x[0] - Unit_Size;
			break;	
		}
	}
	
	public void newFood(){
		foodX = random.nextInt((int)(Screen_Width/Unit_Size))*Unit_Size;
		foodY = random.nextInt((int)(Screen_Height/Unit_Size))*Unit_Size;
	}
	
	public void checkFood() 
	{
		if((x[0] == foodX) && (y[0] == foodY))
		{
			bodyParts++;
			foodEaten++;
			
			//if(Delay > 100) Delay = Delay - 2;
		
			//delayTimer();
			newFood();
		}
	}
	
	public void checkCollisions()
	{
		for(int i = bodyParts; i > 0; i--) //head and body collision
		{
			if((x[0] == x[i]) && (y[0] == y[i]))
			{
				running = false;
			}
		}
		//borders collisions
		if(x[0] < 0) x[0] = Screen_Width;		
		if(x[0] > Screen_Width) x[0] = 0;
		if(y[0] < 0) y[0] = Screen_Height;
		if(y[0] > Screen_Height) y[0] = 0;
	}
	
	public void gameOver(Graphics gph)
	{
		gph.setColor(Color.pink);
		gph.setFont(new Font("Helvetica", Font.BOLD, 70));
		FontMetrics fontMetrics = getFontMetrics(gph.getFont());
		gph.drawString("Game Over!", (Screen_Width - fontMetrics.stringWidth("Game Over!"))/2, Screen_Height/2);
		
		gph.setColor(Color.pink);
		gph.setFont(new Font("Helvetica", Font.BOLD, 25));
		FontMetrics fontMetrics1 = getFontMetrics(gph.getFont());
		gph.drawString("Score: " + foodEaten, (Screen_Width - fontMetrics1.stringWidth("Score: " + foodEaten))/2, gph.getFont().getSize());
		
		gph.setColor(Color.pink);
		gph.setFont(new Font("Helvetica", Font.BOLD, 25));
		FontMetrics fontMetrics2 = getFontMetrics(gph.getFont());
		gph.drawString("To retry press SPACE", (Screen_Width - fontMetrics2.stringWidth("To retry press SPACE"))/2, Screen_Height-gph.getFont().getSize());
		
		Game_Over = true;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		if(running) 
		{
			move();
			checkFood();
			checkCollisions();	
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			switch(e.getKeyCode())
			{
			case KeyEvent.VK_LEFT:
				if(direction != 'R')
				{
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L')
				{
					direction = 'R';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U')
				{
					direction = 'D';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D')
				{
					direction = 'U';
				}
				break;
			case KeyEvent.VK_SPACE:
				if(Game_Over == true)
				{
					Game_Over = false;
					//Delay = 250;
					foodEaten = 0;
					bodyParts = 6;
					startGame();
				}
				break;
			}
		}
	}

}
