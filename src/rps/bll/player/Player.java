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

        // Count the number of times the human player has chosen each move in the last two rounds
        int rockCount = 0;
        int paperCount = 0;
        int scissorCount = 0;
        for (int i = results.size() - 1; i >= Math.max(0, results.size() - 2); i--) {
            Result result = results.get(i);
            if (result.getLoserMove().equals("Rock")) {
                rockCount++;
            } else if (result.getLoserMove().equals("Paper")) {
                paperCount++;
            } else if (result.getLoserMove().equals("Scissor")) {
                scissorCount++;
            }
        }

        // Use the human player's move history to make a more informed decision
        if (rockCount >= 2) {
            return Move.Paper;
        } else if (paperCount >= 2) {
            return Move.Scissor;
        } else if (scissorCount >= 2) {
            return Move.Rock;
        } else {
            // Choose a move randomly with equal probability
            Random random = new Random();
            int randomMove = random.nextInt(3);
            if (randomMove == 0) {
                return Move.Rock;
            } else if (randomMove == 1) {
                return Move.Paper;
            } else {
                return Move.Scissor;
            }
        }
    }
}

