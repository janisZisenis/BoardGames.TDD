package com.company.TicTacToe.Player;

import com.company.Core.InputGeneration.Input;
import com.company.Core.InputGeneration.InputGeneratorStub;
import com.company.TicTacToe.Board.Mark;
import com.company.TicTacToe.Field;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    InputGeneratorStub generator = new InputGeneratorStub();
    MarkFieldServiceMock markService = new MarkFieldServiceMock();
    Player sut;

    @Test
    void IfInputHasRow1AndColumn2_JohnShouldMarkFieldRow1Column2WithHisMark() {
        makeJohnMarksRow1Column1();
        makeMockExpectsJohnMarksRow1Column1();

        sut.play();

        markService.verifyAll();
    }

    @Test
    void IfInputHasRow2AndColumn1_JohnShouldMarkFieldRow2Column1WithHisMark() {
        makeJohnMarksRow2Column0();
        makeMockExpectsJohnMarksRow2Column0();

        sut.play();

        markService.verifyAll();
    }

    @Test
    void IfInputHasRow1AndColumn2_HaleyShouldMarkFieldRow1Column2WithHerMark() {
        makeHaleyMarksRow1Column1();
        makeMockExpectsHaleyMarksRow1Column1();

        sut.play();

        markService.verifyAll();
    }

    private void makePlayerIsJohn() {
        PlayerConfig config = makePlayerConfig(Mark.John);
        sut = makePlayer(config);
    }

    private void makePlayerIsHaley() {
        PlayerConfig config = makePlayerConfig(Mark.Haley);
        sut = makePlayer(config);
    }

    private Player makePlayer(PlayerConfig config) {
        return new Player(config);
    }

    private PlayerConfig makePlayerConfig(Mark mark) {
        return new PlayerConfig(generator, markService, mark);
    }


    private void makeJohnMarksRow1Column1() {
        makePlayerIsJohn();
        makeGeneratorReturnsRow1Column1();
    }

    private void makeJohnMarksRow2Column0() {
        makePlayerIsJohn();
        makeGeneratorReturnsRow2Column0();
    }

    private void makeHaleyMarksRow1Column1() {
        makePlayerIsHaley();
        makeGeneratorReturnsRow1Column1();
    }


    private void makeGeneratorReturnsRow2Column0() {
        Input in = new Input(2, 0);
        Input[] inputs = { in };
        generator.setUserInputs(inputs);
    }

    private void makeGeneratorReturnsRow1Column1() {
        Input in = new Input(1, 1);
        Input[] inputs = { in };
        generator.setUserInputs(inputs);
    }


    private void makeMockExpectsJohnMarksRow1Column1() {
        Field f = new Field(1, 1);
        markService.expectFieldWasMarkedWith(Mark.John, f);
    }

    private void makeMockExpectsHaleyMarksRow1Column1() {
        Field f = new Field(1, 1);
        markService.expectFieldWasMarkedWith(Mark.Haley, f);
    }

    private void makeMockExpectsJohnMarksRow2Column0() {
        Field f = new Field(2, 0);
        markService.expectFieldWasMarkedWith(Mark.John, f);
    }
}
