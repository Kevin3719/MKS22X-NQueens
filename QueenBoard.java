public class QueenBoard {
  private int[][]board;
  public QueenBoard(int size){
    board = new int[size][size];
  }
  public boolean addQueen(int r, int c) {
    if (board[r][c] != 0) {
      return false;
    }
    board[r][c] = -6;
    for (int i = c + 1; i < board.length; i++) {
      board[r][i] += 1;
    }
    for (int i = 1; r + i < board.length && c + i < board.length; i++) {
      board[r + i][c + i] += 1;
    }
    for (int i = 1; r - i >= 0 && c + i < board.length; i++) {
      board[r - i][c + i] += 1;
    }
    return true;
  }
  public boolean removeQueen(int r, int c) {
    if (board[r][c] != -6) {
      return false;
    }
    board[r][c] = 0;
    for (int i = c + 1; i < board.length; i++) {
      board[r][i] -= 1;
    }
    for (int i = 1; r + i < board.length && c + i < board.length; i++) {
      board[r + i][c + i] -= 1;
    }
    for (int i = 1; r - i >= 0 && c + i < board.length; i++) {
      board[r - i][c + i] -= 1;
    }
    return true;
    }
  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _

  *_ _ _ Q

  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String output = "";
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == -6) {
          output += "Q ";
        }
        output += board[i][j] + "_ ";
      }
      output += "\n";
    }
    return output + "\n";




  }

  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;

  *        true when the board is solveable, and leaves the board in a solved state

  *@throws IllegalStateException when the board starts with any non-zero value

  */
  public boolean solve(){
    if (help(0,0)) {
      return true;
    }
    return false;
  }

  public boolean help(int col,int count) {
    if (count > 0) {
      return true;
    }
    if (col == board.length) {
      return true;
    }
      for(int i = 0; i < board.length; i++) {
        if(addQueen(i,col)) {
          System.out.print(this);
          if(help(col + 1,count)) {
            removeQueen(i,col);
            count += 1;
            return help(col, count);
          }
          else {
            removeQueen(i,col);
          }
        }
      }
      System.out.println(count);
      return false;
    }







  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    return helpcount(0,0);
    }
  public int helpcount(int col, int count) {
    if (col == board.length) {
      System.out.println(this);
      return count += 1;
    }
    for (int i = 0; i < board.length; i++) {
      if( addQueen(i, col) ) {
        count += helpcount(col + 1, 0);
      }
      removeQueen(i, col);
    }
    return count;
}
  }
