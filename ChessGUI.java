import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ChessGUI extends JFrame {
    ChessGame game = new ChessGame();
    JButton[][] squares = new JButton[8][8];
    int selR = -1, selC = -1;
    int lastFromR=-1,lastFromC=-1,lastToR=-1,lastToC=-1;
    Color light = new Color(240,217,181);
    Color dark = new Color(181,136,99);
    Color highlight = new Color(255,255,0);
    Color lastMoveColor = new Color(255,215,0);
    DefaultListModel<String> movesModel = new DefaultListModel<>();

    public ChessGUI(){
        super("Chess GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(8,8));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        for(int r=0;r<8;r++) for(int c=0;c<8;c++){
            JButton b = new JButton();
            b.setMargin(new Insets(0,0,0,0));
            b.setFocusPainted(false);
            final int rr=r, cc=c;
            b.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent e){
                    selectSquare(rr,cc);
                }
                public void mouseReleased(MouseEvent e){
                    releaseSquare(rr,cc);
                }
            });
            squares[r][c]=b;
            boardPanel.add(b);
        }

        // Resize listener to adjust font/icon size when window changes
        boardPanel.addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e){ updateSquareFonts(); }
        });

        JPanel side = new JPanel(new BorderLayout());
        JLabel turnLabel = new JLabel(" ");
        side.add(turnLabel, BorderLayout.NORTH);
        JList<String> movesList = new JList<>(movesModel);
        JScrollPane sp = new JScrollPane(movesList);
        sp.setPreferredSize(new Dimension(220,400));
        side.add(sp, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton reset = new JButton("Reset");
        reset.addActionListener(e -> { game = new ChessGame(); selR=selC=-1; lastFromR=lastToR=lastFromC=lastToC=-1; updateBoard(); updateMoves(); });
        bottom.add(reset);
        JButton quit = new JButton("Quit"); quit.addActionListener(e -> System.exit(0)); bottom.add(quit);
        side.add(bottom, BorderLayout.SOUTH);

        add(boardPanel, BorderLayout.CENTER);
        add(side, BorderLayout.EAST);

        updateBoard(); updateMoves();
        updateSquareFonts();

        // update turn label periodically
        Timer t = new Timer(200, e -> turnLabel.setText((game.whiteToMove?"White":"Black")+" to move"));
        t.start();

        pack(); setSize(800,600); setLocationRelativeTo(null); setVisible(true);
    }

    private String pieceSymbol(Piece p){
        if(p==null) return "";
        switch(p.type){
            case KING: return p.white ? "\u2654" : "\u265A";
            case QUEEN: return p.white ? "\u2655" : "\u265B";
            case ROOK: return p.white ? "\u2656" : "\u265C";
            case BISHOP: return p.white ? "\u2657" : "\u265D";
            case KNIGHT: return p.white ? "\u2658" : "\u265E";
            case PAWN: return p.white ? "\u2659" : "\u265F";
        }
        return "";
    }

    void selectSquare(int r,int c){
        Piece p = game.board[r][c];
        if(p==null) return;
        if(p.white!=game.whiteToMove) return;
        selR=r; selC=c;
        clearHighlights();
        squares[r][c].setBackground(highlight);
        List<Move> moves = game.generateLegalMovesForPiece(r,c);
        for(Move m: moves){ squares[m.r2][m.c2].setBackground(Color.GREEN); }
    }

    void releaseSquare(int r,int c){
        if(selR==-1) return;
        if(selR==r && selC==c){ selR=-1; selC=-1; clearHighlights(); updateBoard(); return; }
        String from = ChessGame.rcToAlgebraic(selR,selC);
        String to = ChessGame.rcToAlgebraic(r,c);
        Piece src = game.board[selR][selC];
        boolean ok = game.makeMoveString(from+to);
        if(!ok){ JOptionPane.showMessageDialog(this, "Illegal move or invalid selection"); }
        else {
            lastFromR = selR; lastFromC = selC; lastToR = r; lastToC = c;
            // promotion dialog
            if(src!=null && src.type==PieceType.PAWN && (lastToR==0 || lastToR==7)){
                String[] options = {"Queen","Rook","Bishop","Knight"};
                int choice = JOptionPane.showOptionDialog(this, "Choose promotion:", "Promotion", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                Piece moved = game.board[lastToR][lastToC];
                if(choice>=0){
                    PieceType t;
                    switch(choice){
                        case 1: t = PieceType.ROOK; break;
                        case 2: t = PieceType.BISHOP; break;
                        case 3: t = PieceType.KNIGHT; break;
                        default: t = PieceType.QUEEN; break;
                    }
                    boolean color = moved.white;
                    game.board[lastToR][lastToC] = new Piece(t,color);
                }
            }
        }
        selR=-1; selC=-1; clearHighlights(); updateBoard(); updateMoves();
    }

    void updateBoard(){
        for(int r=0;r<8;r++) for(int c=0;c<8;c++){
            JButton b = squares[r][c];
            Piece p = game.board[r][c];
            b.setText(pieceSymbol(p));
            boolean isLight = ((r+c)%2==0);
            b.setBackground(isLight?light:dark);
            b.setForeground(Color.BLACK);
        }
        // highlight last move
        if(lastFromR!=-1){ squares[lastFromR][lastFromC].setBackground(lastMoveColor); }
        if(lastToR!=-1){ squares[lastToR][lastToC].setBackground(lastMoveColor); }
        updateSquareFonts();
    }

    void updateMoves(){
        movesModel.clear();
        List<Move> legal = game.generateAllLegalMoves(game.whiteToMove);
        int i=0; for(Move m: legal){ movesModel.addElement((++i)+": "+m.toString()); }
    }

    void clearHighlights(){
        for(int r=0;r<8;r++) for(int c=0;c<8;c++){
            boolean isLight = ((r+c)%2==0);
            squares[r][c].setBackground(isLight?light:dark);
        }
    }

    // Adjust font size of piece symbols to fit button size
    void updateSquareFonts(){
        for(int r=0;r<8;r++) for(int c=0;c<8;c++){
            JButton b = squares[r][c];
            int w = b.getWidth(); int h = b.getHeight();
            if(w<=0 || h<=0) continue;
            int size = Math.max(12, (int)(Math.min(w,h) * 0.6));
            Font f = b.getFont().deriveFont((float)size);
            b.setFont(f);
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new ChessGUI());
    }
}
