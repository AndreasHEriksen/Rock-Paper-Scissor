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
        // Counts how many of the rounds the bot lost.
        roundLostCounter(results);

        int rockCount = 0;
        int paperCount = 0;
        if(results.size() > 100){ // Clears result list, so it only shows the 50+ > 100 rounds.
            results.subList(0, 50).clear();
        }
        for (int i = 0; i < results.size(); i++) {
            Result result = results.get(i);
                if (result.getLoserPlayer().equals(this) && result.getWinnerMove().equals(Move.Rock)) {
                    rockCount++;
                } else if (result.getLoserPlayer().equals(this) && result.getWinnerMove().equals(Move.Paper)) {
                    paperCount++;
            }
        }
        // Calculate percentages and return a random move based on percentages
    return  calculateNextMove(rockCount, paperCount, results.size());
    }
    
    private void roundLostCounter(ArrayList<Result> results) {
        rounds = 0;
        for (Result result : results) {
            if (result.getLoserPlayer().equals(this)) {
                rounds++;
            }
        }
    }
    private Move calculateNextMove(int rockCount, int paperCount, int size) {
        Random random = new Random();
        int rando = random.nextInt(1, 100);
        // Checks if results.size < 10 as it needs percentages to counter opponent
        if (size > 10) {
            double rockPercentage = 100 / rounds * rockCount;
            double paperPercentage = 100 / rounds * paperCount;

            if (rando < rockPercentage) {
                return Move.Paper;
            } else if (rando < rockPercentage + paperPercentage) {
                return Move.Scissor;
            } else {
                return Move.Rock;
            }
        }  else {
            return randomMove();
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
