package Domain.GameEvaluation.GameEvaluator.Api;

import Domain.Data.Line.Line;

public class WinningLineProviderStub extends WinningLineProviderDummy {
    private Line winning = null;

    public void setWinningLine(Line winning) {
        this.winning = winning;
    }

    public boolean hasWinningLine() {
        return winning != null;
    }

    public Line getWinningLine() {
        return winning;
    }
}
