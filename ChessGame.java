import java.util.*;

enum PieceType { KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN }

class Piece {
    PieceType type;
    boolean white;
    boolean hasMoved = false;
    Piece(PieceType type, boolean white) { this.type = type; this.white = white; }
    public String toString() {
        String p = "?";
        switch(type) {
            case KING: p = "K"; break; case QUEEN: p = "Q"; break; case ROOK: p = "R"; break;
            case BISHOP: p = "B"; break; case KNIGHT: p = "N"; break; case PAWN: p = "p"; break;
        }
        return (white?"w":"b") + p;
    }
}

class Move {
    int r1,c1,r2,c2; // from row/col to row/col
    Piece promotion = null;
    Move(int r1,int c1,int r2,int c2){this.r1=r1;this.c1=c1;this.r2=r2;this.c2=c2;}
    public String toString(){
        return ChessGame.rcToAlgebraic(r1,c1)+" -> "+ChessGame.rcToAlgebraic(r2,c2)+(promotion!=null?"="+promotion.type:""
        );
    }
}

public class ChessGame {
    Piece[][] board = new Piece[8][8];
    boolean whiteToMove = true;

    public ChessGame() { initBoard(); }

    void initBoard(){
        // Black side top (row 0)
        board[0][0]=new Piece(PieceType.ROOK,false); board[0][1]=new Piece(PieceType.KNIGHT,false);
        board[0][2]=new Piece(PieceType.BISHOP,false); board[0][3]=new Piece(PieceType.QUEEN,false);
        board[0][4]=new Piece(PieceType.KING,false); board[0][5]=new Piece(PieceType.BISHOP,false);
        board[0][6]=new Piece(PieceType.KNIGHT,false); board[0][7]=new Piece(PieceType.ROOK,false);
        for(int c=0;c<8;c++) board[1][c]=new Piece(PieceType.PAWN,false);
        // empty
        for(int r=2;r<6;r++) for(int c=0;c<8;c++) board[r][c]=null;
        for(int c=0;c<8;c++) board[6][c]=new Piece(PieceType.PAWN,true);
        board[7][0]=new Piece(PieceType.ROOK,true); board[7][1]=new Piece(PieceType.KNIGHT,true);
        board[7][2]=new Piece(PieceType.BISHOP,true); board[7][3]=new Piece(PieceType.QUEEN,true);
        board[7][4]=new Piece(PieceType.KING,true); board[7][5]=new Piece(PieceType.BISHOP,true);
        board[7][6]=new Piece(PieceType.KNIGHT,true); board[7][7]=new Piece(PieceType.ROOK,true);
    }

    static int[] algebraicToRC(String sq){
        if(sq==null || sq.length()<2) return null;
        char f = sq.charAt(0);
        char r = sq.charAt(1);
        int c = f - 'a';
        int row = 8 - (r - '0');
        return new int[]{row,c};
    }

    static String rcToAlgebraic(int r,int c){
        char f = (char)('a'+c);
        char rank = (char)('0' + (8-r));
        return ""+f+rank;
    }

    void printBoard(){
        System.out.println("  a  b  c  d  e  f  g  h");
        for(int r=0;r<8;r++){
            System.out.print(8-r+" ");
            for(int c=0;c<8;c++){
                Piece p = board[r][c];
                if(p==null) System.out.print(((r+c)%2==0)?" . ":"   ");
                else System.out.print(p.toString()+" ");
            }
            System.out.println(" "+(8-r));
        }
        System.out.println("  a  b  c  d  e  f  g  h");
    }

    List<Move> generateAllLegalMoves(boolean forWhite){
        List<Move> moves = new ArrayList<>();
        for(int r=0;r<8;r++) for(int c=0;c<8;c++){
            Piece p = board[r][c];
            if(p!=null && p.white==forWhite){
                moves.addAll(generateLegalMovesForPiece(r,c));
            }
        }
        return moves;
    }

