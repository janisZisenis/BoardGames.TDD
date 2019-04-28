package Lib.CLI.View.InputGenerators.ConsoleGameMessenger;

import Lib.Data.Mark;
import Lib.Games.MessagingGame.GameMessenger;
import Lib.GameEvaluation.WinnerProvider;
import Lib.CLI.View.ConsoleBoardView.MarkToStringMapper;

public class ConsoleGameMessenger implements GameMessenger {

    private final String beginningMessage = "Welcome To TicTacToe!";
    private final String winnerMessage = " wins!";
    private final String drawMessage = "Draw!";

    private final WinnerProvider provider;
    private final MarkToStringMapper mapper;

    public ConsoleGameMessenger(WinnerProvider provider, MarkToStringMapper mapper) {
        this.provider = provider;
        this.mapper = mapper;
    }

    public void publishBeginningMessage() {
        System.out.println(beginningMessage);
    }

    public void publishGameOverMessage() {
        if(hasWinner()) {
            publishWinner();
        } else {
            publishDraw();
        }
    }

    private boolean hasWinner() {
        return provider.hasWinner();
    }

    private void publishWinner() {
        Mark winner = provider.getWinner();
        System.out.println(mapper.map(winner) + winnerMessage);
    }

    private void publishDraw() {
        System.out.println(drawMessage);
    }
}
