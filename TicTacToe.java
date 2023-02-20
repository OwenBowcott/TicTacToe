import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    // Static variables for the TicTacToe class, effectively configuration options
    // Use these instead of hard-coding ' ', 'X', and 'O' as symbols for the game
    // In other words, changing one of these variables should change the program
    // to use that new symbol instead without breaking anything
    // Do NOT add additional static variables to the class!
    static char emptySpaceSymbol = ' ';
    static char playerOneSymbol = 'X';
    static char playerTwoSymbol = 'O';

    public static void main(String[] args) {
        // TODO
        // This is where the main menu system of the program will be written.
        // Hint: Since many of the game runner methods take in an array of player names,
        // you'll probably need to collect names here.
        Scanner in = new Scanner(System.in);
        ArrayList<char[][]> history = new ArrayList<>();
        String[] names = new String[2];

        while (true) {
            System.out.println("Welcome to the game of Tic Tac Toe, choose one of the following options: ");
            System.out.println();
            System.out.println("1. Single player");
            System.out.println("2. Two player");
            System.out.println("D. Display last match");
            System.out.println("Q. Quit");
            System.out.println("What do you want to do: ");
            String input = in.nextLine();
            if (input.equals("Q") || input.equals("q")) {
                break;
            } else if (input.equals("D") || input.equals("d")) {
                runGameHistory(names, history);
            } else if (input.equals("1")) {
                System.out.println("Enter player 1 name: ");
                names[0] = in.nextLine();
                names[1] = "Computer";
                history = runOnePlayerGame(names);
            } else {
                System.out.println("Enter player 1 name: ");
                names[0] = in.nextLine();
                System.out.println("Enter player 2 name: ");
                names[1] = in.nextLine();
                history = runTwoPlayerGame(names);
            }
        }
        System.out.println("Thanks for playing. Hope you had fun!");
    }

    // Given a state, return a String which is the textual representation of the tic-tac-toe board at that state.
    private static String displayGameFromState(char[][] state) {
        // TODO
        // Hint: Make use of the newline character \n to get everything into one String
        // It would be best practice to do this with a loop, but since we hardcode the game to only use 3x3 boards
        // it's fine to do this without one.
        String game = "";
        for (int row = 0; row < state.length; row++) {
            for (int col = 0; col < state[row].length; col++) {
                if (state[row][col] == playerOneSymbol) {
                    game += " X ";
                } else if (state[row][col] == playerTwoSymbol) {
                    game += " O ";
                } else {
                    game += "   ";
                }
                if (col != 2) {
                    game += "|";
                }
            }
            if (row != 2) {
                game += "\n-----------\n";
            }
        }
        return game;
    }

    // Returns the state of a game that has just started.
    // This method is implemented for you. You can use it as an example of how to utilize the static class variables.
    // As you can see, you can use it just like any other variable, since it is instantiated and given a value already.
    private static char[][] getInitialGameState() {
        return new char[][]{{emptySpaceSymbol, emptySpaceSymbol, emptySpaceSymbol},
                {emptySpaceSymbol, emptySpaceSymbol, emptySpaceSymbol},
                {emptySpaceSymbol, emptySpaceSymbol, emptySpaceSymbol}};
    }

    // Given the player names, run the two-player game.
    // Return an ArrayList of game states of each turn -- in other words, the gameHistory
    private static ArrayList<char[][]> runTwoPlayerGame(String[] playerNames) {
        // TODO
        ArrayList<char[][]> game = new ArrayList<char[][]>();
        game.add(getInitialGameState());
        boolean first = true;
        int gameCtr = 0;
        //who plays first
        System.out.println("Tossing a coin to see who goes first!");
        int coin = (int) (Math.random() * 1) + 1;
        String player1;
        String player2;
        if (coin == 1) {
            player1 = playerNames[0];
            player2 = playerNames[1];
        } else {
            player1 = playerNames[1];
            player2 = playerNames[0];
        }
        System.out.println(player1 + " gets to go first!");

        //game
        while (true) {
            System.out.println(displayGameFromState(game.get(gameCtr)));
            char[][] gameState;
            if (first) {
                gameState = runPlayerMove(player1, playerOneSymbol, game.get(gameCtr));
                game.add(gameState);
                if (checkWin(gameState)) {
                    System.out.println(displayGameFromState(gameState));
                    System.out.println(player1 + " wins!");
                    return game;
                }
                if (checkDraw(gameState)) {
                    System.out.println(displayGameFromState(gameState));
                    System.out.println("Its a draw!");
                    return game;
                }
                first = false;
            } else {
                gameState = runPlayerMove(player2, playerTwoSymbol, game.get(gameCtr));
                game.add(gameState);
                if (checkWin(gameState)) {
                    System.out.println(displayGameFromState(gameState));
                    System.out.println(player2 + " wins!");
                    return game;
                }
                if (checkDraw(gameState)) {
                    System.out.println(displayGameFromState(gameState));
                    System.out.println("Its a draw!");
                    return game;
                }
                first = true;
            }
            gameCtr++;
        }
    }

    // Given the player names (where player two is "Computer"),
    // Run the one-player game.
    // Return an ArrayList of game states of each turn -- in other words, the gameHistory
    private static ArrayList<char[][]> runOnePlayerGame(String[] playerNames) {
        // TODO
        ArrayList<char[][]> game = new ArrayList<char[][]>();
        game.add(getInitialGameState());
        boolean first = true;
        int gameCtr = 0;
        //who plays first
        System.out.println("Tossing a coin to see who goes first!");
        int coin = (int) (Math.random() * 1) + 1;
        String player1;
        String player2;
        if (coin == 1) {
            player1 = playerNames[0];
            player2 = playerNames[1];
        } else {
            player1 = playerNames[1];
            player2 = playerNames[0];
        }
        System.out.println(player1 + " gets to go first!");

        //game
        while (true) {
            System.out.println(displayGameFromState(game.get(gameCtr)));
            char[][] gameState;
            if (first) {
                gameState = runPlayerMove(player1, playerOneSymbol, game.get(gameCtr));
                game.add(gameState);
                if (checkWin(gameState)) {
                    System.out.println(displayGameFromState(gameState));
                    System.out.println(player1 + " wins!");
                    return game;
                }
                if (checkDraw(gameState)) {
                    System.out.println(displayGameFromState(gameState));
                    System.out.println("Its a draw!");
                    return game;
                }
                first = false;
            } else {
                System.out.println("Computers turn!");
                gameState = getCPUMove(game.get(gameCtr));
                game.add(gameState);
                if (checkWin(gameState) == true) {
                    System.out.println(displayGameFromState(gameState));
                    System.out.println(player2 + " wins!");
                    return game;
                }
                if (checkDraw(gameState)) {
                    System.out.println(displayGameFromState(gameState));
                    System.out.println("Its a draw!");
                    return game;
                }
                first = true;
            }
            gameCtr++;
        }
        //return null;
    }

    // Repeatedly prompts player for move in current state, returning new state after their valid move is made
    private static char[][] runPlayerMove(String playerName, char playerSymbol, char[][] currentState) {
        // TODO
        int[] move;
        char[][] newArr;
        while (true) {
            move = getInBoundsPlayerMove(playerName);
            if (checkValidMove(move, currentState)) {
                newArr = makeMove(move, playerSymbol, currentState);
                break;
            }
        }
        return newArr;
    }

    // Repeatedly prompts player for move. Returns [row, column] of their desired move such that row & column are on
    // the 3x3 board, but does not check for availability of that space.
    private static int[] getInBoundsPlayerMove(String playerName) {
        Scanner sc = new Scanner(System.in);
        // TODO
        System.out.println(playerName + "'s turn: ");
        while (true) {
            System.out.println(playerName + " enter row: ");
            int row = sc.nextInt();
            System.out.println(playerName + " enter column: ");
            int column = sc.nextInt();
            if (row >= 0 && row <= 2 && column >= 0 && column <= 2) {
                int[] array = new int[2];
                array[0] = row;
                array[1] = column;
                return array;
            }
        }
        //return null;
    }

    // Given a [row, col] move, return true if a space is unclaimed.
    // Doesn't need to check whether move is within bounds of the board.
    private static boolean checkValidMove(int[] move, char[][] state) {
        // TODO
        if (state[move[0]][move[1]] == emptySpaceSymbol) {
            return true;
        }
        return false;
    }

    // Given a [row, col] move, the symbol to add, and a game state,
    // Return a NEW array (do NOT modify the argument currentState) with the new game state
    private static char[][] makeMove(int[] move, char symbol, char[][] currentState) {
        // TODO:
        // Hint: Make use of Arrays.copyOf() somehow to copy a 1D array easily
        // You may need to use it multiple times for a 1D array
        char[][] newArr = new char[currentState.length][currentState[0].length];
        for (int row = 0; row < currentState.length; row++) {
            char[] temp = Arrays.copyOf(currentState[row], currentState[row].length);
            newArr[row] = temp;
        }
        newArr[move[0]][move[1]] = symbol;
        return newArr;
    }

    // Given a state, return true if some player has won in that state
    private static boolean checkWin(char[][] state) {
        // TODO
        // Hint: no need to check if player one has won and if player two has won in separate steps,
        // you can just check if the same symbol occurs three times in any row, col, or diagonal (except empty space symbol)
        // But either implementation is valid: do whatever makes most sense to you.

        // Horizontals
        if (state[0][0] == state[0][1] && state[0][1] == state[0][2] && state[0][0] != emptySpaceSymbol && state[0][1] != emptySpaceSymbol && state[0][2] != emptySpaceSymbol) {
            return true;
        }
        if (state[1][0] == state[1][1] && state[1][1] == state[1][2] && state[1][0] != emptySpaceSymbol && state[1][1] != emptySpaceSymbol && state[1][2] != emptySpaceSymbol) {
            return true;
        }
        if (state[2][0] == state[2][1] && state[2][1] == state[2][2] && state[2][0] != emptySpaceSymbol && state[2][1] != emptySpaceSymbol && state[2][2] != emptySpaceSymbol) {
            return true;
        }
        // Verticals
        if (state[0][0] == state[1][0] && state[1][0] == state[2][0] && state[0][0] != emptySpaceSymbol && state[1][0] != emptySpaceSymbol && state[2][0] != emptySpaceSymbol) {
            return true;
        }
        if (state[0][1] == state[1][1] && state[1][1] == state[2][1] && state[0][1] != emptySpaceSymbol && state[1][1] != emptySpaceSymbol && state[2][1] != emptySpaceSymbol) {
            return true;
        }
        if (state[0][2] == state[1][2] && state[1][2] == state[2][2] && state[0][2] != emptySpaceSymbol && state[1][2] != emptySpaceSymbol && state[2][2] != emptySpaceSymbol) {
            return true;
        }
        // Diagonals
        if (state[0][0] == state[1][1] && state[1][1] == state[2][2] && state[0][0] != emptySpaceSymbol && state[1][1] != emptySpaceSymbol && state[2][2] != emptySpaceSymbol) {
            return true;
        }
        if (state[0][2] == state[1][1] && state[1][1] == state[2][0] && state[0][2] != emptySpaceSymbol && state[1][1] != emptySpaceSymbol && state[2][0] != emptySpaceSymbol) {
            return true;
        }
        return false;
    }

    // Given a state, simply checks whether all spaces are occupied. Does not care or check if a player has won.
    private static boolean checkDraw(char[][] state) {
        // TODO
        return state[0][0] != emptySpaceSymbol && state[1][1] != emptySpaceSymbol && state[2][2] != emptySpaceSymbol && state[0][1] != emptySpaceSymbol && state[0][2] != emptySpaceSymbol && state[1][0] != emptySpaceSymbol && state[1][2] != emptySpaceSymbol && state[2][0] != emptySpaceSymbol && state[2][1] != emptySpaceSymbol;
    }

    // Given a game state, return a new game state with move from the AI
    // It follows the algorithm in the PDF to ensure a tie (or win if possible)
    private static char[][] getCPUMove(char[][] gameState) {
        // TODO
        // Hint: you can call makeMove() and not end up returning the result, in order to "test" a move
        // and see what would happen. This is one reason why makeMove() does not modify the state argument

        // Determine all available spaces
        ArrayList<int[]> moves = getValidMoves(gameState);
        // If there is a winning move available, make that move
        for (int[] move : moves) {
            if (checkWin(makeMove(move, playerTwoSymbol, gameState))) {
                return makeMove(move, playerTwoSymbol, gameState);
            }
        }
        // If not, check if opponent has a winning move, and if so, make a move there
        for (int[] move : moves) {
            if (checkWin(makeMove(move, playerOneSymbol, gameState))) {
                return makeMove(move, playerTwoSymbol, gameState);
            }
        }
        // If not, move on center space if possible
        if(gameState[1][1] == emptySpaceSymbol){
            int[] middle = {1,1};
            return makeMove(middle, playerTwoSymbol, gameState);
        }
        // If not, move on corner spaces if possible
        if(gameState[0][0] == emptySpaceSymbol){
            int[] leftCorner = {0,0};
            return makeMove(leftCorner, playerTwoSymbol, gameState);
        }
        else if(gameState[0][2] == emptySpaceSymbol){
            int[] rightCorner = {0,2};
            return makeMove(rightCorner, playerTwoSymbol, gameState);
        }
        else if(gameState[2][2] == emptySpaceSymbol){
            int[] rightBottomCorner = {2,2};
            return makeMove(rightBottomCorner, playerTwoSymbol, gameState);
        }
        else if(gameState[2][0] == emptySpaceSymbol){
            int[] leftBottomCorner = {2,0};
            return makeMove(leftBottomCorner, playerTwoSymbol, gameState);
        }
        // Otherwise, move in any available spot
        if(gameState[0][1] == emptySpaceSymbol){
            int[] midTop = {0,0};
            return makeMove(midTop, playerTwoSymbol, gameState);
        }
        else if(gameState[1][0] == emptySpaceSymbol){
            int[] midLeft = {0,2};
            return makeMove(midLeft, playerTwoSymbol, gameState);
        }
        else if(gameState[2][1] == emptySpaceSymbol){
            int[] bottomMid = {2,2};
            return makeMove(bottomMid, playerTwoSymbol, gameState);
        }
        else if(gameState[1][2] == emptySpaceSymbol){
            int[] rightMid = {2,0};
            return makeMove(rightMid, playerTwoSymbol, gameState);
        }
        return null;
    }

    // Given a game state, return an ArrayList of [row, column] positions that are unclaimed on the board
    private static ArrayList<int[]> getValidMoves(char[][] gameState) {
        // TODO
        ArrayList<int[]> arr = new ArrayList<int[]>();
        for (int row = 0; row < gameState.length; row++) {
            for (int column = 0; column < gameState[row].length; column++) {
                if (gameState[row][column] == emptySpaceSymbol) {
                    int[] temp = new int[2];
                    temp[0] = row;
                    temp[1] = column;
                    arr.add(temp);
                }
            }
        }
        return arr;
    }

    // Given player names and the game history, display the past game as in the PDF sample code output
    private static void runGameHistory(String[] playerNames, ArrayList<char[][]> gameHistory) {
        // TODO
        // We have the names of the players in the format [playerOneName, playerTwoName]
        // Player one always gets 'X' while player two always gets 'O'
        // However, we do not know yet which player went first, but we'll need to know...
        // Hint for the above: which symbol appears after one turn is taken?

        // Hint: iterate over gameHistory using a loop
        boolean whoPlays = false; //if false player 2 goes first
        System.out.println(playerNames[0] + " (X) vs " + playerNames[1] + " (O)");
        System.out.println(displayGameFromState(gameHistory.get(0)));

        //for who went first
        char[][] game = gameHistory.get(1);
        for (char[] chars : game) {
            for (int column = 0; column < chars.length; column++) {
                if (chars[column] == playerOneSymbol) {
                    whoPlays = true;
                    break;
                }
            }
        }

        for(int ctr = 1; ctr < gameHistory.size();ctr++){
            if(whoPlays){
                System.out.println(playerNames[0] + ":");
                System.out.println(displayGameFromState(gameHistory.get(ctr)));
                whoPlays = false;
                if(checkWin(gameHistory.get(ctr))){
                    System.out.println(playerNames[0] + " wins!");
                }
            }
            else{
                System.out.println(playerNames[1] + ":");
                System.out.println(displayGameFromState(gameHistory.get(ctr)));
                whoPlays = true;
                if(checkWin(gameHistory.get(ctr))){
                    System.out.println(playerNames[1] + " wins!");
                }
            }
            if(checkDraw(gameHistory.get(ctr))){
                System.out.println("Its a draw!");
            }
        }
    }
}
