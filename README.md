# Simulator
The simulator takes simple commands which tells the robot what to do in the simulation. The simulator has been put 
into a game fomat were you start at the green box and you have to put in commands in order to get to the red box while
avoid the black obstacles which would kill you. There are three levels, easy, medium and hard. Type the commands in the
code.txt file, everytime you make a change you must restart the program. 

Commands: 
forwards();        //goes forwards one unit

backwards();       //goes backwards one unit

turnright();       //turns the front of the robot 90 degress to the right 

turnleft();       // turns the front of the robot 90 degrees to the left 

setSpeed(int);    // sets the speed NOTE: the higher the number the slower the bot goes DEFAULT SPEED: 1000

stop();           // pauses the bot for three seconds

setMoveDistance(int) // sets the amount of distance moved in each interval (unit thingy ?) 

