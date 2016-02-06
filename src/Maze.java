import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/******************************************************************************
 *  Compilation:  javac Maze.java
 *  Execution:    java Maze.java N
 *  Dependencies: StdDraw.java
 *
 *  Generates a perfect N-by-N maze using depth-first search with a stack.
 *
 *  % java Maze 62
 *
 *  % java Maze 61
 *
 *  Note: this program generalizes nicely to finding a random tree
 *        in a graph.
 *
 ******************************************************************************/

public class Maze {
    private static int N;                 // dimension of maze
    private static   boolean[][] north;     // is there a wall to north of cell i, j
    private static   boolean[][] east;
    private static   boolean[][] south;
    private static   boolean[][] west;
    private   boolean[][] visited;
    private static   boolean done = false;
    public static   String line;
    public   int forwards = 0;
    public   int right = 1;
    private static   double[] playerPos = new double[2];
    private static   String playerDir = "North";
    private static boolean dead = false;

    public Maze(int N) {
        this.N = N;
        StdDraw.setCanvasSize(1000,1000);
        StdDraw.setXscale(0, N+2);
        StdDraw.setYscale(0, N+2);
        init();
        generate();
    }

    private void init() {
        // initialize border cells as already visited
    	playerPos[0] = 1.5;
    	playerPos[1] = 1.5;
        visited = new boolean[N+2][N+2];
        for (int x = 0; x < N+2; x++) {
            visited[x][0] = true;
            visited[x][N+1] = true;
        }
        for (int y = 0; y < N+2; y++) {
            visited[0][y] = true;
            visited[N+1][y] = true;
        }


        // Initialize all walls as present
        north = new boolean[N+2][N+2];
        east  = new boolean[N+2][N+2];
        south = new boolean[N+2][N+2];
        west  = new boolean[N+2][N+2];
        for (int x = 0; x < N+2; x++) {
            for (int y = 0; y < N+2; y++) {
                north[x][y] = true;
                east[x][y]  = true;
                south[x][y] = true;
                west[x][y]  = true;
            }
        }
    }


    // generate the maze
    private void generate(int x, int y) {
        visited[x][y] = true;

        // while there is an unvisited neighbor
        while (!visited[x][y+1] || !visited[x+1][y] || !visited[x][y-1] || !visited[x-1][y]) {

            // pick random neighbor (could use Knuth's trick instead)
            while (true) {
                double r = StdRandom.uniform(4);
                if (r == 0 && !visited[x][y+1]) {
                    north[x][y] = false;
                    south[x][y+1] = false;
                    generate(x, y + 1);
                    break;
                }
                else if (r == 1 && !visited[x+1][y]) {
                    east[x][y] = false;
                    west[x+1][y] = false;
                    generate(x+1, y);
                    break;
                }
                else if (r == 2 && !visited[x][y-1]) {
                    south[x][y] = false;
                    north[x][y-1] = false;
                    generate(x, y-1);
                    break;
                }
                else if (r == 3 && !visited[x-1][y]) {
                    west[x][y] = false;
                    east[x-1][y] = false;
                    generate(x-1, y);
                    break;
                }
            }
        }
    }

    // generate the maze starting from lower left
    private void generate() {
        generate(1, 1);

/*
        // delete some random walls
        for (int i = 0; i < N; i++) {
            int x = 1 + StdRandom.uniform(N-1);
            int y = 1 + StdRandom.uniform(N-1);
            north[x][y] = south[x][y+1] = false;
        }

        // add some random walls
        for (int i = 0; i < 10; i++) {
            int x = N/2 + StdRandom.uniform(N/2);
            int y = N/2 + StdRandom.uniform(N/2);
            east[x][y] = west[x+1][y] = true;
        }
*/
     
    }
    // draw the maze
    public static void draw() {
    	if(!done){
	    	StdDraw.clear(StdDraw.WHITE);
	        StdDraw.setPenColor(StdDraw.RED);
	        StdDraw.filledCircle(N/2.0 + 0.5, N/2.0 + 0.5, 0.375);
	        StdDraw.setPenColor(StdDraw.GREEN);
	        StdDraw.filledCircle(1.5, 1.5, 0.375);
	        StdDraw.setPenColor(StdDraw.BLUE);
	        StdDraw.filledSquare(playerPos[0],playerPos[1],0.37);
	        StdDraw.setPenColor();
	        StdDraw.picture(N+1.5, 0.5, "refresh.png", 1,1);
	        for (int x = 1; x <= N; x++) {
	            for (int y = 1; y <= N; y++) {
	                if (south[x][y]) StdDraw.line(x, y, x + 1, y);
	                if (north[x][y]) StdDraw.line(x, y + 1, x + 1, y + 1);
	                if (west[x][y])  StdDraw.line(x, y, x, y + 1);
	                if (east[x][y])  StdDraw.line(x + 1, y, x + 1, y + 1);
	            }
	        }
	        StdDraw.setPenColor(StdDraw.ORANGE);
	        if(playerDir.equals("North"))StdDraw.filledRectangle(playerPos[0], 0.37+ playerPos[1], 0.18,0.0925);
	        if(playerDir.equals("South"))StdDraw.filledRectangle(playerPos[0], playerPos[1]-0.37, 0.18, 0.0925);
	        if(playerDir.equals("East"))StdDraw.filledRectangle(playerPos[0]+0.37, playerPos[1], 0.0925, 0.18);
	        if(playerDir.equals("West"))StdDraw.filledRectangle(playerPos[0]-0.37, playerPos[1], 0.0925, 0.18);
	        if(dead)StdDraw.picture((N+2)/2,(N+2)/2,"dead.png",N+2,N+2);
    	}
    	else{
    		StdDraw.clear(StdDraw.WHITE);
            StdDraw.text((N+2)/2, (N+2)/2, "Nice Job!");
    	}
        StdDraw.show(0);
    }



