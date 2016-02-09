import java.util.Scanner;

/*
 * [PROMPT]
 * @author PMARINA
 * @version Feb 4, 2016
 */
public class PM_MazeTest {
	/**
	 * Blah Blah Blah
	 * @params param1 does this
	 * @returns returns stuff
	 * @throws noInputError no input given by the user
	 */
	private static boolean[][] maze;
	public static void initMaze(){
		Scanner sc = new Scanner(System.in);
		System.out.println("What is the size of the maze?");
		int s = sc.nextInt();
		maze = new boolean[s+2][s+2];
	}
	public static void generateMaze(){
		//Set the maze's borders
		for(int i = 0; i < maze.length; i++){
			maze[0][i] = true;
			maze[i][0] = true;
			maze[maze.length][i] = true;
			maze[i][maze.length] = true;
		}
	}
	public static void main(String[] args) {
		initMaze();
		generateMaze();
	}
}
