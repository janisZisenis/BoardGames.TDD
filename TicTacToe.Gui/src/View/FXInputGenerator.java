package View;

import Lib.Data.Input.Input;
import Lib.Messages.GenerationMessages;
import Lib.Players.InputGenerator;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;

import java.util.Optional;


public class FXInputGenerator extends Pane implements InputGenerator {

    private final String rowMessage = GenerationMessages.rowMessage;
    private final String columnMessage = GenerationMessages.columnMessage;

    private final String title = "Input Dialog!";

    public FXInputGenerator() {}

    public Input generate() {
        int row = promptInt(rowMessage);
        int column = promptInt(columnMessage);

        return new Input(row, column);
    }

    private int promptInt(String promptMessage) {
        Optional<String> s = prompt(promptMessage);
        while(isNotAnInt(s)) {
            s = prompt(promptMessage);
        }

        return Integer.valueOf(s.get());
    }

    private boolean isNotAnInt(Optional<String> s) {
        if(!s.isPresent())
            return true;

        try {
            Integer.valueOf(s.get());
        } catch(NumberFormatException e) {
            return true;
        }

        return false;
    }

    private Optional<String> prompt(String promptMessage) {
        TextInputDialog d = new TextInputDialog();
        d.setTitle(title);
        d.setHeaderText(promptMessage);
        d.getEditor().setText("");
        d.setX(d.getX() - 20);
        return d.showAndWait();
    }
}
