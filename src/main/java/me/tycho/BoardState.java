package me.tycho;

/**
 * Represents one state of the board
 * 0 - Empty
 * 1 - X
 * -1 - O
 */
public class BoardState {
    int[][] board = new int[3][3];

    public BoardState(){

    }

    public BoardState(int[][] board){
        this.board = board;
    }

    public BoardState(BoardState boardState){
        this.board = boardState.getBoard();
    }

    /**
     * Returns whether the game is over
     * @return
     */
    public boolean terminal(){
        if (winner() != Type.EMPTY) return true;
        for(int[] row : board)
            for(int cell: row)
                if(cell == Type.EMPTY.value) return false;
        return true;
    }

    /**
     * Determines the winner of the current board, if any at all.
     * @return the Type winner
     */
    public Type winner(){
        // Horizontals
        for(int[] row : board){
            if (row[0] == row[1]  && row[1] == row[2]){
                return Type.of(row[0]);
            }
        }

        // Verticals
        for(int i = 0; i < 3; i++){
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]){
                return Type.of(board[0][i]);
            }
        }

        // Diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            return Type.of(board[0][0]);
        }
        else if (board[0][2] == board[1][1] && board[1][1] == board[2][0]){
            return Type.of(board[0][2]);
        }
        return Type.EMPTY;
    }


    /*
     * Getters and setters
     */

    public int[][] getBoard(){
        return board;
    }

    public void setBoard(int[][] board){
        this.board = board;
    }

    public void setCell(int x, int y, int value){
        board[x][y] = value;
    }

    public void setCell(int x, int y, Type value){
        board[x][y] = value.value;
    }

    public int getCell(int x, int y){
        return board[x][y];
    }

    public Type getCellT(int x, int y){
        return Type.of(board[x][y]);
    }

    /**
     * Pretty-print the game state
     * @return
     */
    @Override
    public String toString() {
        String str = "-------------\n";
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                str += "| " + Type.of(board[i][j]) + " ";
            }
            str += "|\n-------------\n";
        }
        return str;
    }

    /**
     * Pretty print the game state with column and row headers
     * @param withHeaders signature differentiator
     * @return the string that represents the game
     */
    public String toString(boolean withHeaders){
        String str = """
                    1   2   3
                  -------------
                1\s""";
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                str +="| " + Type.of(board[i][j]) + " ";
            }
            str += "|\n  -------------\n" + (i < 2 ? (i + 2) + " " : "");
        }
        return str;
    }
}