    // a test client
    public static  void main(String[] args) throws IOException {
        int N = 10;//Integer.parseInt(args[0]);
        Maze maze = new Maze(N);
        StdDraw.show(0);
        while(!done){
        	Maze.draw();
			if(StdDraw.mousePressed() && StdDraw.mouseX() >= N +1 && StdDraw.mouseX()<=N+2 && StdDraw.mouseY()<=1 && StdDraw.mouseY()>=0){
        		dead = false;
				playerPos[0] = 1.5;
        		playerPos[1] = 1.5;
        		playerDir = "North";
        		Maze.draw();
        		StdDraw.show(500);
				Maze.get();
        		Maze.draw();
        	}
			if((playerPos[0] == N/2.0 + 0.5)&&(playerPos[1] == N/2.0 + 0.5))done = true;
        }
        Maze.draw();
        while(true)if(StdDraw.mousePressed())System.exit(1);
    }
	public static void get() throws IOException{
		
		
		BufferedReader in = new BufferedReader(new FileReader("Code.txt")); //Reads text form test document
		while((line = in.readLine()) != null)	//while its not null
		{
			if(line.contains("stop();")){			
				System.out.println("I dont exist as a method, yet.");						//calls check which will go through the switch
			}
			else if(line.contains("forwards();")){
			  goForwards();
		
		   }
			
		   else if(line.contains("backwards();")){
			  goBack();
		   }
		   else if(line.contains("turnleft();")){
			   turnLeft();
		   }
		   else if(line.contains("turnright();")){
			   turnRight();
		   }
		   else if(line.contains("setSpeed")==true){
			   System.out.println("I dont't exist yet");
		   }
		   else if(line.contains("setMoveDistance")){
			   System.out.println("I dont exist yet either");
		   }
		   else if(line.contains("//")==true){
			   
			   System.out.println("COMMENT: "+ line.substring(2));
		   }
		   else{
			   System.out.println("This is jibberish");
		   }
			Maze.draw();
			StdDraw.show(100);// next step 
		}
		in.close();		
	}	
	public static   void goBack(){
		if(playerDir.equals("North")){
			if(south[(int)playerPos[0]][(int)playerPos[1]])dead = true;
			playerPos[1] -=1;
		}
		if(playerDir.equals("South")){
			if(north[(int)playerPos[0]][(int)playerPos[1]])dead = true;
			playerPos[1] +=1;
		}
		if(playerDir.equals("East")){
			if(west[(int)playerPos[0]][(int)playerPos[1]]) dead = true;
			playerPos[0] -=1;
		}
		if(playerDir.equals("West")){
			if(east[(int)playerPos[0]][(int)playerPos[1]])dead = true;
			playerPos[0] +=1;
		}
	}
	public static   void goForwards(){
		if(playerDir.equals("North")){
			if(north[(int)playerPos[0]][(int)playerPos[1]])dead = true;
			playerPos[1] +=1;
		}
		if(playerDir.equals("South")){
			if(south[(int)playerPos[0]][(int)playerPos[1]])dead = true;
			playerPos[1] -=1;
		}
		if(playerDir.equals("East")){
			if(east[(int)playerPos[0]][(int)playerPos[1]])dead = true;
			playerPos[0] +=1;
		}
		if(playerDir.equals("West")){
			if(west[(int)playerPos[0]][(int)playerPos[1]])dead = true;
			playerPos[0] -=1;
		}
	}
	public static   void turnRight(){
		if(playerDir.equals("North"))playerDir = "East";
			else{
				if(playerDir.equals("East"))playerDir = "South";
				else {
					if(playerDir.equals("South")) playerDir = "West";
					else
						if(playerDir.equals("West"))playerDir = "North";
			}
		}
	}
	public static   void turnLeft(){
		if(playerDir.equals("North"))playerDir = "West";
		else{
			if(playerDir.equals("West")) playerDir = "South";
			else{
				if(playerDir.equals("South"))playerDir = "East";
				else{
					if(playerDir.equals("East"))playerDir = "North";
				}
			}
		}
	}

}
