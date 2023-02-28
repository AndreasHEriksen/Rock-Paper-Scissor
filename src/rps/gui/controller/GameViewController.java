package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {
    private boolean rockChosen;
    private boolean paperChosen;
    private boolean scissorChosen;
    public Move chosenMove;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {



    }

    public void handleChooseRock(ActionEvent actionEvent) {
        rockChosen = true;

        chosenMove = Move.Rock;
        getMove();
        playGame(chosenMove);
    }

    public void handleChoosePaper(ActionEvent actionEvent) {
        paperChosen = true;

        chosenMove = Move.Paper;
        getMove();
        playGame(chosenMove);
    }

    public void handleChooseScissor(ActionEvent actionEvent) {
        scissorChosen = true;

        chosenMove = Move.Scissor;
        getMove();
        playGame(chosenMove);
    }

    public Move getMove(){

        return chosenMove;
    }
    public void playGame(Move chosenMove){
        String playerName = "Player";

        IPlayer human = new Player(playerName, PlayerType.Human);
        IPlayer bot = new Player(getRandomBotName(), PlayerType.AI);
        GameManager ge = new GameManager(human, bot);
        ge.playRound(chosenMove);
        getResultAsString(ge.playRound(chosenMove));
        System.out.println(getResultAsString(ge.playRound(chosenMove)));
    }

    private String getRandomBotName() {
        String[] botNames = new String[] {
                "R2D2",
                "Mr. Data",
                "3PO",
                "Bender",
                "Marvin the Paranoid Android",
                "Bishop",
                "Robot B-9",
                "HAL"
        };
        int randomNumber = new Random().nextInt(botNames.length - 1);
        return botNames[randomNumber];
    }
    public String getResultAsString(Result result) {
        String statusText = result.getType() == ResultType.Win ? "wins over " : "ties ";

        return "Round #" + result.getRoundNumber() + ":" +
                result.getWinnerPlayer().getPlayerName() +
                " (" + result.getWinnerMove() + ") " +
                statusText + result.getLoserPlayer().getPlayerName() +
                " (" + result.getLoserMove() + ")!";
    }
}
