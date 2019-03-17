package com.company.Core.CompositeInputRule;

import com.company.Core.InputGeneration.Input.Input;
import com.company.Core.InputGeneration.InputRule.InputRule;

import java.util.LinkedList;

public class CompositeInputRule implements InputRule {

    private final LinkedList<InputRule> rules = new LinkedList<InputRule>();

    public boolean isValid(Input input) {
        boolean isValid = true;

        for(int i = 0; rules.size() > i; i++) {
            InputRule r = rules.get(i);
            isValid = isValid && r.isValid(input);
        }

        return isValid;
    }

    public void add(InputRule validator) {
        rules.add(validator);
    }

}
