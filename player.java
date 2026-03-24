import java.util.Scanner;

public class player {
    boolean isWhite;
    piece[] pieces;
    Scanner scanner;
    
    void isKilled(){
        //checks if a piece has been captured and updates the player's pieces accordingly
    }

    void makeMove() {
        // Gets move input from command line and processes it
        String color = isWhite ? "White" : "Black";
        System.out.print(color + " player's turn. Enter move (e.g., e2e4): ");
        
        String move = scanner.nextLine().toLowerCase().trim();
        if(move.length() == 4) {
            char fromX = move.charAt(0);
            int fromY = Integer.parseInt(String.valueOf(move.charAt(1)));
            char toX = move.charAt(2);
            int toY = Integer.parseInt(String.valueOf(move.charAt(3)));
            
            System.out.println("Moving piece from " + fromX + fromY + " to " + toX + toY);
        } else {
            System.out.println("Invalid move format!");
        }
    }

    public player(boolean isWhite, piece[] pieces) {
        this.isWhite = isWhite;
        this.pieces = pieces;
        this.scanner = new Scanner(System.in);
    }

}
