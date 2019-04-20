package com.company.TicTacToe.TicTacToeWinningLineProviderTest;

import com.company.TicTacToe.Board.Field.Field;
import com.company.TicTacToe.Board.Mark;
import com.company.TicTacToe.BoardPresenter.WinningLineProvider;
import com.company.TicTacToe.GameOverRules.WinningLineRule.LineEvaluatorStub;
import com.company.TicTacToe.GameOverRules.WinningLineRule.LineProviderStub;
import com.company.TicTacToe.Line;
import com.company.TicTacToe.TicTacToeWinningLineProvider.TicTacToeWinningLineProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class OneLineIsProvided {

    private LineProviderStub provider = new LineProviderStub();
    private LineEvaluatorStub evaluator = new LineEvaluatorStub();
    private TicTacToeWinningLineProvider sut = new TicTacToeWinningLineProvider(provider, evaluator);

    private Line line = new Line(new Field(0, 0), new Field(0, 1), new Field(0, 2));

    @BeforeEach
    void SetUp() {
        provider.setProvidedLines(new Line[] { line });
    }

    @Test
    void IfLineHasWinner_ShouldHaveAWinningLine(){
        evaluator.setWinnerForLine(Mark.John, line);

        boolean actual = sut.hasWinningLine();

        assertTrue(actual);
    }

    @Test
    void IfLineHasNoWinner_ShouldNotHaveAWinningLine(){
        boolean actual = sut.hasWinningLine();

        assertFalse(actual);
    }


    @Test
    void IfLineHasWinner_ShouldProvideItAsWinningLine() {
        evaluator.setWinnerForLine(Mark.John, line);

        Line actual = sut.getWinningLine();

        Line expected = line;
        assertEquals(expected, actual);
    }

    @Test
    void IfLineHasNoWinner_GettingTheWinningLineShouldThrow() {
        Executable act = () -> sut.getWinningLine();

        assertThrows(WinningLineProvider.NoWinningLineAvailable.class, act);
    }





    @Test
    void IfLineHasWinner_ShouldHaveAWinner(){
        evaluator.setWinnerForLine(Mark.John, line);

        boolean actual = sut.hasWinner();

        assertTrue(actual);
    }

    @Test
    void IfLineHasNoWinner_ShouldNotHaveAWinner(){
        boolean actual = sut.hasWinner();

        assertFalse(actual);
    }


    @Test
    void IfJohnIsWinnerOfLine_ShouldProvideJohnAsWinner() {
        evaluator.setWinnerForLine(Mark.John, line);

        Mark actual = sut.getWinner();

        Mark expected = Mark.John;
        assertEquals(expected, actual);
    }

    @Test
    void IfHaleyIsWinnerOfLine_ShouldProvideHaleyAsWinner() {
        evaluator.setWinnerForLine(Mark.Haley, line);

        Mark actual = sut.getWinner();

        Mark expected = Mark.Haley;
        assertEquals(expected, actual);
    }

    @Test
    void IfLineHasNoWinner_GettingTheWinnerShouldThrow() {
        Executable act = () -> sut.getWinner();

        assertThrows(WinningLineProvider.NoWinnerAvailable.class, act);
    }

}