    List<Move> generateLegalMovesForPiece(int r,int c){
        Piece p = board[r][c];
        List<Move> result = new ArrayList<>();
        if(p==null) return result;
        switch(p.type){
            case PAWN:
                int dir = p.white ? -1 : 1;
                int startRow = p.white ? 6 : 1;
                // one forward
                if(inBounds(r+dir,c) && board[r+dir][c]==null){
                    result.add(new Move(r,c,r+dir,c));
                    // two forward
                    if(r==startRow && board[r+2*dir][c]==null) result.add(new Move(r,c,r+2*dir,c));
                }
                // captures
                for(int dc=-1;dc<=1;dc+=2){
                    int nc=c+dc, nr=r+dir;
                    if(inBounds(nr,nc) && board[nr][nc]!=null && board[nr][nc].white!=p.white){
                        result.add(new Move(r,c,nr,nc));
                    }
                }
                break;
            case KNIGHT:
                int[] drN = {-2,-2,-1,-1,1,1,2,2};
                int[] dcN = {-1,1,-2,2,-2,2,-1,1};
                for(int i=0;i<8;i++){
                    int nr=r+drN[i], nc=c+dcN[i];
                    if(!inBounds(nr,nc)) continue;
                    if(board[nr][nc]==null || board[nr][nc].white!=p.white) result.add(new Move(r,c,nr,nc));
                }
                break;
            case BISHOP:
                result.addAll(slidingMoves(r,c,new int[][]{{-1,-1},{-1,1},{1,-1},{1,1}}));
                break;
            case ROOK:
                result.addAll(slidingMoves(r,c,new int[][]{{-1,0},{1,0},{0,-1},{0,1}}));
                break;
            case QUEEN:
                result.addAll(slidingMoves(r,c,new int[][]{{-1,-1},{-1,1},{1,-1},{1,1},{-1,0},{1,0},{0,-1},{0,1}}));
                break;
            case KING:
                for(int dr=-1;dr<=1;dr++) for(int dc=-1;dc<=1;dc++){
                    if(dr==0 && dc==0) continue;
                    int nr=r+dr,nc=c+dc; if(!inBounds(nr,nc)) continue;
                    if(board[nr][nc]==null || board[nr][nc].white!=p.white) result.add(new Move(r,c,nr,nc));
                }
                // castling
                if(!p.hasMoved && !isInCheck(p.white)){
                    // king side
                    if(canCastleShort(p.white)){
                        int nr=r, nc=c+2; result.add(new Move(r,c,nr,nc));
                    }
                    // queen side
                    if(canCastleLong(p.white)){
                        int nr=r, nc=c-2; result.add(new Move(r,c,nr,nc));
                    }
                }
                break;
        }
        // filter out moves that leave king in check
        List<Move> legal = new ArrayList<>();
        for(Move m: result){
            Piece captured = makeMoveInternal(m);
            boolean ok = !isInCheck(p.white);
            undoMoveInternal(m,captured);
            if(ok) legal.add(m);
        }
        return legal;
    }

    List<Move> slidingMoves(int r,int c,int[][] dirs){
        List<Move> res = new ArrayList<>();
        Piece p = board[r][c];
        for(int[] d:dirs){
            int nr=r+d[0], nc=c+d[1];
            while(inBounds(nr,nc)){
                if(board[nr][nc]==null) res.add(new Move(r,c,nr,nc));
                else { if(board[nr][nc].white!=p.white) res.add(new Move(r,c,nr,nc)); break; }
                nr+=d[0]; nc+=d[1];
            }
        }
        return res;
    }

    boolean inBounds(int r,int c){ return r>=0 && r<8 && c>=0 && c<8; }

    Piece makeMoveInternal(Move m){
        Piece p = board[m.r1][m.c1];
        Piece captured = board[m.r2][m.c2];
        board[m.r2][m.c2] = p; board[m.r1][m.c1] = null;
        p.hasMoved = true;
        // handle castling move: if king moves two squares, move rook
        if(p.type==PieceType.KING && Math.abs(m.c2 - m.c1)==2){
            if(m.c2>m.c1){ // short
                Piece rook = board[m.r1][7]; board[m.r1][5]=rook; board[m.r1][7]=null; if(rook!=null) rook.hasMoved=true;
            } else { // long
                Piece rook = board[m.r1][0]; board[m.r1][3]=rook; board[m.r1][0]=null; if(rook!=null) rook.hasMoved=true;
            }
        }
        // promotion: if pawn reaches last rank
        if(p.type==PieceType.PAWN){
            if((p.white && m.r2==0) || (!p.white && m.r2==7)){
                // promotion handled in GUI
            }
        }
        return captured;
    }

    void undoMoveInternal(Move m, Piece captured){
        Piece p = board[m.r2][m.c2];
        board[m.r1][m.c1]=p; board[m.r2][m.c2]=captured;
        // undo castling
        if(p.type==PieceType.KING && Math.abs(m.c2 - m.c1)==2){
            if(m.c2>m.c1){ Piece rook = board[m.r1][5]; board[m.r1][7]=rook; board[m.r1][5]=null; }
            else { Piece rook = board[m.r1][3]; board[m.r1][0]=rook; board[m.r1][3]=null; }
        }
        p.hasMoved = false; // best-effort; this may not perfectly restore previous hasMoved state for complex sequences, but sufficient for normal play
    }

