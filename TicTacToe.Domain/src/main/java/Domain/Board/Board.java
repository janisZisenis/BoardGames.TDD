package Domain.Board;

import Domain.Data.Field.Field;
import Domain.Data.Mark;
import Domain.GameEvaluation.EquallyMarkedLineEvaluator.MarkedFieldProvider;
import Domain.InputGeneration.InputValidators.FieldIsEmptyValidator.FieldIsEmptyProvider;
import Domain.NumberOfMovesRule.MarkedFieldCountProvider;
import Domain.TicTacToePlayer.MarkFieldService;

public interface Board extends MarkFieldService, FieldIsEmptyProvider, MarkedFieldCountProvider, MarkedFieldProvider {

    boolean isEmpty(Field f);
    boolean isMarked(Field f);

    void mark(Field f, Mark m);
    Mark getMarkAt(Field f);
    int getMarkedFieldCount();

    class FieldIsNotMarked extends RuntimeException {}

}
