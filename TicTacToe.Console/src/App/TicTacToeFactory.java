package App;


import Lib.CLI.View.ConsoleBoardView.FieldSymbols;
import Lib.CLI.View.InputGenerators.ConsoleInputAlerter.ConsoleInputAlerter;
import Lib.CLI.View.InputGenerators.ConsoleInputGenerator.ConsoleInputGenerator;
import Lib.CLI.View.InputGenerators.ConsoleTurnMessenger.ConsoleTurnMessenger;
import Lib.CLI.View.InputGenerators.ConsoleInputAlerter.AlertingMessages;
import Lib.CLI.View.ConsoleBoardView.ConsoleBoardView;
import Lib.CLI.View.InputGenerators.ConsoleGameMessenger.ConsoleGameMessenger;
import Lib.Data.Mark;
import Lib.Board.Board;
import Lib.Board.HashingBoard.HashingBoard;
import Lib.BoardRenderer.BoardRenderer;
import Lib.BoardRenderer.WinningLineProvider;
import Lib.GameEvaluation.EquallyMarkedLineEvaluator.EquallyMarkedLineEvaluator;
import Lib.GameEvaluation.GameEvaluator.GameEvaluator;
import Lib.GameEvaluation.GameEvaluator.LineEvaluator;
import Lib.GameEvaluation.GameEvaluator.LineProvider;
import Lib.GameEvaluation.HumbleLineProvider.HumbleLineProvider;
import Lib.GameLoopImp.GameLoopImp;
import Lib.GameLoopImp.GameOverRule;
import Lib.GameLoopImp.Renderer;
import Lib.GameLoopImp.Turn;
import Lib.GameOverRules.CompositeGameOverRule.CompositeGameOverRule;
import Lib.GameOverRules.NumberOfMovesRule.NumberOfMovesRule;
import Lib.GameOverRules.WinnerRule.HasWinnerProvider;
import Lib.GameOverRules.WinnerRule.WinnerRule;
import Lib.Games.GameImp.GameImp;
import Lib.Games.GameImp.GameLoop;
import Lib.Games.MessagingGame.Game;
import Lib.Games.MessagingGame.GameMessenger;
import Lib.Games.MessagingGame.MessagingGame;
import Lib.InputGenerators.AlertingInputGenerator.AlertingInputGenerator;
import Lib.InputGenerators.AlertingInputGenerator.InputValidator;
import Lib.InputGenerators.AlertingInputGenerator.InputValidatorImp.InputAlerter;
import Lib.InputGenerators.AlertingInputGenerator.InputValidatorImp.InputRule;
import Lib.InputGenerators.AlertingInputGenerator.InputValidatorImp.InputValidatorImp;
import Lib.InputGenerators.AlertingInputGenerator.InputValidatorImp.RuleChoosingInputAlerter.RuleChoosingInputAlerter;
import Lib.InputGenerators.RandomInputGenerator.RandomInputGenerator;
import Lib.InputGenerators.ValidatingInputGenerator.ValidatingInputGenerator;
import Lib.InputRules.CompositeInputRule.CompositeInputRule;
import Lib.InputRules.FieldExistsRule.FieldExistsRule;
import Lib.InputRules.FieldIsEmptyRule.FieldIsEmptyRule;
import Lib.Players.InputGenerator;
import Lib.Players.PlayerContext;
import Lib.Players.PlayerImp;
import Lib.TwoPlayerTurn.MessagingTwoPlayerTurn.MessagingTwoPlayerTurn;
import Lib.TwoPlayerTurn.Player;
import Lib.GameEvaluation.WinnerProvider;
import Lib.CLI.View.ConsoleBoardView.MarkToStringMapper;
import Lib.CLI.View.ConsoleBoardView.MarkToXOMapper;

public class TicTacToeFactory {

    public Game makeGame() {
        Board board = makeBoard();

        Renderer renderer = makeRenderer(board);
        GameLoop loop = makeGameLoop(board);
        Game game = new GameImp(renderer, loop);
        MarkToStringMapper mapper = makeMarkToStringMapper();
        WinnerProvider provider = makeGameEvaluator(board);
        GameMessenger messenger = new ConsoleGameMessenger(provider, mapper);
        return new MessagingGame(game, messenger);
    }

    private HashingBoard makeBoard() {
        return new HashingBoard();
    }

    private GameLoop makeGameLoop(Board board) {
        GameOverRule rule = makeGameOverRule(board);
        Turn turn = makeTurn(board);
        Renderer renderer = makeRenderer(board);
        return new GameLoopImp(rule, turn, renderer);
    }

    private Renderer makeRenderer(Board board) {
        ConsoleBoardView view = makeConsoleBoardView(board);
        WinningLineProvider provider = makeWinningLineProvider(board);
        return new BoardRenderer(view, provider);
    }

    private ConsoleBoardView makeConsoleBoardView(Board board) {
        MarkToStringMapper mapper = makeMarkToStringMapper();
        return new ConsoleBoardView(board, mapper);
    }

