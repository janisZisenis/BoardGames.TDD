package Lib.Model.InputGenerators.AlertingInputGenerator;

import Lib.Data.Input.Input;
import Lib.Model.Players.InputGenerator;

public class AlertingInputGenerator implements InputGenerator {

    private final InputGenerator generator;
    private InputValidator validator;

    public AlertingInputGenerator(InputGenerator generator, InputValidator validator) {
        this.generator = generator;
        this.validator = validator;
    }

    public Input generate() {
        Input input = getInput();

        while(isNotValid(input)) {
            alertInvalid(input);
            input = getInput();
        }

        return input;
    }

    private void alertInvalid(Input input) {
        validator.alertIsInvalid(input);
    }

    private Input getInput() {
        return generator.generate();
    }

    private boolean isNotValid(Input input) {
        return !validator.isValid(input);
    }
}
