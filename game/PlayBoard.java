package game;

import java.util.Arrays;
import java.util.Map;

public class PlayBoard implements Board, Position {
    private static final Map<Cell, String> cellToString = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0",
            Cell.A, "-",
            Cell.B, "|"
    );

    private final Cell[][] field;
    private Cell turn;
    private int m;
    private int n;
    private int k;
    private int players;
    private int coutnMove = 0;

    public PlayBoard(int m, int n, int k, int players) {
        field = new Cell[m][n];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        this.m = m;
        this.n = n;
        this.k = k;
        this.players = players;
    }

    private Cell isTurn(Cell turn) {
        if (coutnMove % players == 1) {
            return Cell.O;
        } else if (coutnMove % players == 2) {
            return Cell.A;
        } else if (coutnMove % players == 3) {
            return Cell.B;
        } else {
            return Cell.X;
        }
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public GameResult makeMove(Move move) {
        coutnMove++;
        if (!isValid(move)) {
            return GameResult.LOSE;
        }

        field[move.getRow()][move.getCol()] = move.getValue();
        if (checkWin(move)) {
            return GameResult.WIN;
        }

        if (coutnMove == m * n) {
            return GameResult.DRAW;
        }

        turn = isTurn(turn);
        return GameResult.UNKNOWN;
    }

    private boolean checkWin(Move move) {
        int thisM1 = move.getRow();
        int thisN1 = move.getCol();
        return checkLine(thisM1, thisN1, new int[]{1, 1})
                || checkLine(thisM1, thisN1, new int[]{-1, 1})
                || checkLine(thisM1, thisN1, new int[]{0, 1})
                || checkLine(thisM1, thisN1, new int[]{1, 0});
    }

    private boolean checkLine(int m1, int n1, int[] direction) {
        int p = checkSigmentLine(m1, n1, direction, 1);
        return (checkSigmentLine(m1, n1, direction, 1)
                + checkSigmentLine(m1, n1, direction, -1)
                - 1)
                >= k;
    }

    private int checkSigmentLine(int m1, int n1, int[] direction, int vector) {
        int count = 0;
        int thisM = m1;
        int thisN = n1;
        while (true) {
            if (!isPosition(new Move(thisM, thisN, turn)) || field[thisM][thisN] != turn) {
                break;
            }
            thisM += vector * direction[0];
            thisN += vector * direction[1];
            count++;
        }
        return count;
    }

    public boolean isPosition(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getCol() && move.getCol() < n;
    }

    public boolean isValid(final Move move) {
        return isPosition(move)
                && field[move.getRow()][move.getCol()] == Cell.E;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int c = 0; c < n; c++) {
            sb.append(c + 1);
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < m; r++) {
            sb.append(r + 1);
            for (Cell cell : field[r]) {
                sb.append(cellToString.get(cell));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
