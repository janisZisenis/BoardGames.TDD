package Lib.GameEvaluation.EquallyMarkedLineEvaluator;

import Data.Field.Field;
import Lib.Board.Mark;

public interface MarkedFieldProvider {

    Mark getMarkAt(Field f);
    boolean isMarked(Field f);

    class FieldIsNotMarkedException extends RuntimeException {}
}
