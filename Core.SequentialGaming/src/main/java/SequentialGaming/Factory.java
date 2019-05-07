package SequentialGaming;

import SequentialGaming.DelegatingGame.DelegatingGame;
import SequentialGaming.DelegatingGame.GameOverRule;
import SequentialGaming.DelegatingGame.Renderer;
import SequentialGaming.DelegatingGame.Turn;
import SequentialGaming.GameLoopImp.Game;
import SequentialGaming.GameLoopImp.GameLoopImp;
import SequentialGaming.GameOverRules.CompositeGameOverRule.CompositeGameOverRule;
import SequentialGaming.GameOverRules.WinnerRule.HasWinnerProvider;
import SequentialGaming.GameOverRules.WinnerRule.WinnerRule;
import SequentialGaming.MessagingGameLoop.GameLoop;
import SequentialGaming.MessagingGameLoop.GameLoopMessenger;
import SequentialGaming.MessagingGameLoop.MessagingGameLoop;
import SequentialGaming.MultiTurn.MultiTurn;
import SequentialGaming.MultiTurn.MultiTurnMessenger;
import SequentialGaming.MultiTurn.NullMultiTurnMessenger;


public abstract class Factory {

    public static GameLoop makeGameLoop(Game game) {
        return new GameLoopImp(game);
    }

    public static GameLoop makeMessagingGameLoop(Game game, GameLoopMessenger messenger) {
        GameLoop loop = makeGameLoop(game);
        return new MessagingGameLoop(loop, messenger);
    }

    public static Game makeGame(GameOverRule rule, Turn turn, Renderer renderer) {
        return new DelegatingGame(rule, renderer, turn);
    }

    public static CompositeGameOverRule makeCompositeGameOverRule() {
        return new CompositeGameOverRule();
    }

    public static GameOverRule makeWinnerRule(HasWinnerProvider provider) {
        return new WinnerRule(provider);
    }

    public static MultiTurn makeMultiTurn(Turn first) {
        MultiTurnMessenger messenger = new NullMultiTurnMessenger();
        return makeMessagingMultiTurn(first, messenger);
    }

    public static MultiTurn makeMessagingMultiTurn(Turn first, MultiTurnMessenger messenger) {
        return new MultiTurn(first, messenger);
    }

}
