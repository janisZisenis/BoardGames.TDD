package Lib.GameOverRules.NumberOfMovesRule;

import Api.MarkedFieldCountProvider;
import Board.BoardBoundaries;
import Lib.GameLoopImp.GameOverRule;

public class NumberOfMovesRule implements GameOverRule {

    private final int fieldCount = BoardBoundaries.fieldCount;
    private final MarkedFieldCountProvider provider;

    public NumberOfMovesRule(MarkedFieldCountProvider provider) {
        this.provider = provider;
    }

    public boolean isGameOver() {
        return provider.getMarkedFieldCount() >= fieldCount;
    }

}
