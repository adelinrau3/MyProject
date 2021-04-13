package myProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MazePanel extends JPanel implements ActionListener{
	
	static final int Screen_Width = 525;
	static final int Screen_Height = 525;
	static final int Unit_Size = 25;
	static final int Game_Units = (Screen_Width*Screen_Height)/(Unit_Size*Unit_Size);
	static boolean Game_Over = false;
	static int Delay = 250;
	static int x = 0;
	static int y = 25;
	static final int Length = 3;
	int[][] a = new int[Game_Units][Game_Units];
	static int k = 0;
	char direction = ' ';
	String running = "";
	Timer timer;
	
	final int obs1y[] = {1*Unit_Size, 2*Unit_Size, 3*Unit_Size, 0};
	final int obs1x = 9*Unit_Size;
	
	final int obs2y[] = {19*Unit_Size, 18*Unit_Size, 17*Unit_Size, 0};
	final int obs2x = 11*Unit_Size;
	
	public MazePanel() {
		
		this.setPreferredSize(new Dimension(Screen_Width,Screen_Height));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		
		startGame();
		
	}
	
	public void startGame()
	{
		running = "play";
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

		if(running.equals("play"))
		{
			/*for(int i=0;i<Screen_Height/Unit_Size;i++) {
				gph.drawLine(i*Unit_Size, 0, i*Unit_Size, Screen_Height);
				gph.drawLine(0, i*Unit_Size, Screen_Width, i*Unit_Size);
			}*/
			
			gph.setColor(Color.red);
			gph.fillOval(x, y, Unit_Size, Unit_Size);
			
			gph.setColor(Color.GRAY);
			
			for(int i=0;i<Screen_Height/Unit_Size;i++)
				for(int j=0;j<Screen_Height/Unit_Size;j++)			
					if((i == 0 && j != 1) || (i == Screen_Height/Unit_Size-1 && j != 19) || j == 0 || j == Screen_Width/Unit_Size-1) 
						a[i][j] = 1;
			
			for(int i=2; i<6; i++)
				a[1][i] = 1;
			
			for(int i=2; i<6; i++)
				a[3][i] = 1;
			
			for(int i=5; i<8; i++)
				a[i][2] = 1;
			
			for(int i=5; i<8; i++)
				a[i][3] = 1;
			
			for(int i=1; i<14; i++)
				a[7][i] = 1;
			
			for(int i=3; i<6; i++)
				for(int j=5; j<7; j++)
					a[i][j] = 1;
			
			for(int i=2; i<4; i++)
				a[i][7] = 1;
			
			for(int i=2; i<8; i++)
				a[i][8] = 1;
			
			for(int i=10; i<17; i++)
				a[2][i] = 1;
			
			for(int i=3; i<6; i++)
				a[i][10] = 1;
			
			for(int i=3; i<6; i++)
				a[i][11] = 1;
			
			for(int i=4; i<8; i++)
				a[i][14] = 1;
			
			a[4][13] = 1;
			
			for(int i=16; i<20; i++)
				a[7][i] = 1;
			
			for(int i=3; i<7; i++)
				a[i][16] = 1;
			
			for(int i=1; i<4; i++)
				a[i][18] = 1;
			
			for(int i=5; i<7; i++)
				a[i][17] = 1;
			
			for(int i=5; i<7; i++)
				a[i][18] = 1;
			
			for(int i=1; i<20; i++)
				a[13][i] = 1;
			
			a[13][3] = 0;
			
			for(int i=1; i<3; i++)
				a[14][i] = 1;
			
			for(int i=7; i<10; i++)
				a[14][i] = 1;
			
			for(int i=7; i<10; i++)
				a[15][i] = 1;
			
			for(int i=4; i<19; i++)
				a[15][i] = 1;
			
			a[15][6] = 0;
			a[15][10] = 0;
			a[15][12] = 0;
			
			for(int i=2; i<10; i++)
				a[16][i] = 1;
			
			a[16][11] = 1;
			a[16][13] = 1;
			a[16][15] = 1;
			
			a[17][9] = 1;
			a[17][11] = 1;
			a[17][13] = 1;
			a[17][15] = 1;
			
			for(int i=17; i<20; i++)
				a[17][i] = 1;
			
			for(int i=2; i<8; i++)
				a[18][i] = 1;
			
			a[18][11] = 1;
			a[18][15] = 1;
			a[18][19] = 1;
			
			for(int i=7; i<18; i++)
				a[19][i] = 1;
			
			a[19][9] = 0;
			a[19][14] = 0;
			
			for(int i=0;i<Screen_Height/Unit_Size;i++)
				for(int j=0;j<Screen_Width/Unit_Size;j++)
					if(a[i][j] == 1)
						gph.fillRect(i*Unit_Size, j*Unit_Size, Unit_Size, Unit_Size);
			
			
			for(int i = 0; i < Length; i++)
			{
					gph.setColor(Color.green);
					gph.fillRect(obs1x, obs1y[i], Unit_Size, Unit_Size);
					gph.fillRect(obs2x, obs2y[i], Unit_Size, Unit_Size);
			}

		}
		else 
			gameOver(gph);
	}

	public void move()
	{
		try {
			switch(direction)
			{
			case 'U':
				if (a[x/Unit_Size][y/Unit_Size - 1] != 1)
					y = y - Unit_Size;
				break;
			case 'D':
				if (a[x/Unit_Size][y/Unit_Size + 1] != 1)
					y = y + Unit_Size;
				break;
			case 'R':
				if (a[x/Unit_Size + 1][y/Unit_Size] != 1)
					x = x + Unit_Size;
				break;
			case 'L':
				if (a[x/Unit_Size - 1][y/Unit_Size] != 1)
					x = x - Unit_Size;
				break;	
			}
			direction = ' ';
		}catch (ArrayIndexOutOfBoundsException e) {

		}
		
	}
	
	
	public void checkCollisions()
	{
		
		if(x/Unit_Size == 20 && y/Unit_Size == 19) running = "win";
		
		for(int i=0; i<Length-1; i++) 
		{
			if(x == obs1x && y == obs1y[i])
				running = "lose";

			if(x == obs2x && y == obs2y[i])
				running = "lose";
		}
	}
	
	public void gameOver(Graphics gph)
	{
		if(running.equals("win"))
		{
			gph.setColor(Color.YELLOW);
			gph.setFont(new Font("Helvetica", Font.BOLD, 70));
			FontMetrics fontMetrics = getFontMetrics(gph.getFont());
			gph.drawString("WIN!", (Screen_Width - fontMetrics.stringWidth("WIN!"))/2, Screen_Height/2);
		}
		else if(running.equals("lose"))
		{
			gph.setColor(Color.YELLOW);
			gph.setFont(new Font("Helvetica", Font.BOLD, 70));
			FontMetrics fontMetrics = getFontMetrics(gph.getFont());
			gph.drawString("LOSE!", (Screen_Width - fontMetrics.stringWidth("LOSE!"))/2, Screen_Height/2);
		}
		gph.setColor(Color.YELLOW);
		gph.setFont(new Font("Helvetica", Font.BOLD, 25));
		FontMetrics fontMetrics2 = getFontMetrics(gph.getFont());
		gph.drawString("To retry press SPACE", (Screen_Width - fontMetrics2.stringWidth("To retry press SPACE"))/2, Screen_Height-gph.getFont().getSize());
		
		Game_Over = true;
	}
	int count1 = 0;
	boolean ok1 = false;
	public void moveObstacle1(int[] y)
	{
		for (int i = Length;i > 0; i--)
			y[i]=y[i-1];

		char direction;

		if(ok1) direction='U';
		else direction='D';
		count1++;
		
		switch(direction)
		{
		case 'U':
			y[0] = y[0] - Unit_Size;
			break;
		case 'D':
			y[0] = y[0] + Unit_Size;
			break;
		}
		if(count1 == 18 ) 
		{
			ok1=!ok1; 
			count1=0;
		}

	}

	boolean ok2 = false;
	int count2 = 0;
	public void moveObstacle2(int[] y)
	{
		for (int i = Length;i > 0; i--)
			y[i]=y[i-1];

		char direction;

		if(ok2) direction='D';
		else direction='U';
		count2++;
		
		switch(direction)
		{
		case 'U':
			y[0] = y[0] - Unit_Size;
			break;
		case 'D':
			y[0] = y[0] + Unit_Size;
			break;
		}
		if(count2 == 18 ) 
		{
			ok2=!ok2; 
			count2																=0;
		}

	
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(running.equals("play")) 
		{
			
			checkCollisions(); 
			move();
			moveObstacle1(obs1y);
			moveObstacle2(obs2y);
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
				direction = 'L';
				break;
			case KeyEvent.VK_RIGHT:				
				direction = 'R';
				break;
			case KeyEvent.VK_DOWN:				
				direction = 'D';
				break;
			case KeyEvent.VK_UP:				
				direction = 'U';
				break;
			case KeyEvent.VK_SPACE:
				if(Game_Over == true)
				{
					Game_Over = false;
					x = 0;
					y = 25;
					direction = ' ';
					startGame();
				}
				break;
			}
		}
	}
	
	
}
