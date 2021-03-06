package InteractiveGaming.HybridGameImp;

import Input2D.Input.Input;

public class HybridPlayerSpy implements HybridPlayer {

    private Input playedInput;
    private int timesPlayedWithoutInput = 0;
    private boolean isInputTurn = false;

    public int getTimesPlayedWithoutInput() {
        return timesPlayedWithoutInput;
    }
    public void play() {
        timesPlayedWithoutInput++;
    }

    public Input getPlayedInput() {
        return playedInput;
    }
    public void playInput(Input input) {
        playedInput = input;
    }

    public boolean isInputTurn() {
        return isInputTurn;
    }
    public void setIsInputTurn(boolean b) {
        isInputTurn = b;
    }
}
