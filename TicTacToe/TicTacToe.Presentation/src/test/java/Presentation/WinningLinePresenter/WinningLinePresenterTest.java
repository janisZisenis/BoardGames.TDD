package Presentation.WinningLinePresenter;

import Domain.Data.Field.Field;
import Domain.Data.Line.Line;
import Domain.GameEvaluation.GameEvaluator.Api.WinningLineProviderStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class WinningLinePresenterTest {

    private WinningLineProviderStub provider = new WinningLineProviderStub();
    private WinningLineViewSpy view = new WinningLineViewSpy();
    private WinningLinePresenter sut = new WinningLinePresenter(view, provider);

    @Test
    void IfWinningLineIsProvidedOnUpdate_ShouldHighlightLine() {
        Line line = makeLine();
        provider.setWinningLine(line);

        sut.update();

        assertHighlightedLineEquals(line);
    }

    private void assertHighlightedLineEquals(Line expected) {
        Line actual = view.getHighlighted();
        assertEquals(expected, actual);
    }

    private Line makeLine() {
        Field first = new Field(0, 0);
        Field second = new Field(0, 2);
        Field third = new Field(2, 1);

        return new Line(first, second, third);
    }


    @Test
    void IfNoWinningLineIsProvidedOnUpdate_ShouldNotHighlightLine() {
        sut.update();

        assertHasNotHighlightedLine();
    }

    private void assertHasNotHighlightedLine() {
        boolean actual = view.hasHighlightedLine();
        assertFalse(actual);
    }


}
