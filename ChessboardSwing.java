
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Swing-only chessboard GUI. 8x8 board implemented with GridLayout of JButtons.
 * - Shows standard starting chess position using Unicode chess characters.
 * - Click a square to select a piece (highlighted). Click destination to move (no rules enforced).
 * - Right-click (or Shift+click) resets the board.
 *
 * Compile: javac ChessboardSwing.java
 * Run: java ChessboardSwing
 */
public class ChessboardSwing extends JFrame {
    private static final int SIZE = 8;
    private final JButton[][] squares = new JButton[SIZE][SIZE];
    private final String[][] board = new String[SIZE][SIZE];

    // Unicode chess pieces
    private static final String BK = "♚", BQ = "♛", BR = "♜", BB = "♝", BN = "♞", BP = "♟";
    private static final String WK = "♔", WQ = "♕", WR = "♖", WB = "♗", WN = "♘", WP = "♙";

    private Point selected = null; // currently selected square

    public ChessboardSwing() {
        super("Chessboard - Swing GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        JPanel boardPanel = new JPanel(new GridLayout(SIZE, SIZE));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Font pieceFont = new Font("Serif", Font.PLAIN, 36);

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                JButton btn = new JButton();
                btn.setFont(pieceFont);
                btn.setMargin(new Insets(0, 0, 0, 0));
                btn.setFocusPainted(false);
                btn.setOpaque(true);
                btn.setBorder(new LineBorder(Color.DARK_GRAY));

                final int rr = r, cc = c;
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean shift = (e.getModifiers() & ActionEvent.SHIFT_MASK) != 0;
                        if (shift) {
                            resetBoard();
                            return;
                        }
                        onSquareClicked(rr, cc);
                    }
                });

                squares[r][c] = btn;
                boardPanel.add(btn);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        JPanel control = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 6));
        JButton reset = new JButton("Reset");
        reset.addActionListener(e -> resetBoard());
        control.add(reset);

        JLabel hint = new JLabel("Click a piece, then click destination. Shift+click a square to reset.");
        control.add(hint);
        add(control, BorderLayout.SOUTH);

        resetBoard();

        setSize(680, 760);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onSquareClicked(int r, int c) {
        if (selected == null) {
            // select if piece exists
            if (board[r][c] != null && !board[r][c].isEmpty()) {
                selected = new Point(r, c);
                highlightSelected(true);
            }
        } else {
            // move piece (no rule checks)
            int sr = selected.x, sc = selected.y;
            if (sr == r && sc == c) {
                // deselect
                highlightSelected(false);
                selected = null;
                return;
            }
            board[r][c] = board[sr][sc];
            board[sr][sc] = "";
            updateButtons();
            selected = null;
        }
    }

    private void highlightSelected(boolean on) {
        if (selected == null) return;
        int r = selected.x, c = selected.y;
        if (on) {
            squares[r][c].setBorder(new LineBorder(Color.RED, 3));
        } else {
            squares[r][c].setBorder(new LineBorder(Color.DARK_GRAY));
        }
    }

    private void updateButtons() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                JButton btn = squares[r][c];
                String text = board[r][c];
                btn.setText(text == null ? "" : text);

                // color squares like a chessboard
                boolean light = (r + c) % 2 == 0;
                if (light) btn.setBackground(new Color(240, 217, 181));
                else btn.setBackground(new Color(181, 136, 99));

                // if selected, emphasize border
                btn.setBorder(new LineBorder(Color.DARK_GRAY));
            }
        }
    }

    private void resetBoard() {
        // empty
        for (int r = 0; r < SIZE; r++) for (int c = 0; c < SIZE; c++) board[r][c] = "";

        // black
        board[0][0] = BR; board[0][1] = BN; board[0][2] = BB; board[0][3] = BQ;
        board[0][4] = BK; board[0][5] = BB; board[0][6] = BN; board[0][7] = BR;
        for (int c = 0; c < SIZE; c++) board[1][c] = BP;

        // white
        board[7][0] = WR; board[7][1] = WN; board[7][2] = WB; board[7][3] = WQ;
        board[7][4] = WK; board[7][5] = WB; board[7][6] = WN; board[7][7] = WR;
        for (int c = 0; c < SIZE; c++) board[6][c] = WP;

        selected = null;
        updateButtons();
    }

    public static void main(String[] args) {
        // Ensure Swing UI on EDT
        SwingUtilities.invokeLater(() -> new ChessboardSwing());
    }
}
