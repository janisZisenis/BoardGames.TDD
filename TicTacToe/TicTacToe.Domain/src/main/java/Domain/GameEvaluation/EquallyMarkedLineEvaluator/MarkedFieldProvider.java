package Domain.GameEvaluation.EquallyMarkedLineEvaluator;

import Domain.Data.Field.Field;
import Domain.Data.Mark;

public interface MarkedFieldProvider {

    Mark getMarkAt(Field f);
    boolean isMarked(Field f);

    class FieldIsNotMarked extends RuntimeException {}
}
