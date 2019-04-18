package com.company.TicTacToe.GameOverRules.WinningLineRule.EquallyMarkedLineEvaluator;

import com.company.TicTacToe.Board.Mark;
import com.company.TicTacToe.Board.Field.Field;
import com.company.TicTacToe.GameOverRules.WinningLineRule.LineEvaluator;
import com.company.TicTacToe.Line;

public class EquallyMarkedLineEvaluator implements LineEvaluator {

    private final MarkedFieldProvider provider;

    public EquallyMarkedLineEvaluator(MarkedFieldProvider provider) {
        this.provider = provider;
    }

    public boolean isWinningLine(Line line) {
        return isFullyMarked(line) && isEquallyMarked(line);
    }

    private boolean isFullyMarked(Line line) {
        Field first = line.getFirst();
        Field second = line.getSecond();
        Field third = line.getThird();

        return provider.isMarked(first) && provider.isMarked(second) && provider.isMarked(third);
    }

    private boolean isEquallyMarked(Line line) {
        Mark first = provider.getMarkAt(line.getFirst());
        Mark second = provider.getMarkAt(line.getSecond());
        Mark third = provider.getMarkAt(line.getThird());

        return first == second && second == third;
    }

    public Mark getWinner(Line line) {
        if(!isWinningLine(line))
            throw new LineEvaluator.NoWinnerForLineAvailable();

        return provider.getMarkAt(line.getFirst());
    }
}
