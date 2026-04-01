public class board {
    tile tiles[] = new tile[64];

    public board() {
        tiles[0] = new tile.OccupiedTile(0, new piece.rook("bR", false));
        tiles[1] = new tile.OccupiedTile(1, new piece.knight("bN", false));
        tiles[2] = new tile.OccupiedTile(2, new piece.bishop("bB", false));
        tiles[3] = new tile.OccupiedTile(3, new piece.queen("bQ", false));
        tiles[4] = new tile.OccupiedTile(4, new piece.king("bK", false));
        tiles[5] = new tile.OccupiedTile(5, new piece.bishop("bB", false));
        tiles[6] = new tile.OccupiedTile(6, new piece.knight("bN", false));
        tiles[7] = new tile.OccupiedTile(7, new piece.rook("bR", false));
        for(int i = 8; i < 16; i++) {
            tiles[i] = new tile.OccupiedTile(i, new piece.pawn("bP", false));
        }
        for(int i = 16; i < 48; i++) {
            tiles[i] = new tile.OpenTile(i);
        }
        for(int i = 48; i < 56; i++) {
            tiles[i] = new tile.OccupiedTile(i, new piece.pawn("wP", true));
        }
        tiles[56] = new tile.OccupiedTile(56, new piece.rook("wR", true));
        tiles[57] = new tile.OccupiedTile(57, new piece.knight("wN", true));
        tiles[58] = new tile.OccupiedTile(58, new piece.bishop("wB", true));
        tiles[59] = new tile.OccupiedTile(59, new piece.queen("wQ", true));
        tiles[60] = new tile.OccupiedTile(60, new piece.king("wK", true));
        tiles[61] = new tile.OccupiedTile(61, new piece.bishop("wB", true));
        tiles[62] = new tile.OccupiedTile(62, new piece.knight("wN", true));
        tiles[63] = new tile.OccupiedTile(63, new piece.rook("wR", true));
    }



    void printBoard() {
        for(int i = 0; i < 64; i++) {
            if(tiles[i].isTileOccupied()) {
                System.out.print(tiles[i].getPiece().pieceName + " ");
            } else if(tiles[i].isTileOccupied() == false && tiles[i].whiteTile == true) {
                System.out.print("##");
            }   
            else {
                System.out.print("  ");
            }
        }
    }

}

    