package com.company.TicTacToe.LineEvaluator;

import com.company.TicTacToe.Board.Mark;
import com.company.TicTacToe.Field.Field;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsWinningLineEvaluatorTest {


    private MarkedFieldProviderStub provider = new MarkedFieldProviderStub();
    private IsWinningLineEvaluator sut = new IsWinningLineEvaluator(provider);

    private Field first = new Field(0, 1);
    private Field second = new Field(0, 2);
    private Field third = new Field(0, 3);
    private Line line = new Line(first, second, third);


    @Test
    void IfLineIsEquallyMarked_ItShouldBeTheWinningLine() {
        makeLineIsEquallyMarked();

        boolean actual = sut.isWinningLine(line);

        assertTrue(actual);
    }

    @Test
    void IfLineIsNotEquallyMarked_ItShouldNotBeTheWinningLine() {
        makeLineIsNotEquallyMarked();

        boolean actual = sut.isWinningLine(line);

        assertFalse(actual);
    }

    @Test
    void IfFirstOfLineIsNotMarked_ItShouldNotBeTheWinningLine() {
        makeFirstOfLineIsNotMarked();

        line = new Line(first, second, third);

        boolean actual = sut.isWinningLine(line);

        assertFalse(actual);
    }

    @Test
    void IfSecondOfLineIsNotMarked_ItShouldNotBeTheWinningLine() {
        makeSecondOfLineIsNotMarked();

        line = new Line(first, second, third);

        boolean actual = sut.isWinningLine(line);

        assertFalse(actual);
    }

    @Test
    void IfThirdOfLineIsNotMarked_ItShouldNotBeTheWinningLine() {
        makeThirdOfLineIsNotMarked();

        line = new Line(first, second, third);

        boolean actual = sut.isWinningLine(line);

        assertFalse(actual);
    }

    private void makeLineIsNotEquallyMarked() {
        provider.addMarkedField(first, Mark.Haley);
        provider.addMarkedField(second, Mark.John);
        provider.addMarkedField(third, Mark.Haley);
    }

    private void makeLineIsEquallyMarked() {
        provider.addMarkedField(first, Mark.John);
        provider.addMarkedField(second, Mark.John);
        provider.addMarkedField(third, Mark.John);
    }

    private void makeFirstOfLineIsNotMarked() {
        provider.addMarkedField(second, Mark.John);
        provider.addMarkedField(third, Mark.Haley);
    }

    private void makeSecondOfLineIsNotMarked() {
        provider.addMarkedField(first, Mark.John);
        provider.addMarkedField(third, Mark.Haley);
    }

    private void makeThirdOfLineIsNotMarked() {
        provider.addMarkedField(first, Mark.John);
        provider.addMarkedField(second, Mark.Haley);
    }

}
