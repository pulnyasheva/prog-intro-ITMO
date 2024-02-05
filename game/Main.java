package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.println("Enter the number of players");
        final int playersGame = sc.nextInt();
        List<Player> namePlayers = new ArrayList();
        for (int i = 0; i < playersGame; i++) {
            String player = sc.next();
            if (player.equals("RandomPlayer")) {
                namePlayers.add(new RandomPlayer(m, n));
            } else if (player.equals("HumanPlayer")) {
                namePlayers.add(new HumanPlayer(new Scanner(System.in)));
            }
        }
        final int result;
        int result1 = 0;
        if (playersGame == 2) {
            result1 = new TwoPlayerGame(
                    new PlayBoard(m, n, k, 2),
                    namePlayers.get(0),
                    namePlayers.get(1)
            ).play(true);
        } else if (playersGame == 3) {
            result1 = new ThreePlayerGame(
                    new PlayBoard(m, n, k, 3),
                    namePlayers.get(0),
                    namePlayers.get(1),
                    namePlayers.get(2)
            ).play(true);
        } else if (playersGame == 4) {
            result1 = new FourPlayerGame(
                    new PlayBoard(m, n, k, 4),
                    namePlayers.get(0),
                    namePlayers.get(1),
                    namePlayers.get(2),
                    namePlayers.get(3)
            ).play(true);
        }
        result = result1;
        switch (result) {
            case 1:
                System.out.println("First player won");
                break;
            case 2:
                System.out.println("Second player won");
                break;
            case 3:
                System.out.println("Third player won");
                break;
            case 4:
                System.out.println("Fourth player won");
                break;
            case 5:
                System.out.println("First player lose");
                break;
            case 6:
                System.out.println("Second player lose");
                break;
            case 7:
                System.out.println("Third player lose");
                break;
            case 8:
                System.out.println("Fourth player lose");
                break;
            case 0:
                System.out.println("Draw");
                break;
            default:
                throw new AssertionError("Unknown result " + result);
        }
    }
}
