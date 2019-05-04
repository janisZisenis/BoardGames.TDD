package Board.ListenableBoard.ForwardingTests;

import Board.BoardStub;
import Board.ListenableBoard.ListenableBoard;
import Data.Field.Field;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FieldIsEmptyForwardingTest {

    private BoardStub board = new BoardStub();
    private ListenableBoard sut = new ListenableBoard(board);

    private Field field = new Field(0, 0);
    private boolean actual;

    @Test
    void IfFieldIsEmptyOnTheBoard_ItShouldBeEmptyEither() {
        makeFieldIsEmpty();

        actual = sut.isEmpty(field);

        assertTrue(actual);
    }

    @Test
    void IfFieldIsNotEmptyOnTheBoard_ItShouldBeNotEmptyEither() {
        actual = sut.isEmpty(field);

        assertFalse(actual);
    }

    private void makeFieldIsEmpty() {
        Field[] fields = new Field[] { field };
        board.setEmptyFields(fields);
    }

}
