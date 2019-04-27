package Lib.Model.GameOverRules.WinnerRule;

import Lib.Model.GameLoop.GameImp.GameOverRule;

public class WinnerRule implements GameOverRule {

    private final HasWinnerProvider provider;

    public WinnerRule(HasWinnerProvider provider) {
        this.provider = provider;
    }

    public boolean isGameOver() {
        return provider.hasWinner();
    }
}
