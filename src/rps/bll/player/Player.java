package rps.bll.player;

//Project imports
import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;

//Java imports
import java.util.ArrayList;
import java.util.Random;

/**
 * Example implementation of a player.
 *
 * @author smsj
 */
public class Player implements IPlayer {

    private String name;
    private PlayerType type;

    private int rounds;

    /**
     * @param name
     */
    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
    }


    @Override
    public String getPlayerName() {
        return name;
    }


    @Override
    public PlayerType getPlayerType() {
        return type;
    }


    /**
     * Decides the next move for the bot...
     *
     * @param state Contains the current game state including historic moves/results
     * @return Next move
     */
    @Override
    public Move doMove(IGameState state) {
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();
        roundLostCounter(results);
        // Count the number of times the human player has chosen each move in the last two rounds
        int rockCount = 0;
        int paperCount = 0;
        for (int i = 0; i < results.size(); i++) {
            Result result = results.get(i);
            PlayerType loserChecker = result.getLoserPlayer().getPlayerType();
            if (getPlayerType() == loserChecker) {
                if (result.getLoserPlayer().equals(this) && result.getWinnerMove().equals(Move.Rock)) {
                    rockCount++;
                } else if (result.getLoserPlayer().equals(this) && result.getWinnerMove().equals(Move.Paper)) {
                    paperCount++;
                }
            }
        }
    return  calculateNextMove(rockCount, paperCount, results.size());
    }

    private Move calculateNextMove(int rockCount, int paperCount, int size) {
        Random random = new Random();
        int rando = random.nextInt(1, 100);
        if (size > 10) {
            int rockPercentage = 100 / rounds * rockCount;
            int paperPercentage = 100 / rounds * paperCount;

            if (rando < rockPercentage) {
                return Move.Paper;
            } else if (rando < rockPercentage + paperPercentage) {
                return Move.Rock;
            } else {
                return Move.Scissor;
            }
        }  else {
            return randomMove();
        }

    }

    private void roundLostCounter(ArrayList<Result> results) {
        rounds = 0;
        for (Result result : results) {
            if (result.getLoserPlayer().equals(this)) {
                rounds++;
            }
        }
    }

    private Move randomMove() {
        Random random1 = new Random();
        int move = random1.nextInt(3);
        if (move == 0) {
            return Move.Paper;
        } else if (move == 1) {
            return Move.Scissor;
        } else {
            return Move.Rock;
        }
    }
}