    private MarkToStringMapper makeMarkToStringMapper() {
        return new MarkToXOMapper();
    }

    private WinningLineProvider makeWinningLineProvider(Board board) {
        return makeGameEvaluator(board);
    }

    private EquallyMarkedLineEvaluator makeLineEvaluator(Board board) {
        return new EquallyMarkedLineEvaluator(board);
    }

    private LineProvider makeLineProvider() {
        return new HumbleLineProvider();
    }

    private GameOverRule makeGameOverRule(Board board) {
        GameOverRule numberOfMovesRule = makeNumberOfMovesRule(board);
        GameOverRule winningLineRule = makeWinnerRule(board);

        CompositeGameOverRule composite = new CompositeGameOverRule();
        composite.add(numberOfMovesRule);
        composite.add(winningLineRule);
        return composite;
    }

    private GameOverRule makeWinnerRule(Board board) {
        HasWinnerProvider winningLineProvider = makeHasWinnerProvider(board);
        return new WinnerRule(winningLineProvider);
    }

    private NumberOfMovesRule makeNumberOfMovesRule(Board board) {
        return new NumberOfMovesRule(board);
    }

    private HasWinnerProvider makeHasWinnerProvider(Board board) {
        return makeGameEvaluator(board);
    }

    private GameEvaluator makeGameEvaluator(Board board) {
        LineProvider provider = makeLineProvider();
        LineEvaluator evaluator = makeLineEvaluator(board);
        return new GameEvaluator(provider, evaluator);
    }


    private Turn makeTurn(Board board) {
        Player john = makeHumanPlayer(board, Mark.John);
        Player haley = makeComputerPlayer(board, Mark.Haley);

        ConsoleTurnMessenger turnMessageView = makeConsoleTurnMessageView();
        turnMessageView.register(john, FieldSymbols.john);
        turnMessageView.register(haley, FieldSymbols.haley);
        return new MessagingTwoPlayerTurn(john, haley, turnMessageView);
    }

    private ConsoleTurnMessenger makeConsoleTurnMessageView() {
        return new ConsoleTurnMessenger();
    }

    private Player makeHumanPlayer(Board board, Mark m) {
        InputGenerator humanGenerator = makeHumanInputGenerator(board);
        return makePlayer(board, humanGenerator, m);
    }

    private Player makeComputerPlayer(Board board, Mark m) {
        InputGenerator computerGenerator = makeComputerInputGenerator(board);
        return makePlayer(board, computerGenerator, m);
    }

    private Player makePlayer(Board board, InputGenerator generator, Mark m) {
        PlayerContext johnContext = new PlayerContext(generator, board, m);
        return new PlayerImp(johnContext);
    }

    private InputGenerator makeComputerInputGenerator(Board board) {
        InputRule inputRule = makeInputRule(board);
        InputGenerator randomGenerator = makeRandomInputGenerator();
        return new ValidatingInputGenerator(randomGenerator, inputRule);
    }

    private RandomInputGenerator makeRandomInputGenerator() {
        return new RandomInputGenerator();
    }

    private InputGenerator makeHumanInputGenerator(Board board) {
        InputValidator validator = makeInputValidator(board);
        InputGenerator consoleGenerator = makeConsoleInputGenerator();
        return new AlertingInputGenerator(consoleGenerator, validator);
    }

    private ConsoleInputGenerator makeConsoleInputGenerator() {
        return new ConsoleInputGenerator();
    }


    private InputRule makeInputRule(Board board) {
        InputRule existsRule = makeFieldExistsRule();
        InputRule isFreeRule = makeFieldIsEmptyRule(board);

        CompositeInputRule inputRule = new CompositeInputRule();
        inputRule.add(existsRule);
        inputRule.add(isFreeRule);

        return inputRule;
    }

    private FieldExistsRule makeFieldExistsRule() {
        return new FieldExistsRule();
    }

    private InputAlerter makeInputAlerter(Board board) {
        InputRule existsRule = makeFieldExistsRule();
        InputRule isFreeRule = makeFieldIsEmptyRule(board);
        InputAlerter existsAlerter = makeConsoleInputAlerter(AlertingMessages.inputDoesNotExist);
        InputAlerter isFreeAlerter = makeConsoleInputAlerter(AlertingMessages.inputAlreadyMarked);

        RuleChoosingInputAlerter alerter = new RuleChoosingInputAlerter();
        alerter.register(existsRule, existsAlerter);
        alerter.register(isFreeRule, isFreeAlerter);

        return alerter;
    }

    private ConsoleInputAlerter makeConsoleInputAlerter(String inputDoesNotExist) {
        return new ConsoleInputAlerter(inputDoesNotExist);
    }

    private FieldIsEmptyRule makeFieldIsEmptyRule(Board board) {
        return new FieldIsEmptyRule(board);
    }

    private InputValidator makeInputValidator(Board board) {
        InputRule rule = makeInputRule(board);
        InputAlerter alerter = makeInputAlerter(board);
        return new InputValidatorImp(rule, alerter);
    }

}
