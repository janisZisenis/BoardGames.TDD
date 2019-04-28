package App;

import Lib.Games.MessagingGame.Game;

public class Main {

    public static void main(String[] args) {
        TicTacToeFactory factory = new TicTacToeFactory();
        Game ticTacToe = factory.makeGame();

        ticTacToe.play();
    }

}
