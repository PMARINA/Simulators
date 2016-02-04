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
    private boolean[][] north;     // is there a wall to north of cell i, j
    private boolean[][] east;
    private boolean[][] south;
    private boolean[][] west;
    private boolean[][] visited;
    private boolean done = false;
    private static boolean mazeNotSolved = true;
    private static double[] playerPos = new double[2];

    public Maze(int N) {
        this.N = N;
        StdDraw.setXscale(0, N+2);
        StdDraw.setYscale(0, N+2);
        init();
        generate();
    }

    private void init() {
        // initialize border cells as already visited
        visited = new boolean[N+2][N+2];
        for (int x = 0; x < N+2; x++) {
            visited[x][0] = true;
            visited[x][N+1] = true;
        }
        for (int y = 0; y < N+2; y++) {
            visited[0][y] = true;
            visited[N+1][y] = true;
        }
        playerPos[0] = 1.5;
        playerPos[1] = 1.5;
        System.out.println("Player position 0,1: " + playerPos[0] + "," + playerPos[1]);


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
    }

    // draw the maze
    public void draw() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(N/2.0 + 0.5, N/2.0 + 0.5, 0.375);
        StdDraw.filledCircle(1.5, 1.5, 0.375);

        StdDraw.setPenColor(StdDraw.BLACK);
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                if (south[x][y]) StdDraw.line(x, y, x + 1, y);
                if (north[x][y]) StdDraw.line(x, y + 1, x + 1, y + 1);
                if (west[x][y])  StdDraw.line(x, y, x, y + 1);
                if (east[x][y])  StdDraw.line(x + 1, y, x + 1, y + 1);
            }
        }
        StdDraw.square(playerPos[0], playerPos[1], 0.375);
        StdDraw.show(1000);
    }

    @SuppressWarnings("unused")
	private static void getMazeNotSolved(){
    	mazeNotSolved = !(playerPos[0] == N/2.0 + 0.5 && playerPos[1] == N/2.0 + 0.5);
    	System.out.println(mazeNotSolved);
    }
    private static void updatePlayerPos(){
    	
    }
    // a test client
    public static void main(String[] args) {
        int N = Integer.parseInt("10"); //THIS IS THE INPUT
        Maze maze = new Maze(N);
        StdDraw.show(0);
        boolean mazeNotSolved = true;
        while(mazeNotSolved){
        	updatePlayerPos();
        	StdDraw.clear(StdDraw.WHITE);
        	maze.draw();
        	getMazeNotSolved();
        }
        
    }

}
