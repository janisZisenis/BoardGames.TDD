package Lib.InputGenerators.AlertingInputGenerator.InputValidatorImp;

import Lib.Input.Input;
import Lib.InputGenerators.AlertingInputGenerator.InputValidatorImp.RuleChoosingInputAlerter.InputRuleDummy;
import org.junit.jupiter.api.Test;

public class InputValidatorImpAlertingTest {

    private InputRuleDummy rule = new InputRuleDummy();
    private InputAlerterMock alerter = new InputAlerterMock();
    private InputValidatorImp sut = new InputValidatorImp(rule, alerter);

    private Input input = new Input(0, 1);

    @Test
    void IfInputGetsAlerted_TheInputAlerterShouldAlertTheInput() {
        alerter.expectAlertedInput(input);

        sut.alertIsInvalid(input);

        alerter.verifyAll();
    }

}
