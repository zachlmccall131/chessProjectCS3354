
public class board {


    String[][] board;
    String [] capturedPieces;

    void display() {

        //creates initial array


        this.board = new String[8][8];
        for(int i = 0; i < 8; i++) {

           //initialize pieces on the board
            board[1][i] = "bP"; // Black pawns
            board[6][i] = "wP"; // White pawns

            board[0][0] = "bR"; // Black rooks
            board[0][7] = "bR"; // Black rooks
            board[7][0] = "wR"; // White rooks
            board[7][7] = "wR"; // White rooks

            board[0][1] = "bN"; // Black knights
            board[0][6] = "bN"; // Black knights
            board[7][1] = "wN"; // White knights
            board[7][6] = "wN"; // White knights
            
            board[0][2] = "bB"; // Black bishops
            board[0][5] = "bB"; // Black bishops
            board[7][2] = "wB"; // White bishops
            board[7][5] = "wB"; // White bishops

            board[0][3] = "bQ"; // Black queen
            board[0][4] = "bK"; // Black king
            board[7][3] = "wQ"; // White queen
            board[7][4] = "wK"; // White king
        }
        System.out.println("  a b c d e f g h ");
        for(int i = 0; i < 8; i++) {
            System.out.print((8 - i) + " ");
            for(int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println((8 - i));
            

        
    }
}
    void getPiece(int row, int col) {
        //return piece at position
            
        }
    void movePiece(){
        //move piece to new position
        if (/*valid move*/) {
            //update board array
        } else {
            System.out.println("Invalid move!");
        }
    }
    void isCheck(){
        //check if king is in check
    }
    void isCheckmate(){
        //check if king is in checkmate
    }
}

