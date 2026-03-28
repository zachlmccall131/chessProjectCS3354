

public abstract class piece {
    
    String pieceName;
    boolean isWhite;

    


    public piece(String pieceName,boolean isWhite) {
        this.pieceName = pieceName;
        this.isWhite = isWhite;
    }   
    public void possibleMove(String pieceName){
    
    }


    public static class rook extends piece {
        public rook(String pieceName, boolean isWhite) {
            super(pieceName, isWhite);
            if(isWhite) {
                this.pieceName = "wR";
            } else {
                this.pieceName = "bR";
            }
        }
        
        @Override
        public void possibleMove(String pieceName){
            // Rooks can move any number of squares along a rank or file, but cannot leap over other pieces.
        }
    }

    public static class knight extends piece {
        public knight(String pieceName, boolean isWhite) {
            super(pieceName, isWhite);
            if(isWhite) {
                this.pieceName = "wN";
            } else {
                this.pieceName = "bN";
            }
        }
         @Override
        public void possibleMove(String pieceName){
            // Rooks can move any number of squares along a rank or file, but cannot leap over other pieces.
        }
        }
    
    public static class bishop extends piece {
        public bishop(String pieceName, boolean isWhite) {
            super(pieceName, isWhite);
            if(isWhite) {
                this.pieceName = "wB";
            } else {
                this.pieceName = "bB";
            }
        }
         @Override
        public void possibleMove(String pieceName){
            // Rooks can move any number of squares along a rank or file, but cannot leap over other pieces.
        }
    }
    public static class queen extends piece {
        public queen(String pieceName, boolean isWhite) {
            super(pieceName, isWhite);
            if(isWhite) {
                this.pieceName = "wQ";
            } else {
                this.pieceName = "bQ";
            }
        }
         @Override
        public void possibleMove(String pieceName){
            // Rooks can move any number of squares along a rank or file, but cannot leap over other pieces.
        }
    }
    public static class king extends piece {
        public king(String pieceName, boolean isWhite) {
            super(pieceName, isWhite);
            if(isWhite) {
                this.pieceName = "wK";
            } else {
                this.pieceName = "bK";
            }
        }
         @Override
        public void possibleMove(String pieceName){
            // Rooks can move any number of squares along a rank or file, but cannot leap over other pieces.
        }
    }
    public static class pawn extends piece {
        public pawn(String pieceName, boolean isWhite) {
            super(pieceName, isWhite);
            if(isWhite) {
                this.pieceName = "wp";
            } else {
                this.pieceName = "bp";
            }
        }
         @Override
        public void possibleMove(String pieceName){
            // Rooks can move any number of squares along a rank or file, but cannot leap over other pieces.
        }
    }
}