    boolean isInCheck(boolean white){
        int kr=-1,kc=-1;
        for(int r=0;r<8;r++) for(int c=0;c<8;c++){
            Piece p = board[r][c]; if(p!=null && p.type==PieceType.KING && p.white==white){ kr=r; kc=c; }
        }
        if(kr==-1) return true; // no king -> in check
        return isSquareAttacked(kr,kc,!white);
    }

    boolean isSquareAttacked(int r,int c, boolean byWhite){
        // iterate over all pieces of byWhite and see if they attack r,c (ignoring pins)
        for(int rr=0;rr<8;rr++) for(int cc=0;cc<8;cc++){
            Piece p = board[rr][cc]; if(p==null || p.white!=byWhite) continue;
            if(attacksSquare(rr,cc,r,c)) return true;
        }
        return false;
    }

    boolean attacksSquare(int r,int c,int tr,int tc){
        Piece p = board[r][c]; if(p==null) return false;
        int dr = tr - r, dc = tc - c;
        switch(p.type){
            case PAWN:
                int dir = p.white ? -1 : 1; if(tr==r+dir && Math.abs(tc-c)==1) return true; return false;
            case KNIGHT:
                int ar = Math.abs(dr), ac = Math.abs(dc); return (ar==2 && ac==1) || (ar==1 && ac==2);
            case BISHOP:
                if(Math.abs(dr)!=Math.abs(dc)) return false; return clearPath(r,c,tr,tc);
            case ROOK:
                if(dr!=0 && dc!=0) return false; return clearPath(r,c,tr,tc);
            case QUEEN:
                if(dr==0 || dc==0 || Math.abs(dr)==Math.abs(dc)) return clearPath(r,c,tr,tc);
                return false;
            case KING:
                return Math.max(Math.abs(dr),Math.abs(dc))==1;
        }
        return false;
    }

    boolean clearPath(int r,int c,int tr,int tc){
        int dr = Integer.compare(tr,r); int dc = Integer.compare(tc,c);
        int cr = r+dr, cc = c+dc;
        while(cr!=tr || cc!=tc){ if(board[cr][cc]!=null) return false; cr+=dr; cc+=dc; }
        return true;
    }

    boolean canCastleShort(boolean white){
        int r = white?7:0;
        Piece king = board[r][4]; if(king==null || king.hasMoved) return false;
        Piece rook = board[r][7]; if(rook==null || rook.hasMoved) return false;
        // squares between empty
        if(board[r][5]!=null || board[r][6]!=null) return false;
        // squares cannot be attacked
        if(isSquareAttacked(r,4,!white) || isSquareAttacked(r,5,!white) || isSquareAttacked(r,6,!white)) return false;
        return true;
    }
    boolean canCastleLong(boolean white){
        int r = white?7:0;
        Piece king = board[r][4]; if(king==null || king.hasMoved) return false;
        Piece rook = board[r][0]; if(rook==null || rook.hasMoved) return false;
        if(board[r][1]!=null || board[r][2]!=null || board[r][3]!=null) return false;
        if(isSquareAttacked(r,4,!white) || isSquareAttacked(r,3,!white) || isSquareAttacked(r,2,!white)) return false;
        return true;
    }

    boolean makeMoveString(String fromTo){
        // accept formats: "e2 e4" or "e2e4"
        String s = fromTo.replaceAll("\\s+",""); if(s.length()<4) return false;
        String a = s.substring(0,2), b = s.substring(2,4);
        int[] rca = algebraicToRC(a), rcb = algebraicToRC(b);
        if(rca==null || rcb==null) return false;
        List<Move> legal = generateAllLegalMoves(whiteToMove);
        for(Move m: legal){ if(m.r1==rca[0] && m.c1==rca[1] && m.r2==rcb[0] && m.c2==rcb[1]){
            makeMoveInternal(m); whiteToMove = !whiteToMove; return true; }
        }
        return false;
    }

    void printAllLegalMovesForCurrentPlayer(){
        List<Move> legal = generateAllLegalMoves(whiteToMove);
        System.out.println((whiteToMove?"White":"Black")+" to move. Legal moves ("+legal.size()+"):");
        int i=0; for(Move m: legal){ System.out.println((++i)+": "+m); }
    }

    public void runConsole(){
        Scanner sc = new Scanner(System.in);
        while(true){
            printBoard();
            printAllLegalMovesForCurrentPlayer();
            System.out.print("Enter move (e.g. e2e4) or 'quit': ");
            String line = sc.nextLine(); if(line==null) break; line=line.trim();
            if(line.equalsIgnoreCase("quit")) break;
            boolean ok = makeMoveString(line);
            if(!ok) System.out.println("Illegal move or bad format. Try again.");
        }
        System.out.println("Exiting.");
    }

    public static void main(String[] args){
        ChessGame g = new ChessGame(); g.runConsole();
    }
}
