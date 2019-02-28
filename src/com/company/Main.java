package com.company;

import com.company.CLI.InputGeneration.ConsoleInputPrompter;
import com.company.Core.InputGeneration.AlertingValidator.AlertingValidator;
import com.company.CLI.InputGeneration.ConsoleAlerter;
import com.company.Core.InputGeneration.CompositeValidator.CompositeValidator;
import com.company.Core.InputGeneration.InputGenerator;
import com.company.Core.InputGeneration.InputValidator;
import com.company.Core.InputGeneration.PromptingInputGenerator.DefaultInputGenerator;
import com.company.Core.InputGeneration.ValidatingInputGenerator.ValidatingInputGenerator;
import com.company.TicTacToe.Board.Mark;
import com.company.TicTacToe.Board.TicTacToeBoard;
import com.company.TicTacToe.Constants.AlertingMessages;
import com.company.TicTacToe.Field;
import com.company.TicTacToe.FieldExistsValidator.FieldExistsValidator;
import com.company.TicTacToe.FieldIsEmptyValidator.FieldIsEmptyValidator;
import com.company.TicTacToe.Player.Player;
import com.company.TicTacToe.Player.PlayerConfig;

public class Main {

    static Player john;
    static Player haley;
    static Player current;
    static TicTacToeBoard board = new TicTacToeBoard();

    private static InputGenerator makeTicTacToeInputGenerator(TicTacToeBoard board, InputValidator validator) {
        ConsoleInputPrompter prompter = new ConsoleInputPrompter();
        InputGenerator generator = new DefaultInputGenerator(prompter);
        return new ValidatingInputGenerator(generator, validator);
    }

    private static CompositeValidator makeTicTacToeValidator(TicTacToeBoard board) {
        InputValidator existsValidator = makeAlertingFieldExistsValidator(board);
        InputValidator isFreeValidator = makeAlertingFieldIsFreeValidator(board);

        CompositeValidator validator = new CompositeValidator();
        validator.add(existsValidator);
        validator.add(isFreeValidator);
        return validator;
    }

    private static InputValidator makeAlertingFieldIsFreeValidator(TicTacToeBoard board) {
        ConsoleAlerter alreadyMarkedAlerter = new ConsoleAlerter(AlertingMessages.inputAlreadyMarked);
        InputValidator alreadyMarkedValidator = new FieldIsEmptyValidator(board);
        return new AlertingValidator(alreadyMarkedValidator, alreadyMarkedAlerter);
    }

    private static InputValidator makeAlertingFieldExistsValidator(TicTacToeBoard board) {
        ConsoleAlerter notExistingAlerter = new ConsoleAlerter(AlertingMessages.inputDoesNotExist);
        InputValidator notExistingValidator = new FieldExistsValidator(board);
        return new AlertingValidator(notExistingValidator, notExistingAlerter);
    }

    private static Player makeHaley(TicTacToeBoard board, InputGenerator generator) {
        PlayerConfig config = new PlayerConfig(generator, board, Mark.Haley);
        return new Player(config);
    }

    private static Player makeJohn(TicTacToeBoard board, InputGenerator generator) {
        PlayerConfig config = new PlayerConfig(generator, board, Mark.John);
        return new Player(config);
    }

    public static void main(String[] args) {
        InputValidator validator = makeTicTacToeValidator(board);
        InputGenerator generator = makeTicTacToeInputGenerator(board, validator);

        john = makeJohn(board, generator);
        haley = makeHaley(board, generator);
        current = john;

        printBoard();

        for(int i = 0; i < 9; i++) {
            playTurn();
        }

    }

    private static void playTurn() {
        current.playMove();
        togglePlayer();
        printBoard();
    }

    private static void printBoard() {
        print(board);
    }

    private static void togglePlayer() {
        current = (current == john) ? haley : john;
    }

    public static void print(TicTacToeBoard board) {
        for(int row = 0; row < 3; row++) {
            printRow(row, board);
        }
    }

    public static void printRow(int row, TicTacToeBoard board) {
        for(int col = 0; col < 3; col++) {
            Field f = new Field(row, col);
            if(board.isEmpty(f)) {
                System.out.print('.');
            }
            else {
                char c = map(board.getMarkAt(f));
                System.out.print(c);
            }
        }
        System.out.print('\n');
    }

    private static char map(Mark m) {
        return (m == Mark.John) ? 'X' : 'O';
    }

}
