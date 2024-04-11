package me.tycho;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents a tic-tac-toe game
 */
public class Game {
    /**
     * The state that the Game is in
     */
    private BoardState boardState = new BoardState();
    /**
     * The current player
     */
    private Type currentPlayer = Type.O;

    public Game(){

    }

    /**
     * Construct a game from a given state and player
     * @param boardState the board
     * @param currentPlayer the player
     */
    public Game(BoardState boardState, Type currentPlayer){
        this.boardState = boardState;
        this.currentPlayer = currentPlayer;
    }

    /**
     * Begin game loop logic
     */
    public void playGame(){
        while (!boardState.terminal()){
            displayGame();
            askMove();
        }
        displayGame();
        Type winner = boardState.winner();
        if (winner == Type.EMPTY)
            System.out.println("The game is tied. Nobody wins!");
        else System.out.println(boardState.winner() + " wins!");
    }

    /**
     * Display the game in a user friendly way
     */
    public void displayGame() {
        System.out.println(boardState.toString(true));
        System.out.println(currentPlayer + " to move");
    }

    /**
     * Ask the user to provide input and make a move
     */
    public void askMove(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter coordinates x y");
        int x = -1;
        int y = -1;

        try {
            x = scanner.nextInt();
            y = scanner.nextInt();
        } catch (Exception e){
            displayGame();
            System.out.println("Invalid input, try again");
            System.out.print("""
                    Coordinates should:
                     • be values 1-3
                     • appear on a new line
                     • separated by a space or newline
                    like this: \n 1 2\n""");
            askMove();
        }
        if (!makeMove(y-1, x-1)){ // invert move to match array indexes
            displayGame();
            System.out.println("Invalid move, try again");
            System.out.print("""
                    Coordinates should:
                     • be values 1-3
                     • appear on a new line
                     • separated by a space or newline
                    like this: \n 3 2\n""");
            askMove();
        }
    }

    /**
     * Update the game state on the board based on a player made move
     * @param x the move coordinate x
     * @param y the move coordinate y
     * @return whether the move was valid or not
     */
    public boolean makeMove(int x, int y){
        try {
            if (boardState.getCellT(x, y) == Type.EMPTY){
                boardState.setCell(x, y, currentPlayer);
                currentPlayer = currentPlayer == Type.X ? Type.O : Type.X;
                return true;
            }
            else {
                System.out.println("not empty cell!");
                return false;
            }
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
