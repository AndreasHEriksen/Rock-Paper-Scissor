package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import rps.bll.game.GameManager;
import rps.bll.game.Move;
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
    public Text txtPlayerWins;
    public Text txtBotWins;
    public Label lblPlayerWins;
    public Label lblBotWins;
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

    }

    public void handleChoosePaper(ActionEvent actionEvent) {
        paperChosen = true;

        chosenMove = Move.Rock;
        getMove();
    }

    public void handleChooseScissor(ActionEvent actionEvent) {
        scissorChosen = true;

        chosenMove = Move.Scissor;
        getMove();
    }

    public Move getMove(){
        String playerName = "Player";

        IPlayer human = new Player(playerName, PlayerType.Human);
        IPlayer bot = new Player(getRandomBotName(), PlayerType.AI);
        GameManager ge = new GameManager(human, bot);
        return chosenMove;
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
}
