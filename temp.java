public class temp {
    
    String[][] board;
    String [] capturedPieces;

    void display() {
        System.out.println("  a b c d e f g h ");
        for(int i = 0; i < 8; i++) {
            System.out.print((8 - i) + " ");
            for(int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println((8 - i));
        }
    }

    public static void main(String[] args) {
        // Example usage of the board class
        temp chessBoard = new temp();
        chessBoard.board = new String[8][8];
        // Initialize the board with pieces (for demonstration, using "wP" for white pawn and "bP" for black pawn)
        for(int i = 0; i < 8; i++) {

            //initialize pieces on the board
            chessBoard.board[1][i] = "wP"; // White pawns
            chessBoard.board[6][i] = "bP"; // Black pawns
            chessBoard.board[0][0] = "wR"; // White rooks
            chessBoard.board[0][7] = "wR"; // White rooks
            chessBoard.board[7][0] = "bR"; // Black rooks
            chessBoard.board[7][7] = "bR"; // Black rooks

            chessBoard.board[0][1] = "wN"; // White knights
            chessBoard.board[0][6] = "wN"; // White knights
            chessBoard.board[7][1] = "bN"; // Black knights
            chessBoard.board[7][6] = "bN"; // Black knights

            chessBoard.board[0][2] = "wB"; // White bishops
            chessBoard.board[0][5] = "wB"; // White bishops
            chessBoard.board[7][2] = "bB"; // Black bishops
            chessBoard.board[7][5] = "bB"; // Black bishops

            chessBoard.board[0][3] = "wQ"; // White queen
            chessBoard.board[0][4] = "wK"; // White king

            chessBoard.board[7][3] = "bQ"; // Black queen
            chessBoard.board[7][4] = "bK"; // Black king
            

            chessBoard.board[2][0] = "##"; // Empty squares
            chessBoard.board[2][1] = "  ";
            chessBoard.board[2][6] = "##";
            chessBoard.board[2][7] = "  ";
            chessBoard.board[3][0] = "  ";
            chessBoard.board[3][1] = "##";
            chessBoard.board[3][6] = "  ";
            chessBoard.board[3][7] = "##";
            chessBoard.board[4][0] = "##";
            chessBoard.board[4][1] = "  ";
            chessBoard.board[4][6] = "##";
            chessBoard.board[4][7] = "  ";
            chessBoard.board[5][0] = "  ";
            chessBoard.board[5][1] = "##";
            chessBoard.board[5][6] = "  ";
            chessBoard.board[5][7] = "##";
            chessBoard.board[2][2] = "##"; // Empty squares
            chessBoard.board[2][3] = "  ";
            chessBoard.board[2][4] = "##";
            chessBoard.board[2][5] = "  ";
            chessBoard.board[3][2] = "  ";
            chessBoard.board[3][3] = "##";
            chessBoard.board[3][4] = "  ";
            chessBoard.board[3][5] = "##";
            chessBoard.board[4][2] = "##";
            chessBoard.board[4][3] = "  ";
            chessBoard.board[4][4] = "##";
            chessBoard.board[4][5] = "  ";
            chessBoard.board[5][2] = "  ";
            chessBoard.board[5][3] = "##";
            chessBoard.board[5][4] = "  ";
            chessBoard.board[5][5] = "##";


        }
        chessBoard.display();
    }
}
