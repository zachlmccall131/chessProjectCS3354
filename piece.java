abstract class piece {
    String name;
    boolean isWhite;
    int x;
    int y;
    

    void posibleMoves(String name, char x, int y) {
        // Returns a list of possible moves for the piece based on its type and current position
    }

    void move(String name,char newX, int newY) {
        // Moves the piece to the new position
    }




    public piece(String name,boolean isWhite, char x, int y) {
        this.name = name;
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
    }   


    public class rook extends piece {
        public rook(String name, boolean isWhite, char x, int y) {
            super(name, isWhite, x, y);
            if(isWhite) {
                this.name = "wR";
            } else {
                this.name = "bR";
            }
        }
        
        @Override
        public void posibleMoves(String name, char x, int y) {
            // Rooks can move any number of squares along a rank or file, but cannot leap over other pieces.
        }
    }

    public class knight extends piece {
        public knight(String name, boolean isWhite, char x, int y) {
            super(name, isWhite, x, y);
            if(isWhite) {
                this.name = "wN";
            } else {
                this.name = "bN";
            }

        }
    }
    public class bishop extends piece {
        public bishop(String name, boolean isWhite, char x, int y) {
            super(name, isWhite, x, y);   
            if(isWhite) {
                this.name = "wB";
            } else {
                this.name = "bB";
            }
        }
    }
    public class queen extends piece {
        public queen(String name, boolean isWhite, char x, int y) {
            super(name, isWhite, x, y);
            if(isWhite) {
                this.name = "wQ";
            } else {
                this.name = "bQ";
            }
        }
    }
    public class king extends piece {
        public king(String name, boolean isWhite, char x, int y) {
            super(name, isWhite, x, y);
            if(isWhite) {
                this.name = "wK";
            } else {
                this.name = "bK";
            }
        }
    }
    public class pawn extends piece {
        public pawn(String name, boolean isWhite, char x, int y) {
            super(name, isWhite, x, y);
            if(isWhite) {
                this.name = "wp";
            } else {
                this.name = "bp";
            }
        }
    }
}
