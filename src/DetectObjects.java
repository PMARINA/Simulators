//*************************************************************************//
// Screen.java            Author: Marie Petitjean        			       //
// Insert certain commands into code.txt and the little robot square       //
// will perform what is being asked:									   //
//	forwards(); -To go forwards											   //
//  backwards(); - To go backwards();					    				//
//  stop();  - To stop();				                                   //
//  setSpeed(arg0) -To set speed, note: the larger the number the slower    //
//  turnright(); - To turn right                                            //
//  turnleft(); -to turn left                                               //
/// //   - to comment                                                       //
//*************************************************************************//
import java.awt.BasicStroke; 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DetectObjects extends JPanel {	
	public String line;		
	public JButton refresh;
	public int stuff=0,k=0,obstacles=0;
	public int[] array,arguments;
	public boolean f=false,b=false,left=false,win=false;
	public int direction=0,t=1000;
	public int x=30,y=420,x1=215,a=25,b1=10;
	Random gen = new Random();
	public int r1 =gen.nextInt(400),r2=gen.nextInt(400),r3=gen.nextInt(300),r4=gen.nextInt(300),twor1 =gen.nextInt(400),twor2=gen.nextInt(400),twor3=gen.nextInt(300),twor4=gen.nextInt(300),
			threer1 =gen.nextInt(400),threer2=gen.nextInt(400),threer3=gen.nextInt(200),threer4=gen.nextInt(200);

	public DetectObjects() throws IOException{
		setSize(500,500);
		setLayout(null);

		get();
		
	/*	refresh = new JButton();
		refresh.setBounds(50,50,50,50);
		this.add(refresh);
		refresh.addActionListener(
		        new ActionListener() 
		        {
		           public void actionPerformed( ActionEvent event )
		           
		              {
		           			
							try {
								get();
							} catch (IOException e) {
								
							}
		           }
		           
		        } 

		     ); */

	}
	public DetectObjects(int a) throws IOException{
		obstacles=a;
		setSize(500,500);
		setLayout(null);
		get();
	}
	
	public void get() throws IOException{
		array = new int[40];				//makes an array of 10.... can be adjusted, depends on how many lines of code you want (it is ok if you have less lines than size of array)
		arguments = new int[40];
		
		
		BufferedReader in = new BufferedReader(new FileReader("Code.txt")); //Reads text form test document
		while((line = in.readLine()) != null)	//while its not null
		{
			if(line.contains("stop();")){			
				array[stuff]=0;					// when stop(); is called, it sets 1 to its spot in the order
				check();						//calls check which will go through the switch
			}
			else if(line.contains("forwards();")){
			  array[stuff]=1;					// similar to above
			  check();
		
		   }
			
		   else if(line.contains("backwards();")){
			  array[stuff]=2;					//similar to above
			  check();
		   }
		   else if(line.contains("turnleft();")){
			   array[stuff]=3;
			   check();
		   }
		   else if(line.contains("turnright();")){
			   array[stuff]=4;
			   check();
		   }
		   else if(line.contains("setSpeed")==true){
			   array[stuff]=5;
			   arguments[stuff]=Integer.parseInt(line.substring(9,line.length()-2));
			   
			   check();
		   }
		   else if(line.contains("//")==true){
			   
			   System.out.println("COMMENT: "+ line.substring(2));
			   stuff--;
		   }
		   else{
			   stuff--;							// when jibberish is entered, the spot in the array is saved for a useful peice of code
			   check();
		   }
		stuff++;								// next step 
		}
		in.close();		
		
		for (int i=0; i<array.length; i++){		//prints the array, just for reference
			System.out.print(array[i]+" ");		
		}
		System.out.print("\n");
		for (int i=0; i<array.length; i++){		//prints the array, just for reference
			System.out.print(arguments[i]+" ");		
		}	
	}

	public void check(){
		
		switch(array[k]){					// switch on the number of inside array[k]
			case 0:									// 0 is stop
				new java.util.Timer().schedule( 	// waits 1 second 
				        new java.util.TimerTask() {
				            public void run() {
				            	System.out.println("Case 0- Stop");	
				            	if(k<array.length-1){		// if there are still steps left in the array, it goes on to the next step and performs check again
				            	k++;
				            	check();
				            	}
				            }
				        }, 
				        t*3
				);
			
			break;
			case 1:
				
				new java.util.Timer().schedule( 	//waits 1 sec
				        new java.util.TimerTask() {
				            public void run() {
				            	
				            	System.out.println("Case 1 - go foward"); //Only goes through once when called
								f=true;										//this is what allows the graphics to run
				            }
				        }, 
				        t
				);
			break;
			case 2: 

				new java.util.Timer().schedule( // waits 1 sec
				        new java.util.TimerTask() {
				            public void run() {
				            	System.out.println("Case 2 - go backwards");
								f=false;		// tells graphics to not go forward and go backwards
								b=true;
				            	
				            }
				        }, 
				        t
				 );
				
				break;	
			case 3: 
				new java.util.Timer().schedule( // waits 1 sec
				        new java.util.TimerTask() {
				            public void run() {
				            	System.out.println("Case 3 - turn left");
				            		direction--;
				            		if(direction==-4){
				            			direction=0;
				            		}
				            		if(k<array.length-1){
				            			k++;
				            			check();
				            		}
				            		
				            	
				            }
				        }, 
				        t/2
				 );
				break;
			case 4: 
				new java.util.Timer().schedule( // waits 1 sec
				        new java.util.TimerTask() {
				            public void run() {
				            	System.out.println("Case 4 - turn right");
				            		direction++;
				            		if(direction==4){
				            			direction=0;
				            		}
				            		if(k<array.length-1){
				            			k++;
				            			check();
				            		}
				            		
				            	
				            }
				        }, 
				        t/2
				 );
				break;
			case 5: 
				System.out.println("Case 5 - set Speed");
				t = arguments[k];
				
				if(k<array.length-1){
        			k++;
        			check();
        		}
				break;
		}
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		int ax = 480,ay=0,bx=480,by=100;
		boolean dead=false;
		
		graphics.setColor(Color.BLACK);
		graphics.drawLine(ax,ay,bx,by);
		
	
		int m =0;			
		graphics.setColor(Color.GREEN);
		graphics.fillRect(0,460,100,20);
		graphics.setColor(Color.RED);
		graphics.fillRect(480,0,20,100);//useful for determining how far we want each to travel
		
		graphics.setStroke(new BasicStroke(2));
		

			
		switch(obstacles){
		case 0: 
			graphics.setColor(Color.CYAN);
			graphics.drawRect(x, y, 50, 50);
			break;
		case 1: 
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, 300, 300);
			
			if((x<=0+300 && x>=0 && y<=0+300 && y>=0)||(x<=0+300 && x>=0 && y+25<=0+300 && y+25>=0)){
				f=false;
				graphics.drawImage(Toolkit.getDefaultToolkit().getImage("dead.png"),x,y,this);
				dead =true;
			}
			else{
				graphics.setColor(Color.CYAN);
				graphics.drawRect(x, y, 50, 50);
			}

		break;
		case 2:
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 300, 150, 50);
			graphics.fillRect(300, 400, 200, 80);
			graphics.fillRect(450,180,50,250);
			graphics.fillRect(250,100,100,200);
			graphics.fillRect(0,0,100,100);
				
			if((x<=0+150 && x>=0 && y>=300 && y<=300+50)|| (x+25<=0+150 && x+25>=0 && y+50>=300 && y+50<=300+50)||(x<=0+100 && x>=0 && y>=0 && y<=100)||
					(x+25<=250+100 && x+25>=250 && y>=100 && y<=100+200)||(x+50<=250+100 && x+50>=250 && y+25>=100 && y+25<=100+200)||(x+25<=250+100 && x+25>=250 && y+50>=100 && y+50<=100+200)||
					(x<=250+100 && x>=250 && y+50>=100 && y+50<=100+200)||(x+50<=300+200 && x+50>=300 && y+25>=400 && y+25<=400+80) || (x+25<=500 && x+25>300 && y+50>=400 && y+50<480)||
					(x<=250+100 && x>=250 && y>=100 && y<=100+200)||(x+50<=250+100 && x+50>=250 && y+25>=100 && y+25<=100+200)||(x+50<=450+50 && x+50>=450 && y+25>=180 && y+25<=180+250) || (x+25<=450+50 && x+25>=450 && y+50>=180 && y+50<=180+250)){
				f=false;
				graphics.drawImage(Toolkit.getDefaultToolkit().getImage("dead.png"),x,y,this);
				dead =true;
			}
			else{
			graphics.setColor(Color.CYAN);
			graphics.drawRect(x, y, 50, 50);	
			}
		break;
		case 3: 
			//System.out.println("Random Map");
			graphics.setColor(Color.CYAN);
			graphics.drawRect(x, y, 50, 50);
			graphics.setColor(Color.black);
			
			graphics.fillRect(r1, r2, r3, r4);
			graphics.fillRect(twor1,twor2,twor3,twor4);
			graphics.fillRect(threer1,threer2,threer3,threer4);
		break;
		}
		
		repaint();
		
		if(x+25<=bx+50 && x+25>=ax-50 && y<=ay+50){
			System.out.println("you win");
			f=false;
		} 
		
		if(dead==false){
		graphics.setColor(Color.YELLOW);
		switch(direction){
		case 0: graphics.fillRect(x+15,y-5,a,b1);
		break;
		case 3:
		case -1: graphics.fillRect(x-5,y+15,b1,a);
		break;
		case 2:
		case -2: graphics.fillRect(x+15, y+45, a, b1);
		break;
		case 1:
		case -3: graphics.fillRect(x+45, y+14,b1, a);
		break;
		
		}
		}
		
		
		if(f==true){				// this is for going forward
			int a =50;				// a can be changed, depending on how much you want the foward to increase
			while(m<a){
				if(direction==0){
				y-=1;
				}
				else if(direction ==1||direction ==-3){
					x+=1;
				}
				else if(direction ==2||direction ==-2){
					y+=1;
				}
				else if(direction==3||direction==-1){
					x-=1;
				}
			m++;
			repaint();
			}
			if(m==a){				// once the rectangle has traveled the 50 things
				f=false;				// stops the graphics from changing
				if(k<array.length-1){	// moves onto the next step 
					k++;
					check();
				}
			}
		}
		else if(f==false && b==true){  // this is for going backwards
			int a =50;
			while(m<a){
				if(direction==0){
				y+=1;
				}
				else if(direction==1 || direction ==-3){
					x-=1;
				}
				else if(direction==2 || direction==-2){
					y-=1;
				}
				else if(direction==3 || direction ==-1){
					x+=1;
				}
			m++;
			repaint();
			}					// similar to above
			if(m==a){
				b=false;
				if(k<array.length-1){
				k++;
				check();
				}
			}
		}
		
		
	}
	
	
}

