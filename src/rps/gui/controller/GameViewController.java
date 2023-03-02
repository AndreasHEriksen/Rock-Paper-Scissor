package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.net.URL;
import java.nio.file.Path;
import java.util.Random;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {
    public ImageView imgPlayer;
    public ImageView imgBot;
    @FXML private Text txtBotWins;
    @FXML
    private Text txtPlayerWins;
    private boolean rockChosen;
    private boolean paperChosen;
    private boolean scissorChosen;
    public Move chosenMove;
    private IPlayer human;

    private IPlayer bot;
    private GameManager ge;
    private Image imageRock = new Image("rps/gui/Images/Rock.png");
    private Image imagePaper = new Image("rps/gui/Images/Paper.png");
    private Image imageScissor = new Image("rps/gui/Images/Scissor.png");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String playerName = "Player";
        human = new Player(playerName, PlayerType.Human);
        bot = new Player(getRandomBotName(), PlayerType.AI);
        ge = new GameManager(human, bot);
    }

    public void handleChooseRock(ActionEvent actionEvent) {

        rockChosen = true;

        chosenMove = Move.Rock;

        imgPlayer.setImage(imageRock);


        playGame(chosenMove);
    }

    public void handleChoosePaper(ActionEvent actionEvent) {
        paperChosen = true;

        chosenMove = Move.Paper;
        imgPlayer.setImage(imagePaper);
        playGame(chosenMove);
    }

    public void handleChooseScissor(ActionEvent actionEvent) {
        scissorChosen = true;

        chosenMove = Move.Scissor;
        imgPlayer.setImage(imageScissor);
        playGame(chosenMove);

    }

    public Move getMove(){

        return chosenMove;
    }

    private int botWins = 0;
    private int playerWins = 0;

    public void playGame(Move chosenMove){
        Result result = ge.playRound(chosenMove);
        String resultString = getResultAsString(result);
        changeBotImg(result);
        System.out.println(resultString);
        if (result.getType() == ResultType.Win && result.getWinnerPlayer().getPlayerType() == PlayerType.AI) {
            botWins++;
            txtBotWins.setText(Integer.toString(botWins));
        } else if (result.getType() == ResultType.Win && result.getWinnerPlayer().getPlayerType() == PlayerType.Human) {
            playerWins++;
            txtPlayerWins.setText(Integer.toString(playerWins));
        }
    }

    private String getRandomBotName() {
        String[] botNames = new String[] {
                "R2D2",
                "R2D2"
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


    public void changeBotImg(Result result){
        if(result.getWinnerPlayer().getPlayerName() == "R2D2"){
            if (result.getWinnerMove() == Move.Rock){
                imgBot.setImage(imageRock);
            }
            else if (result.getWinnerMove() == Move.Paper){
                imgBot.setImage(imagePaper);
            } else if (result.getWinnerMove() == Move.Scissor) {
                imgBot.setImage(imageScissor);
            }
        } else if (result.getLoserPlayer().getPlayerName() == "R2D2") {
            if (result.getLoserMove() == Move.Rock){
                imgBot.setImage(imageRock);
            }
            else if (result.getLoserMove() == Move.Paper){
                imgBot.setImage(imagePaper);
            } else if (result.getLoserMove() == Move.Scissor) {
                imgBot.setImage(imageScissor);
            }
        }
        else {
            imgBot.setImage(imgPlayer.getImage());
        }

    }
}
