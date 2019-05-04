package Messaging.Game.WinnerMessageProviderImp;

import Board.Mark;
import Core.GameEvaluation.GameEvaluator.WinnerProvider;

public class WinnerProviderStub implements WinnerProvider {

    private Mark winner = null;
    private boolean hasWinner = false;

    public void setWinner(Mark winner) {
        this.winner = winner;
    }
    public Mark getWinner() {
        return winner;
    }

    public void setHasWinner(boolean hasWinner) {
        this.hasWinner = hasWinner;
    }
    public boolean hasWinner() {
        return hasWinner;
    }
}
