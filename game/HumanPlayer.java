package game;

import java.util.Map;
import java.util.Scanner;

import static java.lang.Character.isDigit;
import static java.lang.Integer.parseInt;

public class HumanPlayer implements Player {
    private final Scanner in;
    private static final Map<Cell, String> cellToString = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0",
            Cell.A, "-",
            Cell.B, "|"
    );

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    private Move isMove(Scanner in, Position position) {
        String a = in.next();
        String b = in.next();
        int i = 0;
        int j = 0;
        while (i < a.length() || j < b.length()) {
            if (!isDigit(a.charAt(i++))) {
                return new Move(-1, -1, Cell.E);
            }
            if (!isDigit(b.charAt(j++))) {
                return new Move(-1, -1, Cell.E);
            }
        }
        return new Move(parseInt(a) - 1, parseInt(b) - 1, position.getTurn());
    }

    @Override
    public Move makeMove(Position position) {

        System.out.println();
        System.out.println("Current position");
        System.out.println(position);
        System.out.println("Enter you move for " + cellToString.get(position.getTurn()));
        while (true) {
            Move move = isMove(in, position);
            if (position.isValid(move)) {
                return move;
            } else {
                System.out.println("Wrong move. Enter you move for " + cellToString.get(position.getTurn()));
            }
        }
    }
}
