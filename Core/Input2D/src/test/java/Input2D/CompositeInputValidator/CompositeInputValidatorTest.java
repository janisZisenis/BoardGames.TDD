package Input2D.CompositeInputValidator;

import Input2D.Input.Input;
import Input2D.ValidInputGenerator.InputValidatorStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompositeInputValidatorTest {

    private CompositeInputValidator sut = new CompositeInputValidator();
    private Input input = new Input(0 ,1);

    @Test
    void FreshInstance_InputShouldBeValid() {
        boolean actual = sut.isValid(input);
        assertTrue(actual);
    }

    @Test
    void AddedInvalidating_InputShouldBeInvalid() {
        makeInvalidatingAdded();

        boolean actual = sut.isValid(input);
        assertFalse(actual);
    }

    @Test
    void AddedValidating_InputShouldBeInvalid() {
        makeValidatingAdded();

        boolean actual = sut.isValid(input);
        assertTrue(actual);
    }

    @Test
    void AddedValidatingAfterInvalidating_InputShouldBeFalse() {
        makeInvalidatingAdded();
        makeValidatingAdded();

        boolean actual = sut.isValid(input);
        assertFalse(actual);
    }

    @Test
    void AddedInvalidatingAfterValidating_InputShouldBeFalse() {
        makeValidatingAdded();
        makeInvalidatingAdded();

        boolean actual = sut.isValid(input);
        assertFalse(actual);
    }

    @Test
    void AddedInvalidatingInBetweenTwoValidatings_InputShouldBeFalse() {
        makeValidatingAdded();
        makeInvalidatingAdded();
        makeValidatingAdded();

        boolean actual = sut.isValid(input);
        assertFalse(actual);
    }

    @Test
    void AddedInvalidatingAfterTwoValidatings_InputShouldBeFalse() {
        makeValidatingAdded();
        makeValidatingAdded();
        makeInvalidatingAdded();

        boolean actual = sut.isValid(input);
        assertFalse(actual);
    }


    private void makeValidatingAdded() {
        InputValidatorStub validator = new InputValidatorStub();
        Input[] validInputs = { input };
        validator.setValidInputs(validInputs);
        sut.add(validator);
    }

    private void makeInvalidatingAdded() {
        InputValidatorStub validator = new InputValidatorStub();
        sut.add(validator);
    }

}
