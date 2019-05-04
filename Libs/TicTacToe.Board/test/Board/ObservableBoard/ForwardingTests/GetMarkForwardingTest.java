package Board.ObservableBoard.ForwardingTests;

import Board.BoardStub;
import Board.Mark;
import Board.ObservableBoard.ObservableBoard;
import Data.Field.Field;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetMarkForwardingTest {

    private BoardStub board = new BoardStub();
    private ObservableBoard sut = new ObservableBoard(board);
    private Field field;

    @Test
    void IfBoardHasHaleyOnR0C1_ItShouldHaveHaleyOnR0C1Either() {
        makeBoardHasHaleyOnRow0Column1();

        Mark actual = sut.getMarkAt(field);

        Mark expected = Mark.Haley;
        assertEquals(expected, actual);
    }

    @Test
    void IfBoardHasJohnOnR0C1_ItHaveJohnOnR0C1Either() {
        makeBoardHasJohnOnRow0Column1();

        Mark actual = sut.getMarkAt(field);

        Mark expected = Mark.John;
        assertEquals(expected, actual);
    }

    @Test
    void IfBoardHasJohnOnR1C2_ItHaveJohnOnR0C2Either() {
        makeBoardHasJohnOnRow1Column2();

        Mark actual = sut.getMarkAt(field);

        Mark expected = Mark.John;
        assertEquals(expected, actual);
    }


    private void makeBoardHasHaleyOnRow0Column1() {
        field = new Field(0, 1);
        board.setMarkOnField(Mark.Haley, field);
    }

    private void makeBoardHasJohnOnRow0Column1() {
        field = new Field(0, 1);
        board.setMarkOnField(Mark.John, field);
    }

    private void makeBoardHasJohnOnRow1Column2() {
        field = new Field(1, 2);
        board.setMarkOnField(Mark.John, field);
    }

}
