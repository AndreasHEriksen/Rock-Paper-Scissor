package rps;

//Project imports
import rps.gui.ConsoleApp;
import rps.gui.JavaFXApp;

/**
 * Main class where we start
 *
 * @author smsj
 */
public class Main {


    /**
     * Main start
     * @param args
     */
    public static void main(String[] args)
    {
        //Console version
        startRPSConsoleGame();

        //JavaFX version
<<<<<<< HEAD
        //startRPSJavaFXGame();
=======
        startRPSJavaFXGame();

>>>>>>> e0b8d420ec47cf87be470751764c32b7094949b9
    }

    /**
     * Start a JavaFX version of the game
     */
    private static void startRPSJavaFXGame() {
        JavaFXApp.launch();

    }



    /**
     * Start a console version of the game
     */
    public static void startRPSConsoleGame() {
        new ConsoleApp().startGame();
    }
}



