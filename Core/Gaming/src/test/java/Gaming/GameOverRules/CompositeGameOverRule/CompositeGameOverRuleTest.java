package Gaming.GameOverRules.CompositeGameOverRule;

import Gaming.GameFacade.GameOverRuleStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompositeGameOverRuleTest {

    private CompositeGameOverRule sut = new CompositeGameOverRule();

    @Test
    void FreshInstance_GameShouldBeOver() {
        boolean actual = sut.isGameOver();

        assertTrue(actual);
    }

    @Test
    void AddedNotTerminatingRule_GameShouldNotBeOver() {
        makeNotTerminatingRuleIsAdded();

        boolean actual = sut.isGameOver();

        assertFalse(actual);
    }

    @Test
    void AddedTerminatingRule_GameShouldBeOver() {
        makeTerminatingRuleIsAdded();

        boolean actual = sut.isGameOver();

        assertTrue(actual);
    }

    @Test
    void AddedTerminatingAndNotTerminatingRule_GameShouldBeOver() {
        makeTerminatingRuleIsAdded();
        makeNotTerminatingRuleIsAdded();

        boolean actual = sut.isGameOver();

        assertTrue(actual);
    }

    @Test
    void AddedNotTerminatingAndTerminatingRule_GameShouldBeOver() {
        makeNotTerminatingRuleIsAdded();
        makeTerminatingRuleIsAdded();

        boolean actual = sut.isGameOver();

        assertTrue(actual);
    }

    @Test
    void AddedTerminatingBetweenTwoNotTerminatingRules_GameShouldBeOver() {
        makeNotTerminatingRuleIsAdded();
        makeTerminatingRuleIsAdded();
        makeNotTerminatingRuleIsAdded();

        boolean actual = sut.isGameOver();

        assertTrue(actual);
    }

    @Test
    void AddedTerminatingAfterTwoNotTerminatingRules_GameShouldBeOver() {
        makeNotTerminatingRuleIsAdded();
        makeNotTerminatingRuleIsAdded();
        makeTerminatingRuleIsAdded();

        boolean actual = sut.isGameOver();

        assertTrue(actual);
    }

    private void makeTerminatingRuleIsAdded() {
        GameOverRuleStub rule = new GameOverRuleStub();
        rule.setIsGameOver(true);
        sut.add(rule);
    }

    private void makeNotTerminatingRuleIsAdded() {
        GameOverRuleStub rule = new GameOverRuleStub();
        rule.setIsGameOver(false);
        sut.add(rule);
    }
}