package Presentation.BoardViewPresenter;

import Domain.Board.BoardDecorators.ListenableBoard.BoardListener;
import Domain.Data.Field.Field;
import Domain.Data.Mark;
import Domain.GameEvaluation.EquallyMarkedLineEvaluator.MarkedFieldProvider;
import Input2D.Input.Input;
import Input2D.InputProcessor;
import Presentation.BoardViewPresenter.Api.BoardViewDelegate;

public class BoardViewPresenter implements BoardListener, BoardViewDelegate {

    private final BoardView view;
    private final MarkedFieldProvider provider;
    private InputProcessor processor;

    public BoardViewPresenter(MarkedFieldProvider provider, BoardView view, InputProcessor processor) {
        this.provider = provider;
        this.view = view;
        this.processor = processor;
    }

    public void onFieldUpdated(Field field) {
        if(isMarked(field))
            setField(field);
        else
            clearField(field);
    }

    public void onCleared() {
        view.clearAll();
    }

    private void clearField(Field field) {
        view.clearField(field);
    }

    private void setField(Field field) {
        Mark m = provider.getMarkAt(field);
        view.setField(field, m);
    }

    private boolean isMarked(Field field) {
        return provider.isMarked(field);
    }

    public void onBoardClicked(int row, int col) {
        Input input = new Input(row, col);
        processor.process(input);
    }
}
