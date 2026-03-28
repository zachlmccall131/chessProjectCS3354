
public abstract class tile extends board {


    int tileCoordinate;
    piece pieceOnTile;
    boolean whiteTile;



    tile(int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();

    public abstract piece getPiece();


        public static final class OpenTile extends tile{

            OpenTile(int coordinate){
                super(coordinate);
            }

            @Override
            public boolean isTileOccupied(){
                return false;
            }

            @Override
            public piece getPiece(){
                return null;
            }
            
        }
        public static final class OccupiedTile extends tile{

            OccupiedTile(int coordinate, piece pieceOnTile){
                super(coordinate);
                this.pieceOnTile = pieceOnTile;

            }
            @Override
            public boolean isTileOccupied(){
                return true;
            }

            @Override
            public piece getPiece(){
                return this.pieceOnTile;
            }



        }




        

}

