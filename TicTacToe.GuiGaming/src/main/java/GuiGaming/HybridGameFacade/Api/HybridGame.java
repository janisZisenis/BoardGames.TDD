package GuiGaming.HybridGameFacade.Api;

import InputGeneration.Input.Input;

public interface HybridGame {
    boolean isOver();
    boolean needsInput();
    void play(Input input);
    void play();
}
