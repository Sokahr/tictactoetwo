# TIC TAC TOE TWO
## Basic Requirements
We want to bring the pen-and-paper game Tic-tac-toe to the digital age,
but with a little twist: the size of the playfield should be
configurable between 3x3 and 10x10. And we also want the symbols
(usually O and X) to be configurable. Also it should be for 3 players
instead of just 2.

General Rules: https://en.wikipedia.org/wiki/Tic-tac-toe

The two users will play against each other and against the computer.
Who is starting is random. In and output should be on the console.
After each move, the new state of the playfield is displayed and the
player can enter the next position of their character one after
another. The next position should be provided in a format like 3,2.
Invalid inputs are expected to be handled appropriately.
### Requirements:
- Use the programming language you feel most comfortable with
- The game takes 3 inputs:
- Size of the playground. Valid values are between 3 and 10.
- Play character 1, 2 and 3:
- A single character for the human player 1
- A single character for the human player 2
- A single character for the computer
- These configurations should come from a file
- Software design is more important than a highly developed AI
- Please put the completed assignment on GitHub.
### Rules:
- You may use external libraries only for testing or building purposes
e.g. JUnit, Gradle, Rspec, Rake, GulpJS, etc.
- Please provide an explanation how to run your code
- Please explain your design decisions and assumptions
- Don't include executables* in your submission.
- Please write your solution in a way, that you would feel comfortable
handing this over to a colleague and deploy it into production.
- We especially look at design aspects (e.g. OOP) and if the code is
well tested and understandable.

\* this includes: asp, bat, class, cmd, com, cpl, dll, exe, fon, hta,
ini, ins, iw, jar, jsp, js, jse, pif, scr, shs, sh, vb, vbe, vbs, ws,
wsc, wsf, wsh & msi

## How to Run
You will need **JDK 1.8.0_172**
If you run the gradle script directly it is build with **Gradle 4.7**
Otherwise just use the gradle-wrapper.jar in /gradle/wrapper to execute the gradle tasks.
### Run with pre installed Gradle
Just enter gradle run in your terminal from the project root.
### Run with gradle shell or bat (preconfigured gradlewrapper)
Just enter *gradlew run* or *gradlew.bat run* in your terminal from the project root.

## Classes
To prevent crashes the game will set default values on many points were null could be set or values could mismatch 
and the user could not directly interact.

### Main
The main entry point. 

This class loads a Properties file from the file system or loads the build in properties File.
Uses the PropertyConfigurationReader to read the GameConfiguration from the Properties file.
The game-object will be initialized, configured with the GameConfiguration , and started.
After the game ends it will asks the user to play again or end the program.

### PropertyConfigurationReader / ConfigurationReader 
PropertyConfigurationReader reads a Properties-Object and creates a TicTacToeConfiguration.
If in any case the Property-Object is null or wrong configured it will create a default Configuration.
It implements ConfigurationReader as interface to be able to create a different ConfigurationReader i.E. to Parse .yml

### TicTacToeConfiguration
Is just a value container to hold the informations to create a new game. 
The Game size and a list of PlayerConfigurations which holds the information which contains the information which 
player type (human or computer) and what symbol.

### TicTacToeTwo
This is the game controller class. It creates a GameField and a List of Players according to the configuration.
When the configuration is null it will set default values for the game.
The main logic for the game flow is in here.

### GameIO / TicTacToeIOSystem
GameIO is a interface with the methods defined to communicate. The only implementation is TicTacToeIOSystem which 
uses System.out, System.err and System.in for communication.
A very important function for the GameIO is to draw the current gameField. 
Other possibilities could be a REST interface, or WebSockets, or any other implementation.

### TicTacToeGameField
Container for the gameField. Encapsulates the interactions with the GameField.
Ensures the gameField size is not larger then 10 or smaller then 3 fields.
Validates if a move is possible.

### Player classes
The abstract Player class implements the simple field to get the symbol of the player and defines the abstract method
 to make a move.
 ComputerPlayer and HumanPlayer extends from Player and implements their special functionality to make a move.
 The HumanPlayer communicates over GameIO to request the next move from the user. The ComputerPlayer just picks a 
 random not occupied field.
 The PlayerFactory creates a Player Object according to The PlayerConfiguration
 
### TicTacToeGameValidator
Contains static methods to validate the current state of the Game. 
It validates if a player won or if noMove is possible.

### PropertyKeys
Just a class with constants to refer the property keys



 

