package Lib.GameEvaluation.GameEvaluator;

import Data.Line.Line;
import Lib.Board.Mark;

public interface LineEvaluator {
    boolean isWinningLine(Line line);
    Mark getWinner(Line line);

    class NoWinnerForLineAvailable extends RuntimeException {}
}
