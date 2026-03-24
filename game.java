
public class game {
    board board;
    player whitePlayer;
    player blackPlayer;
    boolean currentTurn = true; // true for white, false for black
    boolean gameRunning = true;

    void startGame() {
        // Initialize the board and players
        board = new board();
        whitePlayer = new player(true, new piece[16]);
        blackPlayer = new player(false, new piece[16]);
        board.display();
    }

    void endGame() {
        // Determine the winner and end the game
        gameRunning = false;
        System.out.println("Game ended!");
    }
    
    void play(){
        // Main game loop to handle player turns and moves
        while(gameRunning) {
            if(currentTurn == true) {
                // White player's turn
                whitePlayer.makeMove();
                currentTurn = false; // Switch to black player's turn
            } else {
                // Black player's turn
                blackPlayer.makeMove();
                currentTurn = true; // Switch to white player's turn
            }
            board.display();
            
            // Optional: Check for quit command
            System.out.print("Continue? (y/n): ");
            java.util.Scanner sc = new java.util.Scanner(System.in);
            String response = sc.nextLine().toLowerCase();
            if(response.equals("n")) {
                endGame();
            }
        }
    }
    

    
    public static void main(String[] args) {
        game chessGame = new game();
        chessGame.startGame();
        chessGame.play();
    }
}